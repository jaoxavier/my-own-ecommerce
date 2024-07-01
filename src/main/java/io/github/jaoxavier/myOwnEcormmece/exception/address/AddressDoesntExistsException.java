package io.github.jaoxavier.myOwnEcormmece.exception.address;

public class AddressDoesntExistsException extends RuntimeException {
    public AddressDoesntExistsException(String message) {
        super(message);
    }

    public AddressDoesntExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
