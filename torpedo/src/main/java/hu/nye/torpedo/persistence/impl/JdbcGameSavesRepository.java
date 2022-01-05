package hu.nye.torpedo.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.model.RawBoard;
import hu.nye.torpedo.persistence.GameSavesRepository;
import hu.nye.torpedo.service.exception.PlayerBoardParsingException;
import hu.nye.torpedo.service.map.parser.PlayerBoardParser;
import hu.nye.torpedo.service.util.BoardToStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDBC based implementation of GameSavesRepository.
 */
public class JdbcGameSavesRepository implements GameSavesRepository, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcGameSavesRepository.class);

    static final String INSERT_STATEMENT = "INSERT INTO game_saves (id, board2, board3) VALUES (1, ?, ?);";
    static final String DELETE_STATEMENT = "DELETE FROM game_saves WHERE id = 1;";
    static final String SELECT_STATEMENT = "SELECT * FROM game_saves WHERE id = 1;";

    private Connection connection;
    private BoardToStringUtil boardToStringUtil;
    private PlayerBoardParser playerBoardParser;

    public JdbcGameSavesRepository(Connection connection, BoardToStringUtil boardToStringUtil, PlayerBoardParser playerBoardParser) {
        this.connection = connection;
        this.boardToStringUtil = boardToStringUtil;
        this.playerBoardParser = playerBoardParser;
    }

    @Override
    public void save(PlayerBoard currentPlayerBoard2, PlayerBoard currentPlayerBoard3) {
        try {
            deleteCurrentlyStoredSave();
            insertNewSave(currentPlayerBoard2, currentPlayerBoard3);
        } catch (SQLException e) {
            LOGGER.error("Unexpected exception during saving game state", e);
        }
    }

    @Override
    public PlayerBoard load() {
        RawBoard rawBoard2 = readRawBoard2();
        try {
            PlayerBoard playerBoard2 = playerBoardParser.parseBoard(rawBoard2);
            return playerBoard2;
        } catch (PlayerBoardParsingException e) {
            throw new RuntimeException("Failed to parse loaded map");
        }
    }

    @Override
    public PlayerBoard load2() {
        RawBoard rawBoard3 = readRawBoard3();
        try {
            PlayerBoard playerBoard3 = playerBoardParser.parseBoard(rawBoard3);
            return playerBoard3;
        } catch (PlayerBoardParsingException e) {
            throw new RuntimeException("Failed to parse loaded map");
        }
    }

    private RawBoard readRawBoard2() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);) {

            resultSet.next();
            String board2 = resultSet.getString("board2");

            RawBoard rawBoard2 = new RawBoard(board2);
            return rawBoard2;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed to load map from DB");
        }
    }

    private RawBoard readRawBoard3() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);) {

            resultSet.next();
            String board3 = resultSet.getString("board3");

            RawBoard rawBoard3 = new RawBoard(board3);
            return rawBoard3;
        } catch (SQLException throwables) {
            throw new RuntimeException("Failed to load map from DB");
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private void deleteCurrentlyStoredSave() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_STATEMENT);
        }
    }

    private void insertNewSave(PlayerBoard currentPlayerBoard2, PlayerBoard currentPlayerBoard3) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, boardToStringUtil.convertBoardToString(currentPlayerBoard2));
            preparedStatement.setString(2, boardToStringUtil.convertBoardToString(currentPlayerBoard3));
            preparedStatement.executeUpdate();
        }
    }


}
