package andrehsvictor.gonote.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import andrehsvictor.gonote.exception.ErrorsDto;
import andrehsvictor.gonote.exception.ResourceConflictException;
import andrehsvictor.gonote.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorsDto<String>> handleAllExceptions(Exception ex) {
        log.error("Error: ", ex);
        return ResponseEntity.internalServerError().body(ErrorsDto.of("An internal error occurred"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorsDto<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(ErrorsDto.of(ex.getMessage()));
    }

    @ExceptionHandler(ResourceConflictException.class)
    public final ResponseEntity<ErrorsDto<String>> handleResourceConflictException(ResourceConflictException ex) {
        return ResponseEntity.status(409).body(ErrorsDto.of(ex.getMessage()));
    }
}
