package io.github.jaoxavier.myOwnEcormmece.exception.client;

public class EmailAlreadyCreatedException extends RuntimeException {

    public EmailAlreadyCreatedException(String message) {
        super(message);
    }

    public EmailAlreadyCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
