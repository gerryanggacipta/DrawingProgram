package command;

import java.security.InvalidParameterException;

import static main.DrawingProgram.CANVAS;

/**
 * Create a new rectangle, whose upper left corner is (x1,y1) and
 * lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
 * using the 'x' character.
 * <p>
 * Usage    : 'R x1 y1 x2 y2'
 * Example  :
 * <p>
 * enter command: R 14 1 18 3
 * ----------------------
 * |             xxxxx  |
 * |xxxxxx       x   x  |
 * |     x       xxxxx  |
 * |     x              |
 * ----------------------
 */
public class RectangleCommand extends Command {

    public static final String CMD = "R";

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


            int x = (x1 < x2) ? x1 : x2;
            int width = Math.abs(x1 - x2);
            for (int i = x; i <= x + width; i++) {
                CANVAS[y1][i] = 'x';
                CANVAS[y2][i] = 'x';
            }

            int y = (y1 < y2) ? y1 : y2;
            int height = Math.abs(y1 - y2);
            for (int i = y; i <= y + height; i++) {
                CANVAS[i][x1] = 'x';
                CANVAS[i][x2] = 'x';
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Arguments must be in integer format!");
        }

        printCanvas();
    }

    @Override
    public String toString() {
        return "Command     : R x1 y1 x2 y2\n" +
                "Description : Create a new rectangle, whose upper left corner is (x1,y1) and\n" +
                "             lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n" +
                "             using the 'x' character.";
    }
}
