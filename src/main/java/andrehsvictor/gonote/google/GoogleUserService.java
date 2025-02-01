package andrehsvictor.gonote.google;

import org.springframework.stereotype.Service;

import andrehsvictor.gonote.user.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleUserService {

    private final GoogleMapper googleMapper;

    public User idTokenToUser(String idToken) {
        return googleMapper.idTokenToUser(idToken);
    }
    
}
