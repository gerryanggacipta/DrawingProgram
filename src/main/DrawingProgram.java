package main;

import command.*;

import java.util.*;

/**
 * Author   : Gerry Anggacipta
 * Email    : gerryanggacipta@yahoo.com
 * Date     : 10 September 2017
 *
 * Solution to Credit Suisse 'Drawing Program'
 */
public class DrawingProgram {

    //Globally used attributes: commands map and canvas (used across all commands)
    public static char[][] CANVAS;
    public static boolean IS_RUNNING = true;
    public static final Map<String, Command> CMD_MAP = new HashMap<String, Command>();

    //creating new command becomes handy, need to only plug a new command class to the map
    static {
        CMD_MAP.put(CreateCanvasCommand.CMD, new CreateCanvasCommand());
        CMD_MAP.put(LineCommand.CMD, new LineCommand());
        CMD_MAP.put(RectangleCommand.CMD, new RectangleCommand());
        CMD_MAP.put(BucketFillCommand.CMD, new BucketFillCommand());
        CMD_MAP.put(HelpCommand.CMD, new HelpCommand());
        CMD_MAP.put(QuitCommand.CMD, new QuitCommand());
    }

    public void launch() {

        Scanner sc = new Scanner(System.in);
        while (IS_RUNNING) {
            try {
                //Print out statement to ask user for input
                System.out.print("enter command: ");
                String line = sc.nextLine().trim();
                StringTokenizer st = new StringTokenizer(line);

                if (!st.hasMoreTokens()) {
                    System.err.println("No command found. Type 'help' to show the list of commands available");
                    continue;
                }

                //First character input is a 'command' character, we'll check if we have it in commands map
                Command cmd = CMD_MAP.get(st.nextToken());
                if (cmd == null) {
                    System.err.println("No command found. Type 'help' to show the list of commands available");
                    continue;
                }

                //Populate the rest of the command arguments (excluding the first character)
                List<String> args = new ArrayList<String>();
                while (st.hasMoreTokens()) {
                    args.add(st.nextToken());
                }
                cmd.execute(args.toArray(new String[0]));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new DrawingProgram().launch();
    }

}
