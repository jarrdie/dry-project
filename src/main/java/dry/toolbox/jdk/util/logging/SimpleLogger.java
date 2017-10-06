package dry.toolbox.jdk.util.logging;

public class SimpleLogger {

    private String className;

    public SimpleLogger(String className) {
        this.className = className;
    }

    public void line() {
        System.out.println();
    }

    public void log(String message) {
        System.out.println("[" + className + "] " + message);
    }

}
