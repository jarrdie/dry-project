package dev.dryproject.jdk.lang.main;

public class ArgumentsPrinter {

    public static void main(String[] args) {
        if (!isEmpty(args)) {
            printArguments(args);
        }
    }

    private static boolean isEmpty(String[] args) {
        return args == null || args.length == 0;
    }

    private static void printArguments(String[] args) {
        System.out.println("-Arguments:");
        for (int i = 0; i < args.length; i++) {
            System.out.println("  args[" + i + "]: " + args[i]);
        }
    }

}
