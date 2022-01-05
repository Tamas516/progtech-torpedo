package hu.nye.torpedo.persistence;

import hu.nye.torpedo.model.PlayerBoard;

/**
 * Interface for storing and retrieving current Sudoku game states.
 */
public interface GameSavesRepository {

    void save(PlayerBoard currentPlayerBoard2, PlayerBoard currentPlayerBoard3);

    PlayerBoard load();

    PlayerBoard load2();
}
