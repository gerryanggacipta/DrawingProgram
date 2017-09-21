package command;

import main.DrawingProgram;

/**
 * This command is to show the list of commands supported
 * Usage: 'help'
 */
public class HelpCommand extends Command {

    public static final String CMD = "help";

    @Override
    public void execute(String[] args) {

        for (String key : DrawingProgram.CMD_MAP.keySet()) {
            System.out.println(DrawingProgram.CMD_MAP.get(key) + "\n");
        }

    }

    @Override
    public String toString() {
        return "Command     : help\n" +
                "Description : Show the list of commands available";
    }
}
