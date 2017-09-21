package command;

import main.DrawingProgram;

/**
 * Quit program
 * Usage: 'Q'
 */
public class QuitCommand extends Command {

    public static final String CMD = "Q";

    @Override
    public void execute(String[] args) {
        System.out.println("Drawing Program is terminating...");
        DrawingProgram.IS_RUNNING = false;
    }

    @Override
    public String toString() {
        return "Command     : Q\n" +
                "Description : Quit the program";
    }
}
