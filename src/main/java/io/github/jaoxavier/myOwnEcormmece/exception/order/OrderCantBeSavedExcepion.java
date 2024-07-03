package io.github.jaoxavier.myOwnEcormmece.exception.order;

public class OrderCantBeSavedExcepion extends RuntimeException {
    public OrderCantBeSavedExcepion(String message) {
        super(message);
    }

    public OrderCantBeSavedExcepion(String message, Throwable cause) {
        super(message, cause);
    }
}
