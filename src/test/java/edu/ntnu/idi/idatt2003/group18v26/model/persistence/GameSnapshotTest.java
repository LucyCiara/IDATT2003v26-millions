package edu.ntnu.idi.idatt2003.group18v26.model.persistence;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class GameSnapshotTest {

  @Test
  void defaultConstructor_createsEmptyObject() {
    GameSnapshot snapshot = new GameSnapshot();

    assertNull(snapshot.playerName);
    assertNull(snapshot.playerMoney);
    assertNull(snapshot.startingMoney);
    assertNull(snapshot.playerPortfolio);
    assertEquals(0, snapshot.week);
    assertNull(snapshot.stockPriceHistory);
    assertNull(snapshot.transactions);
  }

  @Test
  void constructorSetsFieldsCorrectly() {
    GameSnapshot snapshot = new GameSnapshot();
    snapshot.playerName = "Alice";
    snapshot.playerMoney = new BigDecimal("100");
    snapshot.startingMoney = new BigDecimal("500");
    snapshot.playerPortfolio = List.of();
    snapshot.week = 2;
    snapshot.stockPriceHistory = Map.of();
    snapshot.transactions = List.of();

    assertEquals("Alice", snapshot.playerName);
    assertEquals(0, snapshot.playerMoney.compareTo(new BigDecimal("100")));
    assertEquals(2, snapshot.week);
  }
}
