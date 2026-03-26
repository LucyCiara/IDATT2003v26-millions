package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the StockReader interface that reads stock data from a CSV file.
 * The CSV format is expected to include a header line and each stock should be on a new line with
 * the format:
 *      symbol,company,price
 * 
 * Lines starting with '#' are treated as comments and are ignored during reading.
 * The reader ensures that the stock data is correctly parsed and handles any necessary I/O
 * operations.
 */
public class CsvStockReader implements StockReader {

  /**
   * {@inheritDoc}
   * This method reads stock data from a CSV file.
   */
  @Override
  public List<Stock> readStocks(Path path) throws IOException {
    ParameterValidator.objectChecker(path, "path");
    List<Stock> stocks = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(path)){
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isBlank() || line.startsWith("#")) {
          continue;
        }
        stocks.add(parseLine(line));
      }
      ParameterValidator.objectChecker(stocks, "stocks");
    }
    return stocks; 
  }

  /**
   * Parses a line of CSV data into a Stock object.
   * 
   * @param line the line of CSV data to parse.
   * @return a Stock object created from the parsed data.
   * @throws IllegalArgumentException if the line is not in the expected format or contains invalid
   *      data.
   */
  private Stock parseLine(String line) {
    ParameterValidator.stringChecker(line, "line");
    String[] parts = line.split(",");
    if (parts.length != 3) {
      throw new IllegalArgumentException("Invalid stock data: " + line);
    }
    String symbol = parts[0].trim();
    String company = parts[1].trim();
    BigDecimal price = new BigDecimal(parts[2].trim());

    return new Stock(symbol, company, price);
  }
}
