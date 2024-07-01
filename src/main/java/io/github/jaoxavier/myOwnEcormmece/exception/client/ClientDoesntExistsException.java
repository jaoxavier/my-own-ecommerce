package io.github.jaoxavier.myOwnEcormmece.exception.client;

public class ClientDoesntExistsException extends RuntimeException {
    public ClientDoesntExistsException(String message) {
        super(message);
    }

    public ClientDoesntExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
