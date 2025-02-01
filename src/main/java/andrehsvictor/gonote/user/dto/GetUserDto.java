package andrehsvictor.gonote.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDto {
    private String id;
    private String name;
    private String email;
    private boolean emailVerified;
    private String provider;
    private String avatarUrl;
    private String createdAt;
    private String updatedAt;
}
