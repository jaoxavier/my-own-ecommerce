package io.github.jaoxavier.myOwnEcormmece.exception.client;

public class ClientCantBeSaved extends RuntimeException {
    public ClientCantBeSaved(String message) {
        super(message);
    }

    public ClientCantBeSaved(String message, Throwable cause) {
        super(message, cause);
    }
}