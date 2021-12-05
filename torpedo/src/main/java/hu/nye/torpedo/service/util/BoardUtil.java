package hu.nye.torpedo.service.util;

import java.util.ArrayList;
import java.util.List;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.model.PlayerBoard;

/**
 * Util class that helps to extract given parts of a {@link PlayerBoard} object.
 */

public class BoardUtil {

    /**
     * Returns all the numbers from a chosen row as a list.
     *
     * @param playerBoard the map
     * @param rowIndex the index of the chosen row
     * @return a list of integers
     */
    public List<String> getRowOfBoard(PlayerBoard playerBoard, int rowIndex) {
        List<String> result = new ArrayList<>();

        String[][] board = playerBoard.getBoard();
        for (int i = 0; i < playerBoard.getNumberOfColumns(); i++) {
            result.add(board[rowIndex][i]);
        }

        return result;
    }


    /**
     * Returns all the numbers from a chosen column as a list.
     *
     * @param playerBoard       the map
     * @param columnIndex the index of the column
     * @return a list of integers
     */
    public List<String> getColumnOfBoard(PlayerBoard playerBoard, int columnIndex) {
        List<String> result = new ArrayList<>();

        String[][] board = playerBoard.getBoard();
        for (int i = 0; i < playerBoard.getNumberOfRows(); i++) {
            result.add(board[i][columnIndex]);
        }

        return result;
    }

    /**
     * Determines if the given map is completed or not.
     *
     * A map is considered as completed, if there are no more
     * zeros left in it.
     *
     * @param playerBoard the map to check
     * @return {@code true} if the map is completed, {@code false} otherwise
     */
    public boolean isBoardCompleted(PlayerBoard playerBoard, PlayerBoard playerBoard1) {
        boolean result = false;

        String[][] board = playerBoard.getBoard();
        String[][] board1 = playerBoard1.getBoard();

        int boardPlus = 0;
        int board1Plus = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals("+")) {
                    boardPlus++;
                }
                if (board1[i][j].equals("+")) {
                    board1Plus++;
                }
            }
        }
        if (boardPlus == board1Plus) {
            result = true;
        }

        return result;
    }


}
