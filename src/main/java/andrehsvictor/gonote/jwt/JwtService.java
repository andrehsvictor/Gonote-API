package andrehsvictor.gonote.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import andrehsvictor.gonote.exception.UnauthorizedException;
import andrehsvictor.gonote.user.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${gonote.jwt.access-token.lifespan:15m}")
    private Duration accessTokenLifespan = Duration.ofMinutes(15);

    @Value("${gonote.jwt.refresh-token.lifespan:1d}")
    private Duration refreshTokenLifespan = Duration.ofDays(1);

    public Jwt issue(User user, JwtType type) {
        switch (type) {
            case ACCESS_TOKEN:
                return issueAccessToken(user);
            case REFRESH_TOKEN:
                return issueRefreshToken(user);
            default:
                throw new IllegalArgumentException("Invalid JWT type: " + type);
        }
    }

    public Jwt decode(String token) {
        try {
            return jwtDecoder.decode(token);
        } catch (Exception e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    public UUID getCurrentUserUuid() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        return UUID.fromString(authentication.getToken().getSubject());
    }

    public boolean isEmailVerified() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        return (boolean) authentication.getToken().getClaim("email_verified");
    }

    private Jwt issueAccessToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plus(accessTokenLifespan);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("email_verified", user.isEmailVerified())
                .claim("name", user.getName())
                .claim("picture", user.getAvatarUrl())
                .claim("type", "access")
                .issuedAt(now)
                .expiresAt(expiration)
                .id(UUID.randomUUID().toString())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    private Jwt issueRefreshToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plus(refreshTokenLifespan);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getId().toString())
                .claim("type", "refresh")
                .issuedAt(now)
                .expiresAt(expiration)
                .id(UUID.randomUUID().toString())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

}
