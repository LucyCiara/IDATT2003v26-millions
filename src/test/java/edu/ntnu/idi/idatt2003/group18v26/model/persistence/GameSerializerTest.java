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
  void toSnapshotExtractsCurrentWeek() {
    exchange.advance();
    exchange.advance();

    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertEquals(2, snapshot.week);
  }

  @Test
  void toSnapshotExtractsStockPriceHistory() {
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    assertNotNull(snapshot.stockPriceHistory);
    assertTrue(snapshot.stockPriceHistory.containsKey("AAPL"));
    assertTrue(snapshot.stockPriceHistory.containsKey("MSFT"));

    assertFalse(snapshot.stockPriceHistory.get("AAPL").isEmpty());
    assertFalse(snapshot.stockPriceHistory.get("MSFT").isEmpty());
  }

  @Test
  void toSnapshotCapturesStockPriceAfterAdvancedWeeks() {
    BigDecimal initialAaplPrice = exchange.getStock("AAPL").getSalesPrice();

    exchange.advance();
    BigDecimal newAaplPrice = exchange.getStock("AAPL").getSalesPrice();

    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    List<BigDecimal> aaplHistory = snapshot.stockPriceHistory.get("AAPL");
    assertEquals(2, aaplHistory.size());
    assertEquals(0, aaplHistory.get(0).compareTo(initialAaplPrice));
    assertEquals(0, aaplHistory.get(1).compareTo(newAaplPrice));
  }

  @Test
  void toSnapshotAllFieldsNonNull() {
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertNotNull(snapshot.playerName);
    assertNotNull(snapshot.playerMoney);
    assertNotNull(snapshot.startingMoney);
    assertNotNull(snapshot.playerPortfolio);
    assertNotNull(snapshot.stockPriceHistory);
    assertNotNull(snapshot.transactions);
  }

  @Test
  void toSnapshotPreservesBigDecimalPrecision() {
    Player precisePlayer = new Player("Bob", new BigDecimal("1234.567890"));
    GameSnapshot snapshot = serializer.toSnapshot(precisePlayer, exchange);
    assertEquals(0, snapshot.playerMoney.compareTo(new BigDecimal("1234.567890")));
    assertEquals(0, snapshot.startingMoney.compareTo(new BigDecimal("1234.567890")));
  }

  @Test
  void toSnapshotExtractsEmptyPortfolio() {
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertNotNull(snapshot.playerPortfolio);
    assertTrue(snapshot.playerPortfolio.isEmpty());
  }

  @Test
  void toSnapshotExtractsEmptyTransactionArchive() {
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertNotNull(snapshot.transactions);
    assertTrue(snapshot.transactions.isEmpty());
  }

  @Test
  void toSnapshotConvertsPurchaseToUppercase() {
    exchange.buy(exchange.getStock("AAPL").getSymbol(), new BigDecimal("10.00"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertEquals(1, snapshot.transactions.size());
    assertEquals("PURCHASE", snapshot.transactions.get(0).type);
  }

  @Test
  void toSnapshotConvertsSaleToUppercase() {
    exchange.buy(exchange.getStock("AAPL").getSymbol(), new BigDecimal("10.00"), player);
    exchange.sell(player.getPortfolio().getShare("AAPL"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);
    assertEquals(2, snapshot.transactions.size());
    assertEquals("SALE", snapshot.transactions.get(1).type);
  }

  @Test
  void toSnapshotExtractsShareSymbol() {
    exchange.buy("AAPL", new BigDecimal("10.00"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    assertEquals(1, snapshot.playerPortfolio.size());
    assertEquals("AAPL", snapshot.playerPortfolio.get(0).symbol);
  }

  @Test
  void toSnapshotExtractsShareQuantity() {
    exchange.buy("AAPL", new BigDecimal("10.00"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    ShareSnapshot share = snapshot.playerPortfolio.get(0);
    assertEquals(0, new BigDecimal("10.00").compareTo(share.quantity));
  }

  @Test
  void toSnapshotExtractsMultipleShares() {
    exchange.buy("AAPL", new BigDecimal("10.00"), player);
    exchange.buy("MSFT", new BigDecimal("5.00"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    assertEquals(2, snapshot.playerPortfolio.size());
  }

  @Test
  void toSnapshotExtractsTransactionSymbol() {
    exchange.buy("AAPL", new BigDecimal("10.00"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    assertEquals("AAPL", snapshot.transactions.get(0).symbol);
  }

  @Test
  void toSnapshotExtractsTransactionQuantityAndPrice() {
    exchange.buy("AAPL", new BigDecimal("10.00"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    TransactionSnapshot trans = snapshot.transactions.get(0);
    assertEquals(0, new BigDecimal("10.00").compareTo(trans.quantity));
    assertTrue(trans.totalPrice.compareTo(BigDecimal.ZERO) > 0);
  }

  @Test 
  void toSnapshotExtractsMultipleTransactionsOverWeeks() {
    exchange.buy("AAPL", new BigDecimal("10.00"), player);
    exchange.advance();
    exchange.buy("MSFT", new BigDecimal("5.00"), player);
    GameSnapshot snapshot = serializer.toSnapshot(player, exchange);

    assertEquals(2, snapshot.transactions.size());
    assertEquals(0, snapshot.transactions.get(0).week);
    assertEquals(1, snapshot.transactions.get(1).week);
  }

  @Test
  void toSnapshotHandlesEmptyExchange() {
    Exchange emptyExchange = new Exchange("Empty", List.of());
    GameSnapshot snapshot = serializer.toSnapshot(player, emptyExchange);
    assertTrue(snapshot.stockPriceHistory.isEmpty());
  }
}
