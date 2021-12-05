package hu.nye.torpedo.service.game;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import hu.nye.torpedo.service.command.InputHandler;
import hu.nye.torpedo.service.input.UserInputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameStepPerformerTest {

    private static final String INPUT = "input";

    @Mock
    private UserInputReader userInputReader;
    @Mock
    private InputHandler inputHandler;

    private GameStepPerformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new GameStepPerformer(userInputReader, inputHandler);
    }

    @Test
    public void testPerformGameStepShouldReadUserInputAndDelegateCallToInputHandler() {
        // given
        given(userInputReader.readInput()).willReturn(INPUT);

        // when
        underTest.performGameStep();

        // then
        verify(userInputReader).readInput();
        verify(inputHandler).handleInput(INPUT);
    }


}
