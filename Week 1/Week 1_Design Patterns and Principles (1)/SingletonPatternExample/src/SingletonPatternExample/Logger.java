package SingletonPatternExample;

public class Logger {
    // Private static instance of the same class
    private static Logger instance;

    // Private constructor to restrict instantiation from other classes
    private Logger() {
        // Initialization code (if any)
    }

    // Public method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Method to log messages
    public void log(String message) {
        System.out.println("Log message: " + message);
    }
    
    public static void main(String[] args) {
        // Attempt to get two instances of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Verify that both instances are the same
        if (logger1 == logger2) {
            System.out.println("Both logger instances are the same.");
        } else {
            System.out.println("Logger instances are different.");
        }

        // Use the logger to log a message
        logger1.log("This is a log message.");
    }
}
