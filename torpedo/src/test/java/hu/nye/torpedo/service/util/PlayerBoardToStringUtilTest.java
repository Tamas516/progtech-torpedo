package hu.nye.torpedo.service.util;

import hu.nye.torpedo.model.PlayerBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerBoardToStringUtilTest {

    private static final String[][] BOARD = new String[][] {
            {"0","0"},
            {"+","+"}
    };

    private BoardToStringUtil underTest = new BoardToStringUtil();

    @Test
    public void testConvertMapVoMapToStringShouldReturnWithCorrectStringRepresentation() {
        // Given
        PlayerBoard playerBoard = new PlayerBoard(2, 2, BOARD);
        String expected = "00\n++\n";

        // When
        String actual = underTest.convertBoardToString(playerBoard);

        // Then
        Assertions.assertEquals(expected, actual);
    }

}
