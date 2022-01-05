package hu.nye.torpedo.configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import hu.nye.torpedo.Main;
import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.command.InputHandler;
import hu.nye.torpedo.service.command.performer.PutPerformer;
import hu.nye.torpedo.service.game.GameController;
import hu.nye.torpedo.service.game.GameStepPerformer;
import hu.nye.torpedo.service.input.UserInputReader;
import hu.nye.torpedo.service.map.BoardReader;
import hu.nye.torpedo.service.map.BoardReaderFacade;
import hu.nye.torpedo.service.map.parser.PlayerBoardParser;
import hu.nye.torpedo.service.map.reader.impl.PlayerBoardBufferedReader;
import hu.nye.torpedo.service.util.BoardUtil;
import hu.nye.torpedo.ui.BoardPrinter;
import hu.nye.torpedo.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Spring Java configuration class.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean(initMethod = "start")
    GameController gameController(GameState gameState, GameStepPerformer gameStepPerformer, BoardUtil boardUtil) {
        return new GameController(gameState, gameStepPerformer, boardUtil);
    }

    @Bean
    GameState gameState() {

        GameState gameState = new GameState(null, null, null, null, false);

        InputStream is = Main.class.getClassLoader().getResourceAsStream("PlayerBoard.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        BoardReader boardReader = new PlayerBoardBufferedReader(reader);

        PlayerBoardParser boardParser = new PlayerBoardParser(10, 10);

        BoardReaderFacade boardReaderFacade = new BoardReaderFacade(boardReader, boardParser);
        PlayerBoard playerBoard = boardReaderFacade.readBoard();
        gameState.setCurrentBoard(playerBoard);

        InputStream is1 = Main.class.getClassLoader().getResourceAsStream("OpponentBoard.txt");
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(is1));
        BoardReader boardReader1 = new PlayerBoardBufferedReader(reader1);

        BoardReaderFacade boardReaderFacade1 = new BoardReaderFacade(boardReader1, boardParser);
        PlayerBoard playerBoard1 = boardReaderFacade1.readBoard();
        gameState.setCurrentBoard1(playerBoard1);

        InputStream is2 = Main.class.getClassLoader().getResourceAsStream("PlayerEmptyBoard.txt");
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));
        BoardReader boardReader2 = new PlayerBoardBufferedReader(reader2);

        BoardReaderFacade boardReaderFacade2 = new BoardReaderFacade(boardReader2, boardParser);
        PlayerBoard playerBoard2 = boardReaderFacade2.readBoard();
        gameState.setCurrentBoard2(playerBoard2);

        InputStream is3 = Main.class.getClassLoader().getResourceAsStream("OpponentEmptyBoard.txt");
        BufferedReader reader3 = new BufferedReader(new InputStreamReader(is3));
        BoardReader boardReader3 = new PlayerBoardBufferedReader(reader3);

        BoardReaderFacade boardReaderFacade3 = new BoardReaderFacade(boardReader3, boardParser);
        PlayerBoard playerBoard3 = boardReaderFacade3.readBoard();
        gameState.setCurrentBoard3(playerBoard3);
        return new GameState(playerBoard, playerBoard1, playerBoard2, playerBoard3, false);
    }

    @Bean
    PlayerBoardParser playerBoardParser() {
        return new PlayerBoardParser(10, 10);
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    BoardPrinter boardPrinter(BoardUtil boardUtil, PrintWrapper printWrapper) {
        return new BoardPrinter(10, 10, boardUtil, printWrapper);
    }

    @Bean
    PutPerformer putPerformer() {
        return new PutPerformer();
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }

}
