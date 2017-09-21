package command;

import main.DrawingProgram;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

public class LineCommandTest {
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
            new LineCommand().execute(new String[]{"1", "2", "6", "2"});
            Assert.assertTrue(false);
        } catch (UnsupportedOperationException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidArgs() {
        try {
            new LineCommand().execute(new String[]{"1", "2", "6", "2"});
            new LineCommand().execute(new String[]{"6", "3", "6", "4"});
            Assert.assertTrue(true);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testInvalidArgsCount() {
        try {
            new LineCommand().execute(new String[]{"1", "2", "6"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNonIntegerArgs() {
        try {
            new LineCommand().execute(new String[]{"1c", "2.0", "6", "2"});
            Assert.assertTrue(false);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testXYOutOfCanvas() {
        try {
            new LineCommand().execute(new String[]{"21", "5", "21", "2"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testCreateDiagonalLine() {
        try {
            new LineCommand().execute(new String[]{"2", "2", "3", "3"});
            Assert.assertTrue(false);
        } catch (UnsupportedOperationException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

}
