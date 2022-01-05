package hu.nye.torpedo.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.model.RawBoard;
import hu.nye.torpedo.service.exception.PlayerBoardParsingException;
import hu.nye.torpedo.service.exception.PlayerBoardReadingException;
import hu.nye.torpedo.service.map.parser.PlayerBoardParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerBoardReaderFacadeTest {

    private static final RawBoard RAW_BOARD = new RawBoard("board");

    private static final PlayerBoard PLAYER_PLAYER_BOARD = new PlayerBoard(0, 0, null);

    @Mock
    private BoardReader boardReader;
    @Mock
    private PlayerBoardParser playerBoardParser;

    private BoardReaderFacade underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BoardReaderFacade(boardReader, playerBoardParser);
    }

    @Test
    public void testReadMapShouldReturnReadAndParsedAndValidatedMap() throws PlayerBoardReadingException, PlayerBoardParsingException {
        // given
        given(boardReader.readBoard()).willReturn(RAW_BOARD);
        given(playerBoardParser.parseBoard(RAW_BOARD)).willReturn(PLAYER_PLAYER_BOARD);

        // when
        PlayerBoard result = underTest.readBoard();

        // then
        verify(boardReader).readBoard();
        verify(playerBoardParser).parseBoard(RAW_BOARD);
        assertEquals(PLAYER_PLAYER_BOARD, result);
    }

    @Test
    public void testReadMapShouldThrowRuntimeExceptionWhenReadingOfTheMapFails() throws PlayerBoardReadingException {
        // given
        doThrow(PlayerBoardReadingException.class).when(boardReader).readBoard();

        // when - then
        assertThrows(RuntimeException.class, () -> {
            underTest.readBoard();
        });
    }

    @Test
    public void testReadMapShouldThrowRuntimeExceptionWhenParsingOfTheMapFails() throws PlayerBoardReadingException, PlayerBoardParsingException {
        // given
        given(boardReader.readBoard()).willReturn(RAW_BOARD);
        doThrow(PlayerBoardParsingException.class).when(playerBoardParser).parseBoard(RAW_BOARD);

        // when - then
        assertThrows(RuntimeException.class, () -> {
            underTest.readBoard();
        });
    }


}
