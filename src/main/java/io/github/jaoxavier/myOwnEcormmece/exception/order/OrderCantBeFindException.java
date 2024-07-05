package io.github.jaoxavier.myOwnEcormmece.exception.order;

public class OrderCantBeFindException extends RuntimeException{
    public OrderCantBeFindException(String message) {
        super(message);
    }

    public OrderCantBeFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
