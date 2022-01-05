package hu.nye.torpedo.service.util;

import hu.nye.torpedo.model.PlayerBoard;

/**
 * Util class containing useful operations for serialize a Board.
 */
public class BoardToStringUtil {

    /**
     * Convert Board's board attribute to String.
     */
    public String convertBoardToString(PlayerBoard playerBoard) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < playerBoard.getNumberOfRows(); i++) {
            for (int j = 0; j < playerBoard.getNumberOfColumns(); j++) {
                String character = playerBoard.getBoard()[i][j];
                builder.append(character);
            }
            builder.append("\n");
        }
        return builder.toString();
    }


}
