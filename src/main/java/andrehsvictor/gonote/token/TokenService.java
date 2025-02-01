package andrehsvictor.gonote.token;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import andrehsvictor.gonote.auth.AuthService;
import andrehsvictor.gonote.exception.UnauthorizedException;
import andrehsvictor.gonote.jwt.JwtService;
import andrehsvictor.gonote.jwt.JwtType;
import andrehsvictor.gonote.revokedtoken.RevokedTokenService;
import andrehsvictor.gonote.security.impl.UserDetailsImpl;
import andrehsvictor.gonote.token.dto.GetTokenDto;
import andrehsvictor.gonote.token.dto.PostTokenDto;
import andrehsvictor.gonote.token.dto.RefreshTokenDto;
import andrehsvictor.gonote.token.dto.RevokeTokenDto;
import andrehsvictor.gonote.user.User;
import andrehsvictor.gonote.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserService userService;
    private final RevokedTokenService revokedTokenService;

    public GetTokenDto request(PostTokenDto postTokenDto) {
        Authentication authentication = authService.authenticate(postTokenDto.getEmail(), postTokenDto.getPassword());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        return getTokenDto(user);
    }

    public GetTokenDto refresh(RefreshTokenDto refreshTokenDto) {
        Jwt refreshToken = jwtService.decode(refreshTokenDto.getRefreshToken());
        if (!refreshToken.getClaimAsString("type").equals("refresh")) {
            throw new UnauthorizedException("Invalid refresh token");
        }
        revokedTokenService.revoke(refreshToken);
        User user = userService.getById(UUID.fromString(refreshToken.getSubject()));
        return getTokenDto(user);
    }

    public void revoke(RevokeTokenDto revokeTokenDto) {
        Jwt token = jwtService.decode(revokeTokenDto.getToken());
        revokedTokenService.revoke(token);
    }

    private GetTokenDto getTokenDto(User user) {
        Jwt accessToken = jwtService.issue(user, JwtType.ACCESS_TOKEN);
        Jwt refreshToken = jwtService.issue(user, JwtType.REFRESH_TOKEN);
        Long expiresIn = accessToken.getExpiresAt().getEpochSecond() - accessToken.getIssuedAt().getEpochSecond();
        return GetTokenDto.builder()
                .accessToken(accessToken.getTokenValue())
                .refreshToken(refreshToken.getTokenValue())
                .expiresIn(expiresIn)
                .build();
    }
}
