package command;

import java.security.InvalidParameterException;

import static main.DrawingProgram.CANVAS;

/**
 * This command is to create a new line from (x1,y1) to (x2,y2). Currently only
 * horizontal or vertical lines are supported. Horizontal and vertical lines
 * will be drawn using the 'x' character.
 * <p>
 * Usage    : 'L x1 y1 x2 y2'
 * Example  :
 * <p>
 * enter command: L 1 2 6 2
 * ----------------------
 * |                    |
 * |xxxxxx              |
 * |                    |
 * |                    |
 * ----------------------
 */
public class LineCommand extends Command {

    public static final String CMD = "L";

    @Override
    public void execute(String[] args) {

        if (CANVAS == null) {
            throw new UnsupportedOperationException("Please create canvas first");
        }

        if (args.length != 4) {
            throw new InvalidParameterException("Incorrect number of arguments. Example: L 1 2 6 2");
        }

        try {
            int x1 = Integer.parseInt(args[0]);
            int y1 = Integer.parseInt(args[1]);
            int x2 = Integer.parseInt(args[2]);
            int y2 = Integer.parseInt(args[3]);

            if (x1 <= 0 || x2 <= 0 || x1 >= CANVAS[0].length - 1 || x2 >= CANVAS[0].length - 1 ||
                    y1 <= 0 || y2 <= 0 || y1 >= CANVAS.length - 1 || y2 >= CANVAS.length - 1) {
                throw new InvalidParameterException("Coordinates are out of canvas!");
            }

            if (x1 != x2 && y1 != y2) {
                throw new UnsupportedOperationException("Currently only horizontal or vertical lines are supported");
            }

            //paint a new line vertically
            if (x1 == x2) {
                int y = (y1 < y2) ? y1 : y2;
                int length = Math.abs(y1 - y2);
                for (int i = y; i <= y + length; i++) {
                    CANVAS[i][x1] = 'x';
                }
            //paint a new line horizontally
            } else if (y1 == y2) {
                int x = (x1 < x2) ? x1 : x2;
                int length = Math.abs(x1 - x2);
                for (int i = x; i <= x + length; i++) {
                    CANVAS[y1][i] = 'x';
                }
            }


        } catch (NumberFormatException e) {
            throw new NumberFormatException("Arguments must be in integer format!");
        }

        printCanvas();
    }

    @Override
    public String toString() {
        return "Command     : L 1 2 6 2\n" +
                "Description : Create a new line from (x1,y1) to (x2,y2). Horizontal and vertical lines\n" +
                "             will be drawn using the 'x' character. Example: L 1 2 6 2";
    }
}
