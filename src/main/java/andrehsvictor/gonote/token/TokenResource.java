package andrehsvictor.gonote.token;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.gonote.token.dto.GetTokenDto;
import andrehsvictor.gonote.token.dto.PostTokenDto;
import andrehsvictor.gonote.token.dto.RefreshTokenDto;
import andrehsvictor.gonote.token.dto.RevokeTokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TokenResource {

    private final TokenService tokenService;

    @PostMapping("/api/v1/auth/token")
    public GetTokenDto request(@Valid @RequestBody PostTokenDto postTokenDto) {
        return tokenService.request(postTokenDto);
    }

    @PostMapping("/api/v1/auth/token/refresh")
    public GetTokenDto refresh(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        return tokenService.refresh(refreshTokenDto);
    }

    @PostMapping("/api/v1/auth/token/revoke")
    public ResponseEntity<Void> revoke(@Valid @RequestBody RevokeTokenDto revokeTokenDto) {
        tokenService.revoke(revokeTokenDto);
        return ResponseEntity.noContent().build();
    }
}
