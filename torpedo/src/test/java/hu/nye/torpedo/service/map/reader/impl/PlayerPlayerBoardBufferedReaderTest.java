package hu.nye.torpedo.service.map.reader.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import hu.nye.torpedo.model.RawBoard;
import hu.nye.torpedo.service.exception.PlayerBoardReadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerPlayerBoardBufferedReaderTest {

    private static final String LINE_1="00++";
    private static final String LINE_2="++00";

    private static final String EXPECTED_BOARD="00++\n++00\n";

    @Mock
    private BufferedReader bufferedReader;

    private PlayerBoardBufferedReader underTest;

    @BeforeEach
    public void setUp()
    {
        underTest=new PlayerBoardBufferedReader(bufferedReader);
    }

    @Test
    public void testReadBoardShouldReturnReadLinesFromBufferedReader() throws IOException, PlayerBoardReadingException {
        //given
        given(bufferedReader.readLine()).willReturn(LINE_1, LINE_2, null);
        RawBoard expected = new RawBoard(EXPECTED_BOARD);

        //when
        RawBoard result=underTest.readBoard();

        //then
        assertEquals(expected, result);
    }

    @Test
    public void testReadBoardShouldThrowPlayerBoardReadingExceptionWhenBoardReadingFails() throws IOException {
        //given
        doThrow(IOException.class).when(bufferedReader).readLine();

        //when - then
        assertThrows(PlayerBoardReadingException.class, () -> {underTest.readBoard();});
    }


}
