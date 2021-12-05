package hu.nye.torpedo.service.map.reader.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.nye.torpedo.service.exception.PlayerBoardReadingException;
import hu.nye.torpedo.service.map.BoardReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BoardReader implementation that reads
 * a board from a BufferedReader.
 */

public class PlayerBoardBufferedReader implements BoardReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerBoardBufferedReader.class);

    private final BufferedReader reader;

    public PlayerBoardBufferedReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String> readBoard() throws PlayerBoardReadingException {

        LOGGER.info("Reading the map");

        String row;
        List<String> rows = new ArrayList<>();

        try {
            while ((row = reader.readLine()) != null) {
                rows.add(row);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read map", e);
            throw new PlayerBoardReadingException("Failed to read map");
        }

        return rows;
    }

}
