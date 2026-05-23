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
}