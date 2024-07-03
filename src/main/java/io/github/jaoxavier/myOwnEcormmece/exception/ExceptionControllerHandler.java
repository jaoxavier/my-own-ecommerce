package io.github.jaoxavier.myOwnEcormmece.exception;

import io.github.jaoxavier.myOwnEcormmece.exception.address.AddressDoesntExistsException;
import io.github.jaoxavier.myOwnEcormmece.exception.client.ClientDoesntExistsException;
import io.github.jaoxavier.myOwnEcormmece.exception.client.EmailAlreadyCreatedException;
import io.github.jaoxavier.myOwnEcormmece.exception.client.SSNorEINinvalidException;
import io.github.jaoxavier.myOwnEcormmece.exception.order.OrderCantBeSavedExcepion;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(ClientDoesntExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleClientDoesntExists(ClientDoesntExistsException ex){
        return createResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(AddressDoesntExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAddressDoesntExists(AddressDoesntExistsException ex){
        return createResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(SSNorEINinvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleSSNorEINinvalidException(SSNorEINinvalidException ex){
        return createResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(OrderCantBeSavedExcepion.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOrderCantBeSavedExcepion(OrderCantBeSavedExcepion ex){
        return createResponse(HttpStatus.BAD_REQUEST, ex);
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
