package util;

import java.util.logging.*;

public class ColoredLogger {
    private static final Logger LOGGER = Logger.getLogger(ColoredLogger.class.getName());
    private static final ConsoleHandler consoleHandler = new ConsoleHandler();

    static {
        // Set the formatter for console handler
        consoleHandler.setFormatter(new ColoredLogFormatter());

        // Set the console handler to only log messages at the INFO level or above
        consoleHandler.setLevel(Level.INFO);

        // Add the console handler to the logger
        LOGGER.addHandler(consoleHandler);
    }

    public static void info(String message) {
        LOGGER.log(Level.INFO, message);
    }

    public static void warning(String message) {
        LOGGER.log(Level.WARNING, message);
    }

    public static void error(String message) {
        LOGGER.log(Level.SEVERE, message);
    }

    public static void config(String message) {
        LOGGER.log(Level.CONFIG, message);
    }

    public static void fine(String message) {
        LOGGER.log(Level.FINE, message);
    }


    private static class ColoredLogFormatter extends Formatter {
        private static final String ANSI_RESET = "\u001B[0m";
        private static final String ANSI_BOLD = "\u001B[1m";

        private static final String ANSI_BLACK = "\u001B[30m";
        private static final String ANSI_BOLD_BLACK = ANSI_BOLD + ANSI_BLACK;

        private static final String ANSI_WHITE = "\u001B[47m";
        private static final String ANSI_BOLD_WHITE = ANSI_BOLD + ANSI_WHITE;

        private static final String ANSI_RED = "\u001B[31m";
        private static final String ANSI_BOLD_RED = ANSI_BOLD + ANSI_RED;

        private static final String ANSI_GREEN = "\u001B[32m";
        private static final String ANSI_BOLD_GREEN = ANSI_BOLD + ANSI_GREEN;

        private static final String ANSI_BLUE = "\u001B[44m";
        private static final String ANSI_BOLD_BLUE = ANSI_BOLD + ANSI_BLUE;

        private static final String ANSI_CYAN = "\u001B[46m";
        private static final String ANSI_BOLD_CYAN = ANSI_BOLD + ANSI_CYAN;

        private static final String ANSI_YELLOW = "\u001B[33m";
        private static final String ANSI_BOLD_YELLOW = ANSI_BOLD + ANSI_YELLOW;

        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();

            Level level = record.getLevel();
            String levelName = level.getName();
            String color;

            if (level == Level.SEVERE) {
                color = ANSI_RED;
            } else if (level == Level.WARNING) {
                color = ANSI_YELLOW;
            } else if (level == Level.INFO) {
                color = ANSI_GREEN;
            } else if (level == Level.CONFIG) {
                color = ANSI_BLUE;
            } else if (level == Level.FINE) {
                color = ANSI_CYAN;
            } else {
                color = ANSI_BLACK;
            }

            sb.append(color)
                    .append("[").append(levelName).append("] ")
                    .append(record.getMessage())
                    .append(ANSI_RESET)
                    .append("\n");

            return sb.toString();
        }

    }
}
