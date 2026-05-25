package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Interface for reading stock data from a file. Implementations of this interface should provide
 * a method to read a list of Stock objects from a specified file path. 
 * The implementation should handle any necessary parsing and ensure that 
 * the data is read correctly from the file.
 */
public interface StockReader {
  /**
   * Reads a list of Stock objects from a file at the specified path.
   *
   * @param path the file path from which the stock data should be read.
   * @return a list of Stock objects read from the file.
   * @throws IOException if an I/O error occurs while reading from the file.
   */
  List<Stock> readStocks(Path path) throws IOException;
}
