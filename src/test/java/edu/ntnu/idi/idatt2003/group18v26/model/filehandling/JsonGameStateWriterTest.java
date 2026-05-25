package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import edu.ntnu.idi.idatt2003.group18v26.model.persistence.GameSnapshot;
import edu.ntnu.idi.idatt2003.group18v26.model.persistence.ShareSnapshot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonGameStateWriterTest {

  private JsonGameStateWriter writer;

  @BeforeEach
  void setUp() {
    writer = new JsonGameStateWriter();
  }

  private GameSnapshot createSnapshot() {
    GameSnapshot snapshot = new GameSnapshot();
    snapshot.playerName = "TestPlayer";
    snapshot.playerMoney = new BigDecimal("1234.56");
    snapshot.startingMoney = new BigDecimal("5000.00");
    snapshot.playerPortfolio = List.of(new ShareSnapshot("AAPL", new BigDecimal("1"), new BigDecimal("150.00")));
    snapshot.week = 2;
    snapshot.stockPriceHistory = Map.of("AAPL", List.of(new BigDecimal("150.00")));
    snapshot.transactions = List.of();
    return snapshot;
  }

  @Test
  void writeCreatesDirectoriesAndFile(@TempDir Path tmp) throws IOException {
    GameSnapshot snapshot = createSnapshot();

    Path file = tmp.resolve("saves/testplayer/save1.json");

    writer.writeGameState(snapshot, file);

    assertTrue(Files.exists(file));

    String content = Files.readString(file);
    assertTrue(content.contains("TestPlayer"));
    assertTrue(content.contains("playerMoney"));
  }

  @Test
  void writeThenReadRoundTripMatches(@TempDir Path tmp) throws IOException {
    GameSnapshot snapshot = createSnapshot();
    snapshot.playerName = "RoundTrip";

    Path file = tmp.resolve("saves/roundtrip/save.json");

    writer.writeGameState(snapshot, file);

    GameSnapshot read = new JsonGameStateReader().readGameState(file);

    assertEquals(snapshot.playerName, read.playerName);
    assertEquals(0, snapshot.playerMoney.compareTo(read.playerMoney));
    assertEquals(snapshot.week, read.week);
    assertEquals(snapshot.playerPortfolio.size(), read.playerPortfolio.size());
    assertEquals(snapshot.stockPriceHistory.keySet(), read.stockPriceHistory.keySet());
  }

  @Test
  void writeHandlesEmptyPortfolio(@TempDir Path tmp) throws IOException {
    GameSnapshot snapshot = createSnapshot();
    snapshot.playerPortfolio = List.of();

    Path file = tmp.resolve("saves/empty/save.json");
    writer.writeGameState(snapshot, file);
    GameSnapshot read = new JsonGameStateReader().readGameState(file);
    assertTrue(read.playerPortfolio.isEmpty());
  }

  @Test
  void writeHandlesLargePortfolio(@TempDir Path tmp) throws IOException {
    GameSnapshot snapshot = createSnapshot();
    List<ShareSnapshot> largePortfolio = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      largePortfolio.add(new ShareSnapshot(
          "STOCK" + i,
          new BigDecimal(i + 1),
          new BigDecimal("100.00")));
    }
    snapshot.playerPortfolio = largePortfolio;

    Path file = tmp.resolve("saves/large/save.json");
    writer.writeGameState(snapshot, file);

    GameSnapshot read = new JsonGameStateReader().readGameState(file);
    assertEquals(100, read.playerPortfolio.size());
  }

  @Test
  void writePreservesBigDecimalPrecision(@TempDir Path tmp) throws IOException {
    GameSnapshot snapshot = createSnapshot();
    snapshot.playerMoney = new BigDecimal("1234.56789123");

    Path file = tmp.resolve("saves/precision/save.json");
    writer.writeGameState(snapshot, file);

    GameSnapshot read = new JsonGameStateReader().readGameState(file);
    assertEquals(0, snapshot.playerMoney.compareTo(read.playerMoney));
  }

  @Test
  void writeNullSnapshotThrowsException(@TempDir Path tmp) {
    Path file = tmp.resolve("test.json");

    assertThrows(IllegalArgumentException.class, () -> writer.writeGameState(null, file));
  }

  @Test
  void writeNullPathThrowsException() {
    GameSnapshot snapshot = createSnapshot();
    assertThrows(IllegalArgumentException.class, () -> writer.writeGameState(snapshot, null));
  }

  @Test
  void writeCreatesNestedDirectories(@TempDir Path tmp) throws IOException {
    GameSnapshot snapshot = createSnapshot();
    Path file = tmp.resolve("saves/player/save/1.json");

    writer.writeGameState(snapshot, file);

    assertTrue(Files.exists(file));
    assertTrue(Files.isRegularFile(file));
  }
}