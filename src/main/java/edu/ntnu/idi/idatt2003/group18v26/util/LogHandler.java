package edu.ntnu.idi.idatt2003.group18v26.util;

/**
 * An interface for handling log messages. 
 * This allows for different implementations of log handlers, 
 * such as writing to a file, sending logs to a remote server, or simply printing to the console.
 */
public interface LogHandler {

  /**
   * Writes a formatted log message. If an exception is provided, it can be logged as well.
   *
   * @param formattedMessage The log message that has been formatted by the Logger class.
   * @param e An optional exception that can be logged
   */
  void write(String formattedMessage, Exception e);
}