package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for CsvStockReader.
 */
public class CsvStockReaderTest {
  private CsvStockReader reader;

  @BeforeEach
  void setUp() {
    reader = new CsvStockReader();
  }

  @Test
  void testReadStocksWithValidPath() throws IOException {
    Path path = Path.of("src/test/resources/sp500Test.csv");

    List<Stock> stocks = reader.readStocks(path);

    assertFalse(stocks.isEmpty());
    Stock firstStock = stocks.get(0);
    assertEquals("NVDA", firstStock.getSymbol());
    assertEquals("Nvidia", firstStock.getCompany());
    assertEquals(new BigDecimal("191.27"), firstStock.getSalesPrice());
  }

  @Test
  void testReadStockWithInvalidPathThrowsIoException() throws IOException {
    Path invalidPath = Path.of("src/test/resources/nonexistent.csv");

    IOException exception = assertThrows(IOException.class, () -> reader.readStocks(invalidPath));
    String os = System.getProperty("os.name");
    if (os.contains("Windows")) {
      assertEquals("src\\test\\resources\\nonexistent.csv", exception.getMessage());
    } else {
      assertEquals("src/test/resources/nonexistent.csv", exception.getMessage());
    }
  }

  @Test
  void testParseLineThrowsIllegalArgumentExceptionForInvalidFormat() throws IOException {
    Path tempFile = Files.createTempFile("invalid", ".csv");
    try {
      Files.writeString(tempFile, "Invalid,Stock\n");

      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> reader.readStocks(tempFile));
      assertEquals("Invalid stock data: Invalid,Stock", exception.getMessage());
    } finally {
      Files.deleteIfExists(tempFile);
    }
  }

  @Test
  void testParseLineThrowsIllegalArgumentExceptionForInvalidFormat2() throws IOException {
    Path tempFile = Files.createTempFile("invalid", ".csv");
    try {
      Files.writeString(tempFile, "Invalid,Stock,NaBD\n");

      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> reader.readStocks(tempFile));
      assertEquals("Invalid stock data: Invalid,Stock," 
          + "NaBD Third column must be castable as BigDecimal",
          exception.getMessage());
    } finally {
      Files.deleteIfExists(tempFile);
    }
  }
}
