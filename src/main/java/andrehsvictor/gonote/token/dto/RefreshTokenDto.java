package andrehsvictor.gonote.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDto {

    @JsonProperty("refresh_token")
    private String refreshToken;
}
