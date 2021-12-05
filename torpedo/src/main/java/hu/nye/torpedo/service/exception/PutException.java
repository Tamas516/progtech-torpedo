package hu.nye.torpedo.service.exception;

/**
 * Exception that should be thrown when a put operation fails.
 */

public class PutException extends Exception {

    public PutException(String message) {
        super(message);
    }
}
