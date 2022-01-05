package hu.nye.torpedo.service.command.impl;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.persistence.GameSavesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoadCommandTest {

    private LoadCommand underTest;

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    @BeforeEach
    public void init() {
        gameSavesRepository = Mockito.mock(GameSavesRepository.class);
        gameState = Mockito.mock(GameState.class);
        underTest = new LoadCommand(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenInputIsLoad() {
        // Given
        String input = "load";
        boolean expected = true;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotLoad() {
        // Given
        String input = "save";
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNull() {
        // Given
        String input = null;
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState);
    }

    @Test
    public void testProcessShouldRetrieveMapVOFromRepositoryAndSetTheCurrentMapInGameState() {
        // Given
        String input = null;
        PlayerBoard playerBoard = Mockito.mock(PlayerBoard.class);
        Mockito.when(gameSavesRepository.load()).thenReturn(playerBoard);
        PlayerBoard playerBoard1 = Mockito.mock(PlayerBoard.class);
        Mockito.when(gameSavesRepository.load2()).thenReturn(playerBoard1);

        // When
        underTest.process(input);

        // Then
        Mockito.verify(gameSavesRepository).load();
        Mockito.verify(gameState).setCurrentBoard2(playerBoard);
        Mockito.verify(gameSavesRepository).load2();
        Mockito.verify(gameState).setCurrentBoard3(playerBoard1);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, playerBoard, playerBoard1);
    }


}
