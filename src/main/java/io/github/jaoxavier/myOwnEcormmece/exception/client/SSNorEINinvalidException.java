package io.github.jaoxavier.myOwnEcormmece.exception.client;

public class SSNorEINinvalidException extends RuntimeException{
    public SSNorEINinvalidException(String message) {
        super(message);
    }

    public SSNorEINinvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
