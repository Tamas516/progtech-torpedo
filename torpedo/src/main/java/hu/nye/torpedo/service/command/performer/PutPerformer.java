package hu.nye.torpedo.service.command.performer;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.exception.PutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class used to write a number to a given position of a map.
 */

public class PutPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PutPerformer.class);

    /**
     * Writes a number to a given position into the provided map.
     *
     * A write can only be performed, if there is no fixed number at
     * the requested position.
     *
     * @param playerBoard       the map to update
     * @param rowIndex    the index of the row
     * @param columnIndex the index of the column
     * @param ch      the character to write into the map
     */

    public PlayerBoard perform(PlayerBoard playerBoard, int rowIndex, int columnIndex, String ch) throws PutException {

        LOGGER.info("Performing put operation with playerBoard = {}, rowIndex = {}, columnIndex = {}, ch ={}",
                playerBoard, rowIndex, columnIndex, ch);

        String[][] board = playerBoard.getBoard();

        if (board[rowIndex][columnIndex].equals("+") || board[rowIndex][columnIndex].equals("X")) {
            LOGGER.warn("Can't perform put operation, as position at rowIndex = {} and columnIndex = {} is fixed",
                    rowIndex, columnIndex);
            throw new PutException("Can't perform put on a fixed position");
        }

        board[rowIndex][columnIndex] = ch;

        return new PlayerBoard(playerBoard.getNumberOfRows(), playerBoard.getNumberOfColumns(), board);
    }


}
