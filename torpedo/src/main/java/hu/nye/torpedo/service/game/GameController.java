package hu.nye.torpedo.service.game;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.service.util.BoardUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component that controls the flow of a game.
 */

public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final GameState gameState;
    private final GameStepPerformer gameStepPerformer;
    private final BoardUtil boardUtil;

    public GameController(GameState gameState, GameStepPerformer gameStepPerformer, BoardUtil boardUtil) {
        this.gameState = gameState;
        this.gameStepPerformer = gameStepPerformer;
        this.boardUtil = boardUtil;
    }

    /**
     * Starts the game loop.
     */
    public void start() {
        LOGGER.info("Starting game loop");
        while (isGameInProgress()) {
            gameStepPerformer.performGameStep();
        }
        LOGGER.info("Game loop finished");
    }

    private boolean isGameInProgress() {
        return !gameState.isShouldExit() && !boardUtil.isBoardCompleted(gameState.getCurrentBoard(), gameState.getCurrentBoard2())
                && !boardUtil.isBoardCompleted(gameState.getCurrentBoard1(), gameState.getCurrentBoard3());
    }
}
