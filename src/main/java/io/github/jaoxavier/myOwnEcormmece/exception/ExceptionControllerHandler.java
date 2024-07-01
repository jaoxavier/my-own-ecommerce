package io.github.jaoxavier.myOwnEcormmece.exception;

import io.github.jaoxavier.myOwnEcormmece.exception.client.EmailAlreadyCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseBody
public class ExceptionControllerHandler {

    @ExceptionHandler(EmailAlreadyCreatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEmailIsAlreadyCreated(EmailAlreadyCreatedException ex){
        return createResponse(HttpStatus.CONFLICT, ex);
    }

    private ErrorResponse createResponse(HttpStatus status, RuntimeException ex){
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                ex.getStackTrace()[0].toString()
        );
    }
}
