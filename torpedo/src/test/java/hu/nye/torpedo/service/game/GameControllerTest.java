package hu.nye.torpedo.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.util.BoardUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private static final PlayerBoard PLAYER_PLAYER_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard OPPONENT_PLAYER_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard PLAYER_EMPTY_PLAYER_BOARD = new PlayerBoard(0, 0, null);
    private static final PlayerBoard OPPONENT_EMPTY_PLAYER_BOARD = new PlayerBoard(0, 0, null);

    private GameState gameState;
    @Mock
    private GameStepPerformer gameStepPerformer;
    @Mock
    private BoardUtil boardUtil;

    private GameController underTest;

    @Test
    public void testStartShouldLoopTheGameUntilTheUserDoesNotForceExit() {
        // given
        gameState = new GameState(null, null, null, null, true);
        underTest = new GameController(gameState, gameStepPerformer, boardUtil);

        // when
        underTest.start();

        // then
        verifyNoInteractions(gameStepPerformer);
    }

    @Test
    public void testStartShouldLoopTheGameUntilTheMapIsNotCompleted() {
        // given
        gameState = new GameState(PLAYER_PLAYER_BOARD, OPPONENT_PLAYER_BOARD, PLAYER_EMPTY_PLAYER_BOARD, OPPONENT_EMPTY_PLAYER_BOARD, false);
        underTest = new GameController(gameState, gameStepPerformer, boardUtil);
        given(boardUtil.isBoardCompleted(PLAYER_PLAYER_BOARD, PLAYER_EMPTY_PLAYER_BOARD)).willReturn(false, true);
        given(boardUtil.isBoardCompleted(OPPONENT_PLAYER_BOARD, OPPONENT_EMPTY_PLAYER_BOARD)).willReturn(false, true);

        // when
        underTest.start();

        // then
        verify(boardUtil, times(2)).isBoardCompleted(PLAYER_PLAYER_BOARD, PLAYER_EMPTY_PLAYER_BOARD);
        verify(boardUtil, times(2)).isBoardCompleted(OPPONENT_PLAYER_BOARD, OPPONENT_EMPTY_PLAYER_BOARD);
//        verify(gameStepPerformer, times(1)).performGameStep();
    }


}
