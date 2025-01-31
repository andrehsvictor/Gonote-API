package andrehsvictor.gonote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class ResourceNotFountException extends RuntimeException {

    private static final long serialVersionUID = -3801227101481849920L;

    public ResourceNotFountException(String message) {
        super(message);
    }

    public ResourceNotFountException(Class<?> clazz, String field, Object value) {
        super(String.format("%s not found with %s: '%s'", clazz.getSimpleName(), field, value));
    }

}
