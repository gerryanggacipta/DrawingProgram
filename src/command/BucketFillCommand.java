package command;

import java.security.InvalidParameterException;

import static main.DrawingProgram.CANVAS;

/**
 * Fill the entire area connected to (x,y) with "colour" c. The
 * behaviour of this is the same as that of the "bucket fill" tool in paint
 * programs. Two assumptions were made:
 * - If (x,y) is on existing 'x' character, we ignore the fill.
 * - Character 'x' cannot be used as it is reserved for rectangle/line
 * <p>
 * Usage: 'B x y c'
 * <p>
 * Example:
 * <p>
 * enter command: B 10 3 o
 * ----------------------
 * |oooooooooooooxxxxxoo|
 * |xxxxxxooooooox   xoo|
 * |     xoooooooxxxxxoo|
 * |     xoooooooooooooo|
 * ----------------------
 */
public class BucketFillCommand extends Command {

    public static final String CMD = "B";

    @Override
    public void execute(String[] args) {

        if (CANVAS == null) {
            throw new UnsupportedOperationException("Please create canvas first");
        }

        if (args.length != 3) {
            throw new InvalidParameterException("Incorrect number of arguments. Example: B 10 3 o");
        }

        try {
            int x = Integer.parseInt(args[0]); //col
            int y = Integer.parseInt(args[1]); //row
            char c = args[2].charAt(0);

            if ('x' == c) {
                throw new InvalidParameterException("Character 'x' is reserved. Please use any other character");
            }

            if (x <= 0 || x >= CANVAS[0].length - 1 || y <= 0 || y >= CANVAS.length - 1) {
                throw new InvalidParameterException("Coordinate (x,y) is out of canvas!");
            }

            if (CANVAS[y][x] != 'x') {//assumption: ignored when 'fill' is on a line/rectangle
                for (int i = 1; i < CANVAS.length - 1; i++) {
                    for (int j = 1; j < CANVAS[0].length - 1; j++) {

                        //We loop through the canvas. For each pixel, we check if it can create an open path
                        //to (x,y). isConnectedToXY is a recursive method to achieve this.
                        if (isConnectedToXY(i, j, x, y, new boolean[CANVAS.length][CANVAS[i].length])) {
                            CANVAS[i][j] = c;
                        }

                    }
                }
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Arguments must be in integer format!");
        }
        printCanvas();
    }

    private boolean isConnectedToXY(int i, int j, int x, int y, boolean[][] isVisitedArr) {

        //mark this cell as 'visited'
        isVisitedArr[i][j] = true;

        //recursive search finally managed to find (x,y). The path exists!
        if (i == y && j == x) {
            return true;
        }

        //search hits an 'x' (line/rectangle). Retreat...go find other way!
        if (CANVAS[i][j] == 'x') {
            return false;
        }

        //do NOT go back to patch which has been visited

        //check above
        if (i - 1 > 0 && !isVisitedArr[i - 1][j]) {
            boolean isConnectedFromAbove = isConnectedToXY(i - 1, j, x, y, isVisitedArr);
            if (isConnectedFromAbove) {
                return true;
            }
        }

        //check below
        if (i + 1 < CANVAS.length - 1 && !isVisitedArr[i + 1][j]) {
            boolean isConnectedFromBelow = isConnectedToXY(i + 1, j, x, y, isVisitedArr);
            if (isConnectedFromBelow) {
                return true;
            }
        }

        //check left
        if (j - 1 > 0 && !isVisitedArr[i][j - 1]) {
            boolean isConnectedFromLeft = isConnectedToXY(i, j - 1, x, y, isVisitedArr);
            if (isConnectedFromLeft) {
                return true;
            }
        }

        //check right
        if (j + 1 < CANVAS[i].length - 1 && !isVisitedArr[i][j + 1]) {
            boolean isConnectedFromRight = isConnectedToXY(i, j + 1, x, y, isVisitedArr);
            if (isConnectedFromRight) {
                return true;
            }
        }

        //no more movement can be made
        return false;

    }

    @Override
    public String toString() {
        return "Command     : B x y c\n" +
                "Description : Fill the entire area connected to (x,y) with \"colour\" c. Example: B 10 3 o";
    }
}
