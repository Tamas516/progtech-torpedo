package hu.nye.torpedo.persistence.impl;

import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.model.RawBoard;
import hu.nye.torpedo.service.exception.PlayerBoardParsingException;
import hu.nye.torpedo.service.map.parser.PlayerBoardParser;
import hu.nye.torpedo.service.util.BoardToStringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;

public class JdbcGameSavesRepositoryTest {

    private JdbcGameSavesRepository underTest;

    private Connection connection;
    private BoardToStringUtil boardToStringUtil;
    private PlayerBoardParser playerBoardParser;

    @BeforeEach
    public void init() {
        connection = Mockito.mock(Connection.class);
        boardToStringUtil = Mockito.mock(BoardToStringUtil.class);
        playerBoardParser = Mockito.mock(PlayerBoardParser.class);

        underTest = new JdbcGameSavesRepository(connection, boardToStringUtil, playerBoardParser);
    }

    @Test
    public void testSaveShouldDeletePreviousSaveAndStoreTheNewOneWhenThereIsNoException() throws SQLException {

        // Given
        PlayerBoard currentPlayerBoard2 = Mockito.mock(PlayerBoard.class);
        PlayerBoard currentPlayerBoard3 = Mockito.mock(PlayerBoard.class);

        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(JdbcGameSavesRepository.INSERT_STATEMENT))
                .thenReturn(preparedStatement);
        String boardString2 = "boardString2";
        String boardString3 = "boardString3";

        Mockito.when(boardToStringUtil.convertBoardToString(currentPlayerBoard2)).thenReturn(boardString2);
        Mockito.when(boardToStringUtil.convertBoardToString(currentPlayerBoard3)).thenReturn(boardString3);


        // When
        underTest.save(currentPlayerBoard2, currentPlayerBoard3);

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeUpdate(JdbcGameSavesRepository.DELETE_STATEMENT);
        Mockito.verify(statement).close();
        Mockito.verify(connection).prepareStatement(JdbcGameSavesRepository.INSERT_STATEMENT);
        Mockito.verify(boardToStringUtil).convertBoardToString(currentPlayerBoard2);
        Mockito.verify(preparedStatement).setString(1, boardString2);
        Mockito.verify(boardToStringUtil).convertBoardToString(currentPlayerBoard3);
        Mockito.verify(preparedStatement).setString(2, boardString3);
        Mockito.verify(preparedStatement).executeUpdate();
        Mockito.verify(preparedStatement).close();
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser, currentPlayerBoard2,
                currentPlayerBoard3, statement, preparedStatement);
    }

    @Test
    public void testSaveShouldDoNothingWhenThereIsAnSqlException() throws SQLException {
        // Given
        PlayerBoard currentPlayerBoard2 = Mockito.mock(PlayerBoard.class);
        PlayerBoard currentPlayerBoard3 = Mockito.mock(PlayerBoard.class);

        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        underTest.save(currentPlayerBoard2, currentPlayerBoard3);

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser,
                currentPlayerBoard2, currentPlayerBoard3);
    }

    @Test
    public void testCloseShouldDelegateCloseCallToConnection() throws Exception {
        // Given

        // When
        underTest.close();

        // Then
        Mockito.verify(connection).close();
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser);
    }

    @Test
    public void testLoadShouldReturnAMapVOWhenThereIsNoException() throws SQLException, PlayerBoardParsingException {
        // Given
        PlayerBoard expected = Mockito.mock(PlayerBoard.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String boardToString = "boardString";
        Mockito.when(resultSet.getString("board2")).thenReturn(boardToString);
        RawBoard rawBoard = new RawBoard(boardToString);
        Mockito.when(playerBoardParser.parseBoard(rawBoard)).thenReturn(expected);

        // When
        PlayerBoard actual = underTest.load();

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("board2");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(playerBoardParser).parseBoard(rawBoard);
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser, expected, statement, resultSet);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLoad2ShouldReturnAMapVOWhenThereIsNoException() throws SQLException, PlayerBoardParsingException {
        // Given
        PlayerBoard expected = Mockito.mock(PlayerBoard.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String boardToString = "boardString";
        Mockito.when(resultSet.getString("board3")).thenReturn(boardToString);
        RawBoard rawBoard = new RawBoard(boardToString);
        Mockito.when(playerBoardParser.parseBoard(rawBoard)).thenReturn(expected);

        // When
        PlayerBoard actual = underTest.load2();

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("board3");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(playerBoardParser).parseBoard(rawBoard);
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser, expected, statement, resultSet);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenSqlExceptionIsThrown() throws SQLException {
        // Given
        PlayerBoard expected = Mockito.mock(PlayerBoard.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser, expected);
    }

    @Test
    public void testLoad2ShouldThrowRuntimeExceptionWhenSqlExceptionIsThrown() throws SQLException {
        // Given
        PlayerBoard expected = Mockito.mock(PlayerBoard.class);
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load2());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser, expected);
    }

    @Test
    public void testLoadShouldThrowRuntimeExceptionWhenMapParsingExceptionIsThrown() throws SQLException, PlayerBoardParsingException {
        // Given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String boardString = "boardString";
        Mockito.when(resultSet.getString("board2")).thenReturn(boardString);
        RawBoard rawBoard = new RawBoard(boardString);
        Mockito.when(playerBoardParser.parseBoard(rawBoard)).thenThrow(PlayerBoardParsingException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("board2");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(playerBoardParser).parseBoard(rawBoard);
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser, statement, resultSet);
    }

    @Test
    public void testLoad2ShouldThrowRuntimeExceptionWhenMapParsingExceptionIsThrown() throws SQLException, PlayerBoardParsingException {
        // Given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT)).thenReturn(resultSet);
        String boardString = "boardString";
        Mockito.when(resultSet.getString("board3")).thenReturn(boardString);
        RawBoard rawBoard = new RawBoard(boardString);
        Mockito.when(playerBoardParser.parseBoard(rawBoard)).thenThrow(PlayerBoardParsingException.class);

        // When
        Assertions.assertThrows(RuntimeException.class, () -> underTest.load2());

        // Then
        Mockito.verify(connection).createStatement();
        Mockito.verify(statement).executeQuery(JdbcGameSavesRepository.SELECT_STATEMENT);
        Mockito.verify(resultSet).next();
        Mockito.verify(resultSet).getString("board3");
        Mockito.verify(statement).close();
        Mockito.verify(resultSet).close();
        Mockito.verify(playerBoardParser).parseBoard(rawBoard);
        Mockito.verifyNoMoreInteractions(connection, boardToStringUtil, playerBoardParser, statement, resultSet);
    }

}
