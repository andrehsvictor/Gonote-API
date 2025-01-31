package andrehsvictor.gonote.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String provider;
    private String avatarUrl;
    private String createdAt;
    private String updatedAt;
}
