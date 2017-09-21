package command;

import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidParameterException;

public class CreateCanvasCommandTest {

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
    public void testValidArgs() {
        try {
            new CreateCanvasCommand().execute(new String[]{"20", "4"});
            Assert.assertTrue(true);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testInvalidArgsCount() {
        try {
            new CreateCanvasCommand().execute(new String[]{"20", "4", "c"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNonIntegerArgs() {
        try {
            new CreateCanvasCommand().execute(new String[]{"20c", "4.0"});
            Assert.assertTrue(false);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNonPositiveArgs() {
        try {
            new CreateCanvasCommand().execute(new String[]{"0", "0"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }

        try {
            new CreateCanvasCommand().execute(new String[]{"0", "-1"});
            Assert.assertTrue(false);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

}
