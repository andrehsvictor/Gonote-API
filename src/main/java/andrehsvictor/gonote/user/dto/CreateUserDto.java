package andrehsvictor.gonote.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    @Size(min = 3, max = 255, message = "Name must have between 3 and 255 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 5, max = 255, message = "Email must have between 5 and 255 characters")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 255, message = "Password must have between 8 and 255 characters")
    @Pattern(message = "Password must have at least one number", regexp = ".*\\d.*")
    private String password;

    @Size(min = 11, max = 255, message = "Avatar URL must have between 11 and 255 characters")
    @Pattern(message = "Invalid URL", regexp = "^(http|https)://.*$")
    private String avatarUrl;

}
