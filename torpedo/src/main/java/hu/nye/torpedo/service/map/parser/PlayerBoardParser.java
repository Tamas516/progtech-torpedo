package hu.nye.torpedo.service.map.parser;

import java.util.List;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.exception.PlayerBoardParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses a raw representation of a map into a {@link PlayerBoard} object.
 */

public class PlayerBoardParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerBoardParser.class);

    private static int numberOfRows;
    private static int numberOfColumns;


    public PlayerBoardParser(int numberOfRows, int numberOfColumns) {
        PlayerBoardParser.numberOfRows = numberOfRows;
        PlayerBoardParser.numberOfColumns = numberOfColumns;
    }

    /**
     * Parses a map from a raw representation.
     *
     */

    public static PlayerBoard parseBoard(List<String> rows) throws PlayerBoardParsingException {
        LOGGER.info("Parsing the List = {}", rows);

        checkNumberOfRows(rows);
        checkNumberOfColumns(rows);

        String[][] board = getBoard(rows);
        return new PlayerBoard(numberOfRows, numberOfColumns, board);
    }

    private static void checkNumberOfRows(List<String> rows) throws PlayerBoardParsingException {
        if (rows.size() != numberOfRows) {
            throw new PlayerBoardParsingException("Number of rows must be " + numberOfRows);
        }
    }

    private static void checkNumberOfColumns(List<String> rows) throws PlayerBoardParsingException {
        for (String row : rows) {
            if (row.length() != numberOfColumns) {
                throw new PlayerBoardParsingException("Number of columns must be " + numberOfColumns);
            }
        }
    }


    private static String[][] getBoard(List<String> rows) {
        String[][] board = new String[numberOfRows][numberOfColumns];

        for (int x = 0; x < numberOfRows; x++) {
            String row = rows.get(x);
            String[] numbersAsString = row.split("");
            for (int y = 0; y < numberOfColumns; y++) {
                String n = numbersAsString[y];
                board[x][y] = n;
            }
        }

        return board;
    }


}
