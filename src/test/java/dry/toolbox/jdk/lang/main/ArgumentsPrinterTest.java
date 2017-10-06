package dry.toolbox.jdk.lang.main;

import dry.toolbox.jdk.lang.main.ArgumentsPrinter;
import org.junit.*;

public class ArgumentsPrinterTest {

    @Test
    public void testMainEmpty() {
        ArgumentsPrinter.main(null);
    }

    @Test
    public void testMain() {
        String commandLine = "a b c d";
        String[] args = commandLine.split(" ");
        ArgumentsPrinter.main(args);
    }

}
