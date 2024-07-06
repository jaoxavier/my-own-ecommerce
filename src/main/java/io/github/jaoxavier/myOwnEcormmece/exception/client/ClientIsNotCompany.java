package io.github.jaoxavier.myOwnEcormmece.exception.client;

public class ClientIsNotCompany extends RuntimeException {
    public ClientIsNotCompany(String message) {
        super(message);
    }

    public ClientIsNotCompany(String message, Throwable cause) {
        super(message, cause);
    }
}
