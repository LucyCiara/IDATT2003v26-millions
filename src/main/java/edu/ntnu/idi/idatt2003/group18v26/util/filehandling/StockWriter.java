package edu.ntnu.idi.idatt2003.group18v26.util.filehandling;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
/**
 * Interface for writing stock data to a file. Implementations of this interface should provide
 * a method to write a list of Stock objects to a specified file path. 
 * The implementation should handle any necessary formatting and ensure 
 * that the data is written correctly to the file.
 */
public interface StockWriter {
  /**
   * Writes a list of Stock objects to a file at the specified path.
   * @param stocks the list of Stock objects to be written to the file
   * @param path the file path where the stock data should be written
   * @throws IOException if an I/O error occurs while writing to the file
   */
  void writeStocks(List<Stock> stocks, Path path) throws IOException;
}
