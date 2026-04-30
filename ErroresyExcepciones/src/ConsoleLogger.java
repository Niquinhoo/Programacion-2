

public final class ConsoleLogger {
    private ConsoleLogger() {
    }

    public static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
