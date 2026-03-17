package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;


public class ExchangeTest {
  private static final String TEST_EXCHANGE_NAME = "Wallstreet";
  private static final String WRONG_STOCK_SYMBOL = "WGC";
  private static final String TEST_PLAYER_NAME = "John Doe";
  private BigDecimal testStartingMoney;
  private BigDecimal testQuantity;
  private BigDecimal testPrice;
  private Stock testStock;
  private List<Stock> testStocks;

  @BeforeEach
  void init() {
    testStartingMoney = new BigDecimal(201913);
    testQuantity = new BigDecimal(20.17);
    testPrice = new BigDecimal(20.16);
    testStock = new Stock("TTC", "TestINC", new BigDecimal(20.0204));
    testStocks = new ArrayList<Stock>();
    testStocks.add(testStock);
  }

  @Test
  public void constructorThrowsNoException() {
    assertDoesNotThrow(() -> new Exchange("Wallstreet", testStocks));
  }

  @Test
  public void nullNameThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Exchange(null, this.testStocks));
    assertEquals("name can't be null", exception.getMessage());
  }

  @Test
  public void emptyNameThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Exchange("", this.testStocks));
    assertEquals("name can't be blank", exception.getMessage());
  }

  @Test
  public void nullStocksThrowsExpectedException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Exchange(TEST_EXCHANGE_NAME, null));
    assertEquals("stocks can't be null", exception.getMessage());
  }

  @Test
  public void getNameGetsName() {
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    assertEquals(TEST_EXCHANGE_NAME, exchange.getName());
  }

  @Test
  public void advanceIncrementsWeek() {
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    assertEquals(0, exchange.getWeek());
    exchange.advance();
    assertEquals(1, exchange.getWeek());
  }

  @Test
  public void advanceAddsNewPrice() {
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    BigDecimal initialPrice = testStock.getSalesPrice();
    assertEquals(initialPrice, exchange.getStock(testStock.getSymbol()).getSalesPrice());
    exchange.advance();
    assertNotEquals(initialPrice, exchange.getStock(testStock.getSymbol()).getSalesPrice());
  }

  @Test
  public void hasStockIsOnlyTrueOnStock() {
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    assertTrue(exchange.hasStock(testStock.getSymbol()));
    assertFalse(exchange.hasStock(WRONG_STOCK_SYMBOL));
  }

  @Test
  public void findStockFindsOnlyStockWithSearchTerm() {
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    assertEquals(testStocks.size(), exchange.findStocks("TestINC").size());
    assertEquals(testStock, exchange.findStocks("TestINC").get(0));
    assertEquals(testStock, exchange.findStocks("TTC").get(0));
    assertEquals(0, exchange.findStocks(WRONG_STOCK_SYMBOL).size());
  }

  @Test
  public void findStocksFindsStockWithWronglyCapitalizedSearchTerm() {
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    assertEquals(testStock, exchange.findStocks("test").get(0));
    assertEquals(testStock, exchange.findStocks("ttc").get(0));
  }

  @Test
  public void findStocksFindsStockWithIncompleteSearchTerm() {
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    assertEquals(testStock, exchange.findStocks("test").get(0));
    assertEquals(testStock, exchange.findStocks("ttc").get(0));
  }

  @Test
  public void buyStockAddsStockAndTransactionToPlayer() {
    Player player = new Player(TEST_PLAYER_NAME, testStartingMoney);
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    assertEquals(0, player.getPortfolio().getShares().size());
    Transaction transaction = exchange.buy(testStock.getSymbol(), testQuantity, player);
    assertEquals(transaction, player.getTransactionArchive().getTransactions(exchange.getWeek()).getLast());
    assertEquals(1, player.getPortfolio().getShares().size());
  }

  @Test
  public void sellStockRemovesStockAndTransactionFromPlayer() {
    Player player = new Player(TEST_PLAYER_NAME, testStartingMoney);
    Exchange exchange = new Exchange(TEST_EXCHANGE_NAME, testStocks);
    Share share = new Share(testStock, testQuantity, testPrice);
    player.getPortfolio().addShare(share);
    assertEquals(1, player.getPortfolio().getShares().size());
    Transaction transaction = exchange.sell(share, player);
    assertEquals(transaction, player.getTransactionArchive().getTransactions(exchange.getWeek()).getLast());
    assertEquals(0, player.getPortfolio().getShares().size());
  }
}
