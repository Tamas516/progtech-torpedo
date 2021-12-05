package hu.nye.torpedo.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.ui.BoardPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class PrintCommandTest {

    private static final String PRINT_COMMAND = "print";
    private static final String NOT_PRINT_COMMAND = "not-print";

    private static final PlayerBoard PLAYER_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard PLAYER_BOARD1 = new PlayerBoard(0, 0, null);
    private static final PlayerBoard PLAYER_BOARD2 = new PlayerBoard(0, 0, null);
    private static final PlayerBoard PLAYER_BOARD3 = new PlayerBoard(0, 0, null);


    private GameState gameState;
    @Mock
    private BoardPrinter boardPrinter;

    private PrintCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(PLAYER_BOARD, PLAYER_BOARD1, PLAYER_BOARD2, PLAYER_BOARD3, false);
        underTest = new PrintCommand(gameState, boardPrinter);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheGivenCommandIsPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(PRINT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheGivenCommandIsNotPrint() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_PRINT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessShouldPrintTheCurrentBoardsFromGameState() {
        // given in setup

        // when
        underTest.process(PRINT_COMMAND);

        // then
        verify(boardPrinter,times(4)).printBoard(PLAYER_BOARD);

    }

}
