package edu.ntnu.idi.idatt2003.group18v26.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple logger class that supports different log levels 
 * (INFO, WARN, ERROR) and allows for custom log handlers.
 * By default, it uses a ConsoleLogHandler to write log messages to the console. 7
 */
public class Logger {
  private static final List<LogHandler> handlers = new ArrayList<>();
  private static final boolean DEBUG = true;
  private static final String LEVEL_INFO = "INFO";
  private static final String LEVEL_WARN = "WARN";
  private static final String LEVEL_ERROR = "ERROR";
  private static final String LEVEL_DEBUG = "DEBUG";
  private static final DateTimeFormatter formatter 
      = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  static {
    handlers.add(new ConsoleLogHandler());
  }

  /**
   * Logs an informational message. 
   * This is used for general information about the application's operation.
   * @param message The informational message to be logged
   */
  public static void info(String message) {
    log(LEVEL_INFO, message, null);
  }

  /**
   * Logs a warning message.
   * This is used for messages that indicate a potential issue or deviation from normal operation.
   * @param message The warning message to be logged
   */
  public static void warn(String message) {
    log(LEVEL_WARN, message, null);
  }

  /**
   * Logs an error message along with an exception.
   * This is used for messages that indicate a serious problem or failure in the application.
   * @param message The error message to be logged
   * @param e The exception associated with the error
   */
  public static void error(String message, Exception e) {
    log(LEVEL_ERROR, message, e);
  }

  /**
   * Logs a debug message.
   * This is used for messages that provide detailed information for debugging purposes.
   * @param message The debug message to be logged
   */
  public static void debug(String message) {
    if (DEBUG) {
      log(LEVEL_DEBUG, message, null);
    }
  }
  
  /**
   * Formats the log message with a timestamp and log level, 
   * then sends it to all registered log handlers.
   * 
   * @param level The log level (e.g., INFO, WARN, ERROR, DEBUG)
   * @param message The log message to be logged
   * @param e An optional exception that can be logged (used for ERROR level)
   */
  private static void log(String level, String message, Exception e) {
    String timestamp = LocalDateTime.now().format(formatter);
    String formatted = "[" + timestamp + "] [" + level + "] " + message;
    
    handlers.forEach(handler -> handler.write(formatted, e));
  }
  
  /**
   * Adds a custom log handler to the logger.
   * @param handler The log handler to be added
   */
  public static void addHandler(LogHandler handler) {
    handlers.add(handler);
  }
}
