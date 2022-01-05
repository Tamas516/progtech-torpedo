package hu.nye.torpedo.configuration;

import java.util.List;

import hu.nye.torpedo.model.GameState;
import hu.nye.torpedo.persistence.GameSavesRepository;
import hu.nye.torpedo.service.command.Command;
import hu.nye.torpedo.service.command.InputHandler;
import hu.nye.torpedo.service.command.impl.DefaultCommand;
import hu.nye.torpedo.service.command.impl.ExitCommand;
import hu.nye.torpedo.service.command.impl.LoadCommand;
import hu.nye.torpedo.service.command.impl.PrintCommand;
import hu.nye.torpedo.service.command.impl.PutCommand;
import hu.nye.torpedo.service.command.impl.SaveCommand;
import hu.nye.torpedo.service.command.performer.PutPerformer;
import hu.nye.torpedo.ui.BoardPrinter;
import hu.nye.torpedo.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Spring Java configuration class for command specific Spring Beans.
 */
@Configuration
public class CommandConfiguration {

    @Bean
    InputHandler inputHandler(List<Command> commandList) {
        return new InputHandler(commandList);
    }

    @Bean
    PrintCommand printCommand(GameState gameState, BoardPrinter boardPrinter) {
        return new PrintCommand(gameState, boardPrinter);
    }

    @Bean
    PutCommand putCommand(GameState gameState, PutPerformer putPerformer, BoardPrinter boardPrinter, PrintWrapper printWrapper) {
        return new PutCommand(gameState, putPerformer, boardPrinter, printWrapper);
    }

    @Bean
    SaveCommand saveCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        return new SaveCommand(gameSavesRepository, gameState);
    }

    @Bean
    LoadCommand loadCommand(GameSavesRepository gameSavesRepository, GameState gameState) {
        return new LoadCommand(gameSavesRepository, gameState);
    }

   @Bean
    ExitCommand exitCommand(GameState gameState) {
        return new ExitCommand(gameState);
   }

   @Bean
    DefaultCommand defaultCommand(PrintWrapper printWrapper) {
        return new DefaultCommand(printWrapper);
   }

}
