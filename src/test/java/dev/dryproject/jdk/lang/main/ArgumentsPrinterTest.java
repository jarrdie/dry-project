package dev.dryproject.jdk.lang.main;

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
