package hu.nye.torpedo.service.command.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.command.performer.PutPerformer;
import hu.nye.torpedo.service.exception.PutException;
import hu.nye.torpedo.ui.BoardPrinter;
import hu.nye.torpedo.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PutCommandTest {

    private static final String PUT_COMMAND = "put 1 1";
    private static final String NOT_PUT_COMMAND = "not-put";

    private static final int ROW_INDEX = 1;
    private static final int COLUMN_INDEX = 1;
    private static final String CHARACTER = "+";

    private static final PlayerBoard PLAYER_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard OPPONENT_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard PLAYER_EMPTY_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard OPPONENT_EMPTY_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard NEW_PLAYER_EMPTY_BOARD = new PlayerBoard(0, 0, null);

    private static final String PUT_ERROR_MESSAGE = "Can't write to a fixed position";

    private GameState gameState;
    @Mock
    private PutPerformer putPerformer;

    @Mock
    private BoardPrinter boardPrinter;
    @Mock
    private PrintWrapper printWrapper;

    private PutCommand underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(PLAYER_BOARD, OPPONENT_BOARD, PLAYER_EMPTY_BOARD, OPPONENT_EMPTY_BOARD, false);
        underTest = new PutCommand(gameState, putPerformer, boardPrinter, printWrapper);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheGivenInputIsValidPutCommand() {
        // given in setup

        // when
        boolean result = underTest.canProcess(PUT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheGivenInputIsNotValidPutCommand() {
        // given in setup

        // when
        boolean result = underTest.canProcess(NOT_PUT_COMMAND);

        // then
        assertFalse(result);
    }

//    @Test
//    public void testProcessShouldNotUpdateGameStateWhenPutPerformingFails() throws PutException {
//        // given
//        doThrow(PutException.class).when(putPerformer).perform(PLAYER_BOARD, ROW_INDEX, COLUMN_INDEX, CHARACTER);
//
//        // when
//        underTest.process(PUT_COMMAND);
//
//        // then
//        verify(putPerformer).perform(PLAYER_BOARD, ROW_INDEX, COLUMN_INDEX, CHARACTER);
//        assertEquals(PLAYER_BOARD, gameState.getCurrentBoard());
//        verifyNoInteractions(boardPrinter);
//        verify(printWrapper).printLine(PUT_ERROR_MESSAGE);
//    }


}
