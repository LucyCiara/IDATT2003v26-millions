package edu.ntnu.idi.idatt2003.group18v26.util;

/**
 * A simple log handler that writes log messages to the console.
 * This is the default log handler used by the Logger class.
 */
public class ConsoleLogHandler implements LogHandler {
  private static final boolean DEBUG = true;

  /**{@inheritDoc} */
  @Override
  public void write(String formattedMessage, Exception e) {
    System.out.println(formattedMessage);
    if (e != null && DEBUG) {
      e.printStackTrace();
    }
  }
}