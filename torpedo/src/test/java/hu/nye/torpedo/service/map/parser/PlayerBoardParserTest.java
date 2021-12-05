package hu.nye.torpedo.service.map.parser;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.exception.PlayerBoardParsingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerBoardParserTest {

    private static final int NUMBER_OF_ROWS=2;
    private static final int NUMBER_OF_COLUMNS=2;

    private static final List<String> VALID_RAW_BOARD=List.of(
      "++",
      "++"
    );

    private static final List<String> INVALID_RAW_MAP_FEW_ROWS = List.of(
            "++"
    );
    private static final List<String> INVALID_RAW_MAP_FEW_COLUMNS = List.of(
            "++",
            "+"
    );

    private static final String[][] BOARD={
            {"+","+"},
            {"+","+"}
    };

    private static final PlayerBoard EXPECTED_BOARD=new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, BOARD);

    private PlayerBoardParser underTest;

    @BeforeEach
    public void setUp()
    {
        underTest=new PlayerBoardParser(NUMBER_OF_ROWS,NUMBER_OF_COLUMNS);
    }

    @Test
    public void testParseBoardShouldReturnNewParsedMap() throws PlayerBoardParsingException {
        //given

        //when
        PlayerBoard result=underTest.parseBoard(VALID_RAW_BOARD);

        //then
        assertEquals(EXPECTED_BOARD, result);
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughRows() throws PlayerBoardParsingException {
        // given in setup

        // when - then
        assertThrows(PlayerBoardParsingException.class, () -> {
            underTest.parseBoard(INVALID_RAW_MAP_FEW_ROWS);
        });
    }

    @Test
    public void testParseMapShouldThrowMapParsingExceptionWhenThereAreNotEnoughColumns() {
        // given in setup

        // when - then
        assertThrows(PlayerBoardParsingException.class, () -> {
            underTest.parseBoard(INVALID_RAW_MAP_FEW_COLUMNS);
        });
    }


}
