package command;

import org.junit.Assert;
import org.junit.Test;

public class HelpCommandTest {

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
    public void testExecute() {
        try {
            new HelpCommand().execute(null);
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Assert.assertTrue(false);
        }
    }
}
