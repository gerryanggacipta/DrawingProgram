package command;

import main.DrawingProgram;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

public class RectangleCommandTest {

    @Before
    public void init() {
        new CreateCanvasCommand().execute(new String[]{"20", "4"});
    }

    @Test //Not really necessary, but for the sake of test coverage
    public void testToString() {
        String desc = new QuitCommand().toString();
        if (desc == null || desc.isEmpty()) {
            Assert.assertTrue(false);
        }
        System.out.println(desc);
        Assert.assertTrue(true);
    }

    @Test
    public void testNullCanvas() {
        try {
            DrawingProgram.CANVAS = null;
            new RectangleCommand().execute(new String[]{"14", "1", "18", "3"});
            Assert.assertTrue(false);
        } catch (UnsupportedOperationException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidArgs() {
        try {
            new RectangleCommand().execute(new String[]{"14", "1", "18", "3"});
            Assert.assertTrue(true);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testInvalidArgsCount() {
        try {
            new RectangleCommand().execute(new String[]{"14", "1", "18"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNonIntegerArgs() {
        try {
            new RectangleCommand().execute(new String[]{"14a", "1.0", "18", "3"});
            Assert.assertTrue(false);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }


    @Test
    public void testXYOutOfCanvas() {
        try {
            new RectangleCommand().execute(new String[]{"21", "5", "21", "2"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
