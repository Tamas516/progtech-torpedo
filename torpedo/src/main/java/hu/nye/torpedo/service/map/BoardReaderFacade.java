package hu.nye.torpedo.service.map;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.model.RawBoard;
import hu.nye.torpedo.service.exception.PlayerBoardParsingException;
import hu.nye.torpedo.service.exception.PlayerBoardReadingException;
import hu.nye.torpedo.service.map.parser.PlayerBoardParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Facade that makes it easier to read a map.
 */

public class BoardReaderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardReaderFacade.class);

    private final BoardReader boardReader;
    private final PlayerBoardParser playerBoardParser;

    public BoardReaderFacade(BoardReader boardReader, PlayerBoardParser playerBoardParser) {
        this.boardReader = boardReader;
        this.playerBoardParser = playerBoardParser;
    }

    /**
     * Reads a map.
     *
     * This method hides the low level operations of reading a map, like reading it
     * in a raw format, parsing the map then also running a validation on it.
     *
     * @return a parsed map as a {@link PlayerBoard} object
     */
    public PlayerBoard readBoard() {
        try {
            RawBoard rawBoard = boardReader.readBoard();
            PlayerBoard playerBoard = playerBoardParser.parseBoard(rawBoard);

            return playerBoard;
        } catch (PlayerBoardReadingException e) {
            LOGGER.error("Failed to read map", e);
            throw new RuntimeException("Failed to read map");
        } catch (PlayerBoardParsingException e) {
            LOGGER.error("Failed to parse map", e);
            throw new RuntimeException("Failed to parse map");
        }
    }

}
