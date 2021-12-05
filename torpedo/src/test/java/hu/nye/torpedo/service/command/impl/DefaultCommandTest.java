package hu.nye.torpedo.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import hu.nye.torpedo.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DefaultCommandTest {

    private static final String INPUT="";

    private static final String UNKNOWN_COMMAND_MESSAGE="Unknown command";

    @Mock
    private PrintWrapper printWrapper;

    private DefaultCommand underTest;

    @BeforeEach
    public void setUp()
    {
        underTest=new DefaultCommand(printWrapper);
    }

    @Test
    public void testCanProcessShouldAlwaysReturnTrue()
    {
        //given

        //when
        boolean result=underTest.canProcess(INPUT);

        //then
        assertTrue(result);
    }

    @Test
    public void testProcessShouldPrintUnknownCommand()
    {
        //given

        //when
        underTest.process(INPUT);

        //then
        verify(printWrapper).printLine(UNKNOWN_COMMAND_MESSAGE);
    }

}
