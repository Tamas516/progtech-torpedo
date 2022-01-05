package hu.nye.torpedo.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Model class used to represent a Torpedo board.
 */
public class PlayerBoard {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final String[][] board;

    public PlayerBoard(int numberOfRows, int numberOfColumns, String[][] board) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.board = deepCopy(board);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public String[][] getBoard() {
        return deepCopy(board);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerBoard playerBoard1 = (PlayerBoard) o;
        return numberOfRows == playerBoard1.numberOfRows && numberOfColumns == playerBoard1.numberOfColumns &&
                Arrays.deepEquals(board, playerBoard1.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public String toString() {
        return "Board{" +
                "numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns +
                ", board=" + Arrays.deepToString(board) +
                '}';
    }

    private String[][] deepCopy(String[][] array) {
        String[][] result = null;

        if (array != null) {
            result = new String[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }

}
