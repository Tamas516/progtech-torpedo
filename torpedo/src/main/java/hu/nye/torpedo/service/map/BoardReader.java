package hu.nye.torpedo.service.map;

import java.util.List;

import hu.nye.torpedo.service.exception.PlayerBoardReadingException;

/**
 * Interface for reading a Board.
 */


public interface BoardReader {

    List<String> readBoard() throws PlayerBoardReadingException;

}
