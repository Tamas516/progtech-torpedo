package hu.nye.torpedo.service.command.impl;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.service.command.Command;
import hu.nye.torpedo.ui.BoardPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to request the printing of the current state
 * of the game map.
 */

public class PrintCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);

    private static final String PRINT_COMMAND = "print";

    private final GameState gameState;
    private final BoardPrinter boardPrinter;

    public PrintCommand(GameState gameState, BoardPrinter boardPrinter) {
        this.gameState = gameState;
        this.boardPrinter = boardPrinter;
    }

    @Override
    public boolean canProcess(String input) {
        return PRINT_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.info("Performing print command");
        System.out.println("Player Board");
        boardPrinter.printBoard(gameState.getCurrentBoard());
        System.out.println("Opponent Board");
        boardPrinter.printBoard(gameState.getCurrentBoard1());
        System.out.println("Player Empty Board");
        boardPrinter.printBoard(gameState.getCurrentBoard2());
        System.out.println("Opponent Empty Board");
        boardPrinter.printBoard(gameState.getCurrentBoard3());
    }
}
