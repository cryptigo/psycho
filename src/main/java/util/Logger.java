package util;

import java.io.BufferedWriter;
import java.util.Date;

public class Logger {
    private enum LogLevel {
        FATAL, ERROR, WARNING, INFO, DEBUG, TRACE
    }

    private static LogLevel currentLogLevel = LogLevel.INFO;

    public static void setLogLevel(LogLevel level) {
        currentLogLevel = level;
    }

    public static void logTrace(String message) {
        log(LogLevel.TRACE, message);
    }

    public static void logDebug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public static void logInfo(String message) {
        log(LogLevel.INFO, message);
    }

    public static void logWarning(String message) {
        log(LogLevel.WARNING, message);
    }

    public static void logError(String message) {
        log(LogLevel.ERROR, message);
    }

    public static void logException(Exception e) {
        log(LogLevel.ERROR, e.getMessage());
        e.printStackTrace();
    }

    public static void logFatalException(Exception e) {
        log(LogLevel.FATAL, e.getMessage());
        e.printStackTrace();
        assert false : "";
    }

    public static void log(LogLevel level, String message, String format) {
        if (level.ordinal() >= currentLogLevel.ordinal()) {
            String formattedMessage = String.format(format, new Date(), level, message);
            System.out.println(formattedMessage);
        }
    }

    public static void log(LogLevel level, String message) {
        if (level.ordinal() >= currentLogLevel.ordinal()) {
            String formattedMessage = "[" + new Date() + "] " + level + ": " + message;
            System.out.println(formattedMessage);
        }
    }

}
