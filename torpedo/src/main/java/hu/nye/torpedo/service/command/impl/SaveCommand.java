package hu.nye.torpedo.service.command.impl;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.persistence.GameSavesRepository;
import hu.nye.torpedo.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to save the current game state.
 */
public class SaveCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveCommand.class);
    private static final String SAVE_COMMAND = "save";

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    public SaveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        this.gameSavesRepository = gameSavesRepository;
        this.gameState = gameState;
    }

    @Override
    public boolean canProcess(String input) {
        return SAVE_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Game Save command was called");
        gameSavesRepository.save(gameState.getCurrentBoard2(), gameState.getCurrentBoard3());
        LOGGER.info("Game Save was successfully persisted");
    }
}
