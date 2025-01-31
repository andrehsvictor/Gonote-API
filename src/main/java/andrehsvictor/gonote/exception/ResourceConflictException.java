package andrehsvictor.gonote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Resource already exists")
public class ResourceConflictException extends RuntimeException {

    private static final long serialVersionUID = 4870656394958764078L;

    public ResourceConflictException(String message) {
        super(message);
    }

}
