package command;

import java.security.InvalidParameterException;

import static main.DrawingProgram.CANVAS;

/**
 * This command is to create (reset) canvas. Width and Height are +ve integer values
 * Usage: 'C width height'
 */
public class CreateCanvasCommand extends Command {

    public static final String CMD = "C";

    @Override
    public void execute(String[] args) {

        if (args.length != 2) {
            throw new InvalidParameterException("Incorrect number of arguments. Example: C 20 4");
        }

        try {
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);

            if (width <= 0 || height <= 0) {
                throw new InvalidParameterException("Width and height should be positive values!");
            }

            //Add 2 more rows & 2 columns due the the bordering
            width += 2;
            height += 2;

            //Loop through the 2D array, and draw with border
            CANVAS = new char[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == 0 || i == height - 1) {
                        CANVAS[i][j] = '-';
                    } else if (j == 0 || j == width - 1) {
                        CANVAS[i][j] = '|';
                    } else {
                        CANVAS[i][j] = ' ';
                    }
                }
            }


        } catch (NumberFormatException e) {
            throw new NumberFormatException("Arguments must be in integer format!");
        }

        printCanvas();
    }

    @Override
    public String toString() {
        return "Command     : C w h\n" +
                "Description : Create a new canvas of width w and height h. Example: C 20 4";
    }
}
