package hu.nye.torpedo.service.command.performer;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.exception.PutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PutPerformerTest {

    private static final int NUMBER_OF_ROWS = 2;
    private static final int NUMBER_OF_COLUMNS = 2;

    private static final int TARGET_ROW_INDEX = 0;
    private static final int VALID_TARGET_COLUMN_INDEX = 0;
    private static final int INVALID_TARGET_COLUMN_INDEX = 1;
    private static final String TARGET_CHARACTER = "+";

    private static final String[][] INPUT_BOARD = {
            {"0", "+"},
            {"0", "+"}
    };

    private static final String[][] EXPECTED_BOARD = {
            {"+", "+"},
            {"0", "+"}
    };

    private static final PlayerBoard INPUT_PLAYER_BOARD= new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, INPUT_BOARD);
    private static final PlayerBoard EXPECTED_PLAYER_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, EXPECTED_BOARD);

    private PutPerformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PutPerformer();
    }

    @Test
    public void testPerformShouldPerformPutAndReturnNewMap() throws PutException {
        // given in setup

        // when
        PlayerBoard result = underTest.perform(INPUT_PLAYER_BOARD, TARGET_ROW_INDEX, VALID_TARGET_COLUMN_INDEX, TARGET_CHARACTER);

        // then
        assertEquals(EXPECTED_PLAYER_BOARD, result);
    }

    @Test
    public void testPerformShouldThrowPutExceptionWhenWeTryToPutIntoFixedPosition() {
        // given in setup

        // when - then
        Assertions.assertThrows(PutException.class, () -> {
            underTest.perform(INPUT_PLAYER_BOARD, TARGET_ROW_INDEX, INVALID_TARGET_COLUMN_INDEX, TARGET_CHARACTER);
        });
    }


}
