package andrehsvictor.gonote.user;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.gonote.user.dto.GetUserDto;
import andrehsvictor.gonote.user.dto.PostUserDto;
import andrehsvictor.gonote.user.dto.PutUserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountResource {

    private final UserService userService;

    @PostMapping("/api/v1/account")
    public ResponseEntity<GetUserDto> create(@Valid @RequestBody PostUserDto postUserDto) {
        User user = userService.create(postUserDto);
        GetUserDto getUserDto = userService.toGetUserDto(user);
        URI location = URI.create("/api/v1/account");
        return ResponseEntity.created(location).body(getUserDto);
    }

    @GetMapping("/api/v1/account")
    public GetUserDto get() {
        User user = userService.getCurrentUser();
        return userService.toGetUserDto(user);
    }

    @PutMapping("/api/v1/account")
    public GetUserDto update(@Valid @RequestBody PutUserDto putUserDto) {
        User user = userService.update(putUserDto);
        return userService.toGetUserDto(user);
    }

    @DeleteMapping("/api/v1/account")
    public ResponseEntity<Void> delete() {
        userService.delete();
        return ResponseEntity.noContent().build();
    }
}
