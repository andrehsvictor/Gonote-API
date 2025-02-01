package andrehsvictor.gonote.user;

import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import andrehsvictor.gonote.exception.ResourceConflictException;
import andrehsvictor.gonote.exception.ResourceNotFoundException;
import andrehsvictor.gonote.jwt.JwtService;
import andrehsvictor.gonote.user.dto.GetUserDto;
import andrehsvictor.gonote.user.dto.PostUserDto;
import andrehsvictor.gonote.user.dto.PutUserDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public GetUserDto toGetUserDto(User user) {
        return userMapper.userToGetUserDto(user);
    }

    public User create(PostUserDto postUserDto) {
        if (existsByEmail(postUserDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        User user = userMapper.postUserDtoToUser(postUserDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(PutUserDto putUserDto) {
        if (existsByEmail(putUserDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }
        User user = getById(getCurrentUserId());
        userMapper.updateUserFromPutUserDto(putUserDto, user);
        return userRepository.save(user);
    }

    public User getCurrentUser() {
        return getById(jwtService.getCurrentUserUuid());
    }

    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class, "ID", id));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, "email", email));
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
