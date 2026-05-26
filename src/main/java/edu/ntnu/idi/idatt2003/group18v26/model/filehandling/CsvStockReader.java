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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the StockReader interface that reads stock data from a CSV
 * file.
 * The CSV format is expected to include a header line and each stock should be
 * on a new line with
 * the format:
 * symbol,company,price
 * Lines starting with '#' are treated as comments and are ignored during
 * reading.
 * The reader ensures that the stock data is correctly parsed and handles any
 * necessary I/O
 * operations.
 */
public class CsvStockReader implements StockReader {
  private static final Logger logger
      = LoggerFactory.getLogger(CsvStockReader.class);

  /**
   * {@inheritDoc}
   * This method reads stock data from a CSV file.
   */
  @Override
  public List<Stock> readStocks(Path path) throws IOException {
    ParameterValidator.objectChecker(path, "path");
    logger.info("Reading stocks from file: {}", path);
    List<Stock> stocks = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      String line;
      int lineCount = 0;
      while ((line = reader.readLine()) != null) {
        if (line.isBlank() || line.startsWith("#")) {
          continue;
        }
        try {
          stocks.add(parseLine(line));
          lineCount++;
        } catch (IllegalArgumentException e) {
          logger.error("Failed to parse CSV line: {}", line, e);
          throw e;
        }
      }
      ParameterValidator.objectChecker(stocks, "stocks");
      logger.info("Successfully loaded {} stocks from file", lineCount);
    } catch (IOException e) {
      logger.error("IO error reading from file: {}", path, e);
      throw e;
    }
    return stocks;
  }

  /**
   * Parses a line of CSV data into a Stock object.
   *
   * @param line the line of CSV data to parse.
   * @return a Stock object created from the parsed data.
   * @throws IllegalArgumentException if the line is not in the expected format or
   *                                  contains invalid
   *                                  data.
   */
  private Stock parseLine(String line) {
    ParameterValidator.stringChecker(line, "line");
    String[] parts = line.split(",");
    if (parts.length != 3) {
      throw new IllegalArgumentException("Invalid stock data: " + line);
    }

    String symbol = parts[0].trim();
    String company = parts[1].trim();
    BigDecimal price = null;
    try {
      price = new BigDecimal(parts[2].trim());
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Invalid stock data: " + line + " Third column must be castable as BigDecimal");
    }

    return new Stock(symbol, company, price);
  }
}
