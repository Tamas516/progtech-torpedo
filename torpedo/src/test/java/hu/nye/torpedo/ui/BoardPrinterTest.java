package hu.nye.torpedo.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.util.BoardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BoardPrinterTest {

    private static final int NUMBER_OF_ROWS = 4;
    private static final int NUMBER_OF_COLUMNS = 4;
    private static final int BOX_WIDTH = 4;
    private static final int BOX_HEIGHT = 4;

    private static final String[][] MAP = {
            {" ", "1", "2", "3"},
            {" ", "1", "2", "3"},
            {" ", "1", "2", "3"},
            {" ", "1", "2", "3"},
    };

    private static final PlayerBoard PLAYER_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);
    private static final PlayerBoard OPPONENT_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);
    private static final PlayerBoard PLAYER_EMPTY_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);
    private static final PlayerBoard OPPONENT_EMPTY_BOARD = new PlayerBoard(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP);

    private static final List<List<String>> ROWS_AS_LISTS = List.of(
            List.of(" ", "1", "2", "3"),
            List.of(" ", "1", "2", "3"),
            List.of(" ", "1", "2", "3"),
            List.of(" ", "1", "2", "3")
    );

    private static final List<String> MAP_AS_STRING = List.of(
            "===================",
            "||   | 1 | 2 | 3 ||",
            "||   | 1 | 2 | 3 ||",
            "||   | 1 | 2 | 3 ||",
            "||   | 1 | 2 | 3 ||",
            "==================="
    );

    @Mock
    private BoardUtil boardUtil;
    @Mock
    private PrintWrapper printWrapper;
    @Captor
    private ArgumentCaptor<String> printWrapperArgumentCaptor;

    private BoardPrinter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BoardPrinter(BOX_WIDTH, BOX_HEIGHT, boardUtil, printWrapper);
    }

    @Test
    public void testPrintMapShouldDelegateCorrectCallsToPrintWrapper() {
        // given
        for (int i = 0; i < ROWS_AS_LISTS.size(); i++) {
            given(boardUtil.getRowOfBoard(PLAYER_BOARD, i)).willReturn(ROWS_AS_LISTS.get(i));
        }
//        for (int i = 0; i < ROWS_AS_LISTS.size(); i++) {
//            given(boardUtil.getRowOfBoard(OPPONENT_BOARD, i)).willReturn(ROWS_AS_LISTS.get(i));
//        }
//        for (int i = 0; i < ROWS_AS_LISTS.size(); i++) {
//            given(boardUtil.getRowOfBoard(PLAYER_EMPTY_BOARD, i)).willReturn(ROWS_AS_LISTS.get(i));
//        }
//        for (int i = 0; i < ROWS_AS_LISTS.size(); i++) {
//            given(boardUtil.getRowOfBoard(OPPONENT_EMPTY_BOARD, i)).willReturn(ROWS_AS_LISTS.get(i));
//        }

        // when
        underTest.printBoard(PLAYER_BOARD);

        // then
        verify(printWrapper, times(6)).printLine(printWrapperArgumentCaptor.capture());
        assertEquals(MAP_AS_STRING, printWrapperArgumentCaptor.getAllValues());
    }


}
