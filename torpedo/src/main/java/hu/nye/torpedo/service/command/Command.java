package hu.nye.torpedo.service.command;

/**
 * Interface that represents a command which the user can use to
 * interact with the game.
 */

public interface Command {

    boolean canProcess(String input);

    void process(String input);

}
