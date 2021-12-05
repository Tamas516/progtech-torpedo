package hu.nye.torpedo.service.exception;

/**
 * Exception that should be thrown when the reading of a map fails.
 */

public class PlayerBoardReadingException extends Exception {

    public PlayerBoardReadingException(String message) {
        super(message);
    }
}
