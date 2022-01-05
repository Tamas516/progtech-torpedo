package hu.nye.torpedo.model;

import java.util.Objects;

/**
 * Represents the current state of the game.
 */

public class GameState {

    private PlayerBoard currentPlayerBoard;
    private PlayerBoard currentPlayerBoard1;
    private PlayerBoard currentPlayerBoard2;
    private PlayerBoard currentPlayerBoard3;

    private boolean shouldExit;

    public GameState(PlayerBoard currentPlayerBoard, PlayerBoard currentPlayerBoard1, PlayerBoard currentPlayerBoard2,
                     PlayerBoard currentPlayerBoard3, boolean shouldExit) {
        this.currentPlayerBoard = currentPlayerBoard;
        this.currentPlayerBoard1 = currentPlayerBoard1;
        this.currentPlayerBoard2 = currentPlayerBoard2;
        this.currentPlayerBoard3 = currentPlayerBoard3;
        this.shouldExit = shouldExit;
    }

    public PlayerBoard getCurrentBoard() {
        return currentPlayerBoard;
    }

    public void setCurrentBoard(PlayerBoard currentPlayerBoard) {
        this.currentPlayerBoard = currentPlayerBoard;
    }

    public PlayerBoard getCurrentBoard1() {
        return currentPlayerBoard1;
    }

    public void setCurrentBoard1(PlayerBoard currentPlayerBoard1) {
        this.currentPlayerBoard1 = currentPlayerBoard1;
    }

    public PlayerBoard getCurrentBoard2() {
        return currentPlayerBoard2;
    }

    public void setCurrentBoard2(PlayerBoard currentPlayerBoard2) {
        this.currentPlayerBoard2 = currentPlayerBoard2;
    }

    public PlayerBoard getCurrentBoard3() {
        return currentPlayerBoard3;
    }

    public void setCurrentBoard3(PlayerBoard currentPlayerBoard3) {
        this.currentPlayerBoard3 = currentPlayerBoard3;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameState gameState = (GameState) o;
        return shouldExit == gameState.shouldExit && currentPlayerBoard.equals(gameState.currentPlayerBoard)
                && currentPlayerBoard1.equals(gameState.currentPlayerBoard1)
                && currentPlayerBoard2.equals(gameState.currentPlayerBoard2)
                && currentPlayerBoard3.equals(gameState.currentPlayerBoard3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPlayerBoard, currentPlayerBoard1, currentPlayerBoard2, currentPlayerBoard3, shouldExit);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "currentBoard=" + currentPlayerBoard +
                ", currentBoard1=" + currentPlayerBoard1 +
                ", currentBoard2=" + currentPlayerBoard2 +
                ", currentBoard3=" + currentPlayerBoard3 +
                ", shouldExit=" + shouldExit +
                '}';
    }
}
