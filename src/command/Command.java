package command;

import static main.DrawingProgram.CANVAS;

/*
* Parent of any command. This may share some common methods to all commands.
* */
abstract public class Command {
    abstract public void execute(String[] args);

    public void printCanvas() {
        for (int i = 0; i < CANVAS.length; i++) {
            for (int j = 0; j < CANVAS[0].length; j++) {
                System.out.print(CANVAS[i][j]);
            }
            System.out.println();
        }
    }
}
