package hu.nye.torpedo.service.command.impl;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.persistence.GameSavesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SaveCommandTest {

    private SaveCommand underTest;

    private GameSavesRepository gameSavesRepository;
    private GameState gameState;

    @BeforeEach
    public void init() {
        gameSavesRepository = Mockito.mock(GameSavesRepository.class);
        gameState = Mockito.mock(GameState.class);
        underTest = new SaveCommand(gameSavesRepository, gameState);
    }

    @Test
    public void testCanProcessShouldReturnTrueWhenTheInputIsSave() {
        // Given
        String input = "save";
        boolean expected = true;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheInputIsNotSave() {
        // Given
        String input = "load";
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenTheInputIsNull() {
        // Given
        String input = null;
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testProcessShouldRetrieveCurrentMapAndPassItToGameSavesRepository() {
        // Given
        String input = "input";
        PlayerBoard playerBoard = Mockito.mock(PlayerBoard.class);
        Mockito.when(gameState.getCurrentBoard2()).thenReturn(playerBoard);
        PlayerBoard playerBoard1 = Mockito.mock(PlayerBoard.class);
        Mockito.when(gameState.getCurrentBoard3()).thenReturn(playerBoard1);

        // When
        underTest.process(input);

        // Then
        Mockito.verify(gameState).getCurrentBoard2();
        Mockito.verify(gameState).getCurrentBoard3();
        Mockito.verify(gameSavesRepository).save(playerBoard, playerBoard1);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, playerBoard, playerBoard1);
    }

    @Test
    public void testProcessShouldRetrieveCurrentMapAndPassItToGameSavesRepositoryWhenInputIsNull() {
        // Given
        String input = null;
        PlayerBoard playerBoard = Mockito.mock(PlayerBoard.class);
        Mockito.when(gameState.getCurrentBoard2()).thenReturn(playerBoard);
        PlayerBoard playerBoard1 = Mockito.mock(PlayerBoard.class);
        Mockito.when(gameState.getCurrentBoard3()).thenReturn(playerBoard1);

        // When
        underTest.process(input);

        // Then
        Mockito.verify(gameState).getCurrentBoard2();
        Mockito.verify(gameState).getCurrentBoard3();
        Mockito.verify(gameSavesRepository).save(playerBoard, playerBoard1);
        Mockito.verifyNoMoreInteractions(gameSavesRepository, gameState, playerBoard, playerBoard1);
    }

}
