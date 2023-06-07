package util.log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public class ConsoleLogger {
    private static final String LOG_FORMAT = "%1$tF %1$tT [%4$s] %2$s - %5$s%6$s%n";

    private static Logger logger;

    static {
        // Initialize the logger
        logger = Logger.getLogger(ConsoleLogger.class.getName());
        logger.setUseParentHandlers(false);

        // Create console handler and set the log format
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format(LOG_FORMAT,
                        new java.util.Date(record.getMillis()),
                        record.getSourceClassName(),
                        record.getLoggerName(),
                        record.getLevel().getLocalizedName(),
                        record.getMessage(),
                        formatThrowable(record.getThrown())
                );
            }
        });

        // Set the console handler level and add it to the logger
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }

    public static void logError(String message) {
        logger.severe(message);
    }

    public static void logException(Exception exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);
    }

    private static String formatThrowable(Throwable throwable) {
        if (throwable == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        try (java.io.StringWriter sw = new java.io.StringWriter();
             java.io.PrintWriter pw = new java.io.PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            sb.append(sw.toString());
        } catch (IOException ignored) {
        }
        return sb.toString();
    }
}
