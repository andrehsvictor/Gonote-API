package andrehsvictor.gonote.user;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import andrehsvictor.gonote.exception.ResourceConflictException;
import andrehsvictor.gonote.exception.ResourceNotFoundException;
import andrehsvictor.gonote.user.dto.CreateUserDto;
import andrehsvictor.gonote.user.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User create(CreateUserDto createUserDto) {
        if (existsByEmail(createUserDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        User user = userMapper.createUserDtoToUser(createUserDto);
        return userRepository.save(user);
    }

    public User update(UpdateUserDto updateUserDto) {
        if (existsByEmail(updateUserDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        User user = findById(getCurrentUserId());
        userMapper.updateUserFromUpdateUserDto(updateUserDto, user);
        return userRepository.save(user);
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, "ID", id));
    }

    public void delete() {
        userRepository.deleteById(getCurrentUserId());
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    public UUID getCurrentUserId() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        return UUID.fromString(authentication.getToken().getSubject());
    }
}
