package hu.nye.torpedo.model;

import java.util.List;
import java.util.Objects;

/**
 * Raw representation of a Sudoku map.
 */
public class RawBoard {

    private String board;

    public RawBoard(String board) {
        this.board = board;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RawBoard rawBoard = (RawBoard) o;
        return board.equals(rawBoard.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    @Override
    public String toString() {
        return "RawBoard{" +
                "board='" + board + '\'' +
                '}';
    }

}
