package hu.nye.torpedo.model;

import java.util.Objects;

/**
 * Represents the current state of the game.
 */

public class GameState {

    private PlayerBoard currentBoard;
    private PlayerBoard currentBoard1;
    private PlayerBoard currentBoard2;
    private PlayerBoard currentBoard3;

    private boolean shouldExit;

    public GameState(PlayerBoard currentBoard, PlayerBoard currentBoard1, PlayerBoard currentBoard2,
                     PlayerBoard currentBoard3, boolean shouldExit) {
        this.currentBoard = currentBoard;
        this.currentBoard1 = currentBoard1;
        this.currentBoard2 = currentBoard2;
        this.currentBoard3 = currentBoard3;
        this.shouldExit = shouldExit;
    }

    public PlayerBoard getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(PlayerBoard currentBoard) {
        this.currentBoard = currentBoard;
    }

    public PlayerBoard getCurrentBoard1() {
        return currentBoard1;
    }

    public void setCurrentBoard1(PlayerBoard currentBoard1) {
        this.currentBoard1 = currentBoard1;
    }

    public PlayerBoard getCurrentBoard2() {
        return currentBoard2;
    }

    public void setCurrentBoard2(PlayerBoard currentBoard2) {
        this.currentBoard2 = currentBoard2;
    }

    public PlayerBoard getCurrentBoard3() {
        return currentBoard3;
    }

    public void setCurrentBoard3(PlayerBoard currentBoard3) {
        this.currentBoard3 = currentBoard3;
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
        return shouldExit == gameState.shouldExit && Objects.equals(currentBoard, gameState.currentBoard)
                && Objects.equals(currentBoard1, gameState.currentBoard1)
                && Objects.equals(currentBoard2, gameState.currentBoard2)
                && Objects.equals(currentBoard3, gameState.currentBoard3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentBoard, currentBoard1, currentBoard2, currentBoard3, shouldExit);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "currentBoard=" + currentBoard +
                ", currentBoard1=" + currentBoard1 +
                ", currentBoard2=" + currentBoard2 +
                ", currentBoard3=" + currentBoard3 +
                ", shouldExit=" + shouldExit +
                '}';
    }
}
