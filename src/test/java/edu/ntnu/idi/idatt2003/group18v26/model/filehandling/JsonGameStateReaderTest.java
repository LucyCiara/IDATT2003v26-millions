package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import edu.ntnu.idi.idatt2003.group18v26.model.persistence.GameSnapshot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class JsonGameStateReaderTest {

  private JsonGameStateReader reader;

  @BeforeEach
  void setUp() {
    reader = new JsonGameStateReader();
  }

  @Test
  void validJson_returnsSnapshot(@TempDir Path tmp) throws IOException {
    Path file = tmp.resolve("save.json");

    Files.writeString(file, """
        {
          "playerName": "Alice",
          "playerMoney": 100.0,
          "startingMoney": 500.0,
          "playerPortfolio": [],
          "week": 1,
          "stockPriceHistory": {},
          "transactions": []
        }
        """);

    GameSnapshot result = reader.readGameState(file);

    assertEquals("Alice", result.playerName);
    assertEquals(0, result.playerMoney.compareTo(new BigDecimal("100.0")));
  }

  @Test
  void fileMissingThrows(@TempDir Path tmp) {
    Path missing = tmp.resolve("missing.json");

    IOException ex = assertThrows(
        IOException.class,
        () -> reader.readGameState(missing));

    assertTrue(ex.getMessage().contains("Save file not found"));
  }

  @Test
  void invalidJsonThrows(@TempDir Path tmp) throws IOException {
    Path file = tmp.resolve("bad.json");
    Files.writeString(file, "{ broken json }");

    IOException ex = assertThrows(
        IOException.class,
        () -> reader.readGameState(file));

    assertTrue(ex.getMessage().contains("Invalid JSON format"));
  }

  @Test
  void readHandlesNullFields(@TempDir Path tmp) throws IOException {
    Path file = tmp.resolve("minimal.json");
    Files.writeString(file, """
        {
          "playerName": "Test",
          "playerPortfolio": [],
          "transactions": []
        }
        """);
    GameSnapshot result = reader.readGameState(file);
    assertNotNull(result.playerName);
    assertNotNull(result.playerPortfolio);
    assertNotNull(result.transactions);
  }

  @Test
  void readHandlesLargeFile(@TempDir Path tmp) throws IOException {
    // Create a large JSON file
    StringBuilder json = new StringBuilder("{");
    json.append("\"playerName\": \"LargeTest\",");
    json.append("\"playerPortfolio\": [");

    for (int i = 0; i < 1000; i++) {
      if (i > 0) {
        json.append(",");
      }
      json.append(String.format(
          "{\"symbol\": \"S%d\", \"quantity\": %d, \"purchasePrice\": 100.0}",
          i, i));
    }
    json.append("],");
    json.append("\"transactions\": []");
    json.append("}");

    Path file = tmp.resolve("large.json");
    Files.writeString(file, json.toString());

    GameSnapshot result = reader.readGameState(file);

    assertEquals("LargeTest", result.playerName);
    assertEquals(1000, result.playerPortfolio.size());
  }

  @Test
  void readHandlesEmptyArrays(@TempDir Path tmp) throws IOException {
    Path file = tmp.resolve("empty_arrays.json");

    Files.writeString(file, """
        {
          "playerName": "Empty",
          "playerMoney": 1000.0,
          "startingMoney": 1000.0,
          "playerPortfolio": [],
          "week": 0,
          "stockPriceHistory": {},
          "transactions": []
        }
        """);

    GameSnapshot result = reader.readGameState(file);

    assertTrue(result.playerPortfolio.isEmpty());
    assertTrue(result.stockPriceHistory.isEmpty());
    assertTrue(result.transactions.isEmpty());
  }

  @Test
  void readThrowsOnMalformedJson(@TempDir Path tmp) throws IOException {
    Path file = tmp.resolve("malformed.json");
    Files.writeString(file, """
        {
          "playerName": "Test"
          "missing comma here"
        }
        """);

    assertThrows(IOException.class, () -> reader.readGameState(file));
  }
}