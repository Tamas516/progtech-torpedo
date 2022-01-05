package hu.nye.torpedo.service.map.parser;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.model.RawBoard;
import hu.nye.torpedo.service.exception.PlayerBoardParsingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerPlayerBoardParserTest {

    private static final int NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLUMNS = 2;

    private static final String VALID_RAW_BOARD = "00\n++\n";
    private static final String INVALID_RAW_BOARD_FEW_ROWS = "00\n";
    private static final String INVALID_RAW_BOARD_FEW_COLUMNS = "00\n+\n";

    private static final String[][] BOARD = {
            {"0", "0"},
            {"+", "+"}
    };

    private static final PlayerBoard EXPECTED_PLAYER_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, BOARD);

    private PlayerBoardParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayerBoardParser(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
    }

    @Test
    public void testParseMapShouldReturnNewParsedMap() throws PlayerBoardParsingException {
        // given
        RawBoard input = new RawBoard(VALID_RAW_BOARD);

        // when
        PlayerBoard result = underTest.parseBoard(input);

        // then
        assertEquals(EXPECTED_PLAYER_BOARD, result);
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughRows() throws PlayerBoardParsingException {
        // given in setup
        RawBoard input = new RawBoard(INVALID_RAW_BOARD_FEW_ROWS);

        // when - then
        assertThrows(PlayerBoardParsingException.class, () -> {
            underTest.parseBoard(input);
        });
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughColumns() {
        // given in setup
        RawBoard input = new RawBoard(INVALID_RAW_BOARD_FEW_COLUMNS);

        // when - then
        assertThrows(PlayerBoardParsingException.class, () -> {
            underTest.parseBoard(input);
        });
    }

}
