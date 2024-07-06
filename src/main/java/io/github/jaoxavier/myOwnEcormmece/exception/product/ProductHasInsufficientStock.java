package io.github.jaoxavier.myOwnEcormmece.exception.product;

import jakarta.validation.constraints.NotNull;

public class ProductHasInsufficientStock extends RuntimeException {
    public ProductHasInsufficientStock(String message) {
        super(message);
    }

    public ProductHasInsufficientStock(String message, Throwable cause) {
        super(message, cause);
    }
}
