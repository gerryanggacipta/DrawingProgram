package command;

import main.DrawingProgram;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

public class BucketFillCommandTest {

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
            new BucketFillCommand().execute(new String[]{"10", "3", "o"});
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
            new RectangleCommand().execute(new String[]{"14", "1", "18", "3"});
            new BucketFillCommand().execute(new String[]{"10", "3", "o"});
            Assert.assertTrue(true);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testInvalidArgsCount() {
        try {
            new BucketFillCommand().execute(new String[]{"10", "3"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIncorrectArgTypes() {
        try {
            new BucketFillCommand().execute(new String[]{"10", "a", "o"});
            Assert.assertTrue(false);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }


    @Test
    public void testXYOutOfCanvas() {
        try {
            new BucketFillCommand().execute(new String[]{"21", "3", "o"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testCharIsX() {
        try {
            new BucketFillCommand().execute(new String[]{"21", "3", "x"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

}
