package hu.nye.torpedo.service.util;

import hu.nye.torpedo.model.PlayerBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerBoardUtilTest {

    private static final int NUMBER_OF_ROWS = 3;
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final String[][] MAP = {
            {"0", "0", "0"},
            {"0", "+", "0"},
            {"0", "0", "0"}
    };

    private static final String[][] MAP2 = {
            {"0", "0", "0"},
            {"0", "+", "0"},
            {"0", "0", "0"}
    };
    private static final String[][] MAP3 = {
            {"0", "0", "0"},
            {"0", "+", "0"},
            {"0", "0", "0"}
    };

    private static final String[][] MAP4 = {
            {"0", "0", "0"},
            {"0", "0", "0"},
            {"0", "0", "0"}
    };

    private static final PlayerBoard PLAYER_PLAYER_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);
    private static final PlayerBoard OPPONENT_PLAYER_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP3);
    private static final PlayerBoard PLAYER_EMPTY_PLAYER_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP2);
    private static final PlayerBoard OPPONENT_EMPTY_PLAYER_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP4);

    private static final int FIRST_ROW_INDEX = 0;
    private static final int FIRST_COLUMN_INDEX = 0;

    private static final List<String> FIRST_ROW_AS_LIST = List.of("0", "0", "0");
    private static final List<String> FIRST_COLUMN_AS_LIST = List.of("0", "0", "0");
    private static final List<Integer> TOP_LEFT_TWO_BY_TWO_BOX_AS_LIST = List.of(1, 2, 4, 5);

    private BoardUtil underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BoardUtil();
    }

    @Test
    public void testGetRowOfMapShouldReturnTheSelectedRowAsList() {
        // given in setup

        // when
        List<String> result = underTest.getRowOfBoard(PLAYER_PLAYER_BOARD, FIRST_ROW_INDEX);

        // then
        assertEquals(FIRST_ROW_AS_LIST, result);
    }

    @Test
    public void testGetColumnOfMapShouldReturnTheSelectedColumnAsList() {
        // given in setup

        // when
        List<String> result = underTest.getColumnOfBoard(PLAYER_PLAYER_BOARD, FIRST_COLUMN_INDEX);

        // then
        assertEquals(FIRST_COLUMN_AS_LIST, result);
    }


    @Test
    public void testIsMapCompletedShouldReturnTrueWhenThereAreNoZeroValuesInTheMap() {
        // given in setup

        // when
        boolean result = underTest.isBoardCompleted(PLAYER_PLAYER_BOARD, PLAYER_EMPTY_PLAYER_BOARD);

        // then
        assertTrue(result);
    }

    @Test
    public void testIsMapCompletedShouldReturnFalseWhenThereAreStillZeroValuesInTheMap() {
        // given in setup

        // when
        boolean result1 = underTest.isBoardCompleted(OPPONENT_PLAYER_BOARD, OPPONENT_EMPTY_PLAYER_BOARD);

        // then
        assertFalse(result1);
    }

}
