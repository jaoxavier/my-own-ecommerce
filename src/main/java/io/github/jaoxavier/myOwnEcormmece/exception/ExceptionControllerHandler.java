package io.github.jaoxavier.myOwnEcormmece.exception;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.exception.address.AddressDoesntExistsException;
import io.github.jaoxavier.myOwnEcormmece.exception.client.*;
import io.github.jaoxavier.myOwnEcormmece.exception.order.OrderCantBeFindException;
import io.github.jaoxavier.myOwnEcormmece.exception.order.OrderCantBeSavedExcepion;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductCantBeFindException;
import io.github.jaoxavier.myOwnEcormmece.exception.product.ProductHasInsufficientStock;
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

    @ExceptionHandler(ClientCantBeSaved.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleClientCantBeSaved(ClientCantBeSaved ex){
        return createResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(OrderCantBeFindException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleOrderCantBeFindException(OrderCantBeFindException ex){
        return createResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ClientIsNotCompany.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleClientIsNotCompany(ClientIsNotCompany ex){
        return createResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(ProductCantBeFindException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductCantBeFindException(ProductCantBeFindException ex){
        return createResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ProductHasInsufficientStock.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductHasInsufficientStock(ProductHasInsufficientStock ex){
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
