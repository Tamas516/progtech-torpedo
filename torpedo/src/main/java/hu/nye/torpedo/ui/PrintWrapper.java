package hu.nye.torpedo.ui;

/**
 * Util class used to wrap operations that print to stdout.
 * Created for making unit testing easier.
 */

public class PrintWrapper {

    public void printLine(String string) {
        System.out.println(string);
    }

}
