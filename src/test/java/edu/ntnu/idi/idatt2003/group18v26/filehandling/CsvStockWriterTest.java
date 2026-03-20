package edu.ntnu.idi.idatt2003.group18v26.filehandling;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvStockWriterTest {
  private CsvStockWriter writer;

  @BeforeEach
  void setUp() {
    writer = new CsvStockWriter();
  }

  @Test
  void testWriteStocksSucceeds() throws IOException {
    Path tempFile = Files.createTempFile("Valid", ".csv");
    try {
        List<Stock> stocks = List.of(
            new Stock("AAPL", "Apple Inc.", new BigDecimal("150.00")),
            new Stock("GOOGL", "Alphabet Inc.", new BigDecimal("2800.00"))
        );
        writer.writeStocks(stocks, tempFile);

        List<String> lines = Files.readAllLines(tempFile);
        assertEquals(4, lines.size());
        assertEquals("# Stock data", lines.get(0));
        assertEquals("# Ticker,Name,Price", lines.get(1));
        assertEquals("AAPL,Apple Inc.,150.00", lines.get(2));
        assertEquals("GOOGL,Alphabet Inc.,2800.00", lines.get(3));
    } finally {
        Files.deleteIfExists(tempFile);
    }
  }
}
