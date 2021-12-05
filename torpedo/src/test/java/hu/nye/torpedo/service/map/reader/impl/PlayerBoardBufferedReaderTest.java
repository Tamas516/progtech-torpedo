package hu.nye.torpedo.service.map.reader.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import hu.nye.torpedo.service.exception.PlayerBoardReadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerBoardBufferedReaderTest {

    private static final String LINE_1="line1";
    private static final String LINE_2="line2";

    private static final List<String> EXPECTED_RAW_BOARD=List.of(LINE_1, LINE_2);

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
        //when
        List<String> result=underTest.readBoard();

        //then
        assertEquals(EXPECTED_RAW_BOARD, result);
    }

    @Test
    public void testReadBoardShouldThrowPlayerBoardReadingExceptionWhenBoardReadingFails() throws IOException {
        //given
        doThrow(IOException.class).when(bufferedReader).readLine();

        //when - then
        assertThrows(PlayerBoardReadingException.class, () -> {underTest.readBoard();});
    }


}
