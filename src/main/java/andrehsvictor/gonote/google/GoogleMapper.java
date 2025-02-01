package andrehsvictor.gonote.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import andrehsvictor.gonote.user.User;
import andrehsvictor.gonote.user.UserProvider;

@Mapper(componentModel = "spring")
public abstract class GoogleMapper {

    @Autowired
    protected GoogleIdTokenVerifier googleIdTokenVerifier;

    public User idTokenToUser(String idToken) {
        try {
            IdToken.Payload payload = googleIdTokenVerifier.verify(idToken).getPayload();
            User user = new User();
            user.setEmail((String) payload.get("email"));
            user.setName((String) payload.get("name"));
            user.setAvatarUrl((String) payload.get("picture"));
            user.setEmailVerified((Boolean) payload.get("email_verified"));
            user.setProvider(UserProvider.GOOGLE);
            return user;
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
