package hu.nye.torpedo.service.command.impl;

import java.util.regex.Pattern;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.command.Command;
import hu.nye.torpedo.service.command.performer.PutPerformer;
import hu.nye.torpedo.service.exception.PutException;
import hu.nye.torpedo.ui.BoardPrinter;
import hu.nye.torpedo.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to write a number to a given field of the map.
 */


public class PutCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(PutCommand.class);

    private static final String PUT_COMMAND_REGEX = "^put [0-9] [0-9]$";
    private static final String PUT_ERROR_MESSAGE = "Invalid";

    private final GameState gameState;
    private final PutPerformer putPerformer;
    private final BoardPrinter boardPrinter;
    private final PrintWrapper printWrapper;

    public PutCommand(GameState gameState, PutPerformer putPerformer,
                      BoardPrinter boardPrinter, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.putPerformer = putPerformer;
        this.boardPrinter = boardPrinter;
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return Pattern.matches(PUT_COMMAND_REGEX, input);
    }

    @Override
    public void process(String input) {
        String[] parts = input.split(" ");
        int rowIndex = Integer.parseInt(parts[1]);
        int columnIndex = Integer.parseInt(parts[2]);
        PlayerBoard playerBoard = gameState.getCurrentBoard();
        PlayerBoard opponentPlayerBoard = gameState.getCurrentBoard1();

        String[][] fixed1 = playerBoard.getBoard();
        String[][] fixed = opponentPlayerBoard.getBoard();

        LOGGER.info("Performing put command with rowIndex = {}, columnIndex = {}", rowIndex, columnIndex);

        try {
            if (fixed1[rowIndex][columnIndex].equals("0")) {
                String ch = "X";
                PlayerBoard newPlayerBoard = putPerformer.perform(gameState.getCurrentBoard2(), rowIndex, columnIndex, ch);
                gameState.setCurrentBoard2(newPlayerBoard);
                System.out.println("Player Empty Board");
                boardPrinter.printBoard(newPlayerBoard);
            } else if (fixed1[rowIndex][columnIndex].equals("+")) {
                String ch = "+";
                PlayerBoard newPlayerBoard = putPerformer.perform(gameState.getCurrentBoard2(), rowIndex, columnIndex, ch);
                gameState.setCurrentBoard2(newPlayerBoard);
                System.out.println("Player Empty Board");
                boardPrinter.printBoard(newPlayerBoard);
            }
        } catch (PutException e) {
            LOGGER.error("Exception occurred while performing put operation", e);
                printWrapper.printLine(PUT_ERROR_MESSAGE);
        }

        try {
            if (fixed[rowIndex][columnIndex].equals("0")) {
                String ch = "X";
                PlayerBoard newPlayerBoard1 = putPerformer.perform(gameState.getCurrentBoard3(), rowIndex, columnIndex, ch);
                gameState.setCurrentBoard3(newPlayerBoard1);
                System.out.println("Opponent Empty Board");
                boardPrinter.printBoard(newPlayerBoard1);
            } else if (fixed[rowIndex][columnIndex].equals("+")) {
                String ch = "+";
                PlayerBoard newPlayerBoard1 = putPerformer.perform(gameState.getCurrentBoard3(), rowIndex, columnIndex, ch);
                gameState.setCurrentBoard3(newPlayerBoard1);
                System.out.println("Opponent Empty Board");
                boardPrinter.printBoard(newPlayerBoard1);
            }
        } catch (PutException e) {
            LOGGER.error("Exception occurred while performing put operation", e);
                printWrapper.printLine(PUT_ERROR_MESSAGE);
        }

    }


}
