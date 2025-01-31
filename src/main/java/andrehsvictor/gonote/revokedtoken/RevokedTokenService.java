package andrehsvictor.gonote.revokedtoken;

import java.time.Duration;
import java.time.Instant;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevokedTokenService {

    private final StringRedisTemplate redisTemplate;

    private static final String PREFIX = "revoked_token:";

    public void revoke(Jwt jwt) {
        Duration duration = Duration.ofSeconds(jwt.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond());
        redisTemplate.opsForValue().set(PREFIX + jwt.getId(), "", duration);
    }

    public boolean isRevoked(Jwt jwt) {
        return redisTemplate.hasKey(PREFIX + jwt.getId());
    }
}
