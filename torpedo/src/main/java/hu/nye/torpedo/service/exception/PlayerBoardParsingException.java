package hu.nye.torpedo.service.exception;

/**
 * Exception that should be thrown when the parsing of a map fails.
 */

public class PlayerBoardParsingException extends Exception {

    public PlayerBoardParsingException(String message) {
        super(message);
    }
}
