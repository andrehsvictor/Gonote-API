package andrehsvictor.gonote.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutUserDto {

    @Size(min = 3, max = 255, message = "Name must have between 3 and 255 characters")
    private String name;

    @Size(min = 5, max = 255, message = "Email must have between 5 and 255 characters")
    @Email(message = "Invalid email")
    private String email;

    @Size(min = 11, max = 255, message = "Avatar URL must have between 11 and 255 characters")
    @Pattern(message = "Invalid URL", regexp = "^(http|https)://.*$")
    private String avatarUrl;

}
