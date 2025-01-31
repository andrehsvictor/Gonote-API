package andrehsvictor.gonote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends AuthenticationException {

    private static final long serialVersionUID = 717713690424843421L;

    public UnauthorizedException(String msg) {
        super(msg);
    }

}
