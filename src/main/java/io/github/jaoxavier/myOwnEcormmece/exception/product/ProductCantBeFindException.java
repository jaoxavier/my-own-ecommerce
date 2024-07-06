package io.github.jaoxavier.myOwnEcormmece.exception.product;

public class ProductCantBeFindException extends RuntimeException{
    public ProductCantBeFindException(String message) {
        super(message);
    }

    public ProductCantBeFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
