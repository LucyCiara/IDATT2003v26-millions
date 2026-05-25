package edu.ntnu.idi.idatt2003.group18v26.model.persistence;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Exchange;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameSerializerTest {
  private GameSerializer serializer;
  private Player player;
  private Exchange exchange;

  @BeforeEach
  void setUp() {
    serializer = new GameSerializer();
    player = new Player("Alice", new BigDecimal("5000.00"));
    Stock aapl = new Stock("AAPL", "Apple Inc.", new BigDecimal("150.00"));
    Stock msft = new Stock("MSFT", "Microsoft Corporation", new BigDecimal("320.00"));
    exchange = new Exchange("Test Market", List.of(aapl, msft));
  }

  @Test
  void toSnapshotExtractsPlayerName() {
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertEquals("Alice", snapshot.playerName);
  }

  @Test
  void toSnapshotExtractsPlayerMoney() {
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertEquals(0, snapshot.playerMoney.compareTo(new BigDecimal("5000.00")));
  }

  @Test
  void toSnapshotExtractsStartingMoney() {
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertEquals(0, snapshot.startingMoney.compareTo(new BigDecimal("5000.00")));
  }

  @Test
  void toSnapshot_extractsCurrentWeek() {
    exchange.advance();
    exchange.advance();

    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    assertEquals(2, snapshot.week);
  }
}
