package andrehsvictor.gonote.refreshtoken;

import java.time.Duration;
import java.time.Instant;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final StringRedisTemplate redisTemplate;

    private static final String PREFIX = "refresh_token:";

    public void store(Jwt jwt) {
        Duration duration = Duration.ofSeconds(jwt.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond());
        redisTemplate.opsForValue().set(PREFIX + jwt.getId(), jwt.getTokenValue(), duration);
    }

    public boolean exists(String id) {
        return redisTemplate.hasKey(PREFIX + id);
    }
}
