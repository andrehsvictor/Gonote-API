package andrehsvictor.gonote.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorsDto<T> {
    private T errors;

    public static ErrorsDto<String> of(String message) {
        ErrorsDto<String> errorsDto = new ErrorsDto<>();
        errorsDto.setErrors(message);
        return errorsDto;
    }
}
