package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Implementation of the StockWriter interface that writes stock data to a CSV file.
 * The CSV format includes a header line and each stock is written on a new line with the format:
 * symbol,company,price
 * Lines starting with '#' are treated as comments and are not written to the file.
 * The writer ensures that the stock data is correctly formatted and handles any necessary I/O
 * operations.
 */
public class CsvStockWriter implements StockWriter {

  /**
   * {@inheritDoc} 
   * This method writes stock data to a CSV file.
   */
  @Override
  public void writeStocks(List<Stock> stocks, Path path) throws IOException {
    ParameterValidator.objectChecker(stocks, "stocks");
    ParameterValidator.objectChecker(path, "path");
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      writer.write("# Stock data");
      writer.newLine();
      writer.write("# Ticker,Name,Price");
      writer.newLine();

      for (Stock stock : stocks) {
        writer.write(formatStock(stock));
        writer.newLine();
      }
    }
  }

  /**
   * Formats a Stock object into a CSV line.
   * 
   * @param stock the Stock object to format.
   * @return a string representing the Stock in CSV format.
   */
  private String formatStock(Stock stock) {
    ParameterValidator.objectChecker(stock, "stock");
    return String.format("%s,%s,%s", 
    stock.getSymbol(), stock.getCompany(), stock.getSalesPrice().toPlainString());
  }
}
