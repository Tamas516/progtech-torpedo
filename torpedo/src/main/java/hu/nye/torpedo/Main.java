package hu.nye.torpedo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.model.PlayerBoard;
import hu.nye.torpedo.service.command.Command;
import hu.nye.torpedo.service.command.InputHandler;
import hu.nye.torpedo.service.command.impl.DefaultCommand;
import hu.nye.torpedo.service.command.impl.ExitCommand;
import hu.nye.torpedo.service.command.impl.PrintCommand;
import hu.nye.torpedo.service.command.impl.PutCommand;
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

/**
 * Main Class.
 */

public class Main {

    /**
     * Main method.
     */

    public static void main(String[] args) {

        GameState gameState = new GameState(null, null, null, null, false);

        InputStream is = Main.class.getClassLoader().getResourceAsStream("PlayerBoard.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        BoardReader boardReader = new PlayerBoardBufferedReader(reader);

        BoardUtil boardUtil = new BoardUtil();
        PrintWrapper printWrapper = new PrintWrapper();

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

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInputReader userInputReader = new UserInputReader(bufferedReader);

        BoardPrinter boardPrinter = new BoardPrinter(10, 10,  boardUtil, printWrapper);

        PutPerformer putPerformer = new PutPerformer();

        List<Command> commandList = List.of(
                new PrintCommand(gameState, boardPrinter),
                new PutCommand(gameState, putPerformer, boardPrinter, printWrapper),
                //new PutCommandOpponent(gameState, putPerformer, boardPrinter, printWrapper),
                new ExitCommand(gameState),
                new DefaultCommand(printWrapper)
        );
        InputHandler inputHandler = new InputHandler(commandList);

        GameStepPerformer gameStepPerformer = new GameStepPerformer(userInputReader, inputHandler);

        System.out.println("TORPEDO");
        System.out.println("print - See the boards, put (0-9) (0-9) - Attack enemy ship, exit - Exit the game");

        // game
        GameController gameController = new GameController(gameState, gameStepPerformer, boardUtil);
        gameController.start();




    }


    }


