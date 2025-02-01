package andrehsvictor.gonote.google;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Value;

@Configuration
public class GoogleConfig {

    @Value("${gonote.google.client-id}")
    private String clientId;

    @Value("${gonote.google.client-secret}")
    private String clientSecret;

    @Bean
    GoogleIdTokenVerifier googleIdTokenVerifier() {
        try {
            HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            return new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(clientId))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
