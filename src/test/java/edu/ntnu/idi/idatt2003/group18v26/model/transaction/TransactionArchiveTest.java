package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import org.junit.jupiter.api.BeforeEach;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransactionArchiveTest {
  private TransactionArchive transArchive;
  private Stock stockA;
  private Stock stockB;
  private Stock stockC;
  private Share share1;
  private Share share2;
  private Share share3;
  private Transaction transaction1;
  private Transaction transaction2;
  private Transaction transaction3;

  @BeforeEach
  public void setUp() {
    transArchive = new TransactionArchive();
    stockA = new Stock("StockA", "CompanyA", new BigDecimal("100.00"));
    stockB = new Stock("StockB", "CompanyB", new BigDecimal("200.00"));
    stockC = new Stock("StockC", "CompanyC", new BigDecimal("300.00"));
    share1 = new Share(stockA, new BigDecimal("100.00"), new BigDecimal("50.00"));
    share2 = new Share(stockB, new BigDecimal("200.00"), new BigDecimal("100.00"));
    share3 = new Share(stockC, new BigDecimal("300.00"), new BigDecimal("150.00"));
    transaction1 = new Purchase(share1, 10);
    transaction2 = new Sale(share2, 10);
    transaction3 = new Purchase(share3, 20);
    transArchive.add(transaction1);
    transArchive.add(transaction2);
  }


  @Test
  void testAddValidTransactionReturnsTrue() {
    assertTrue(transArchive.add(transaction3));
  }

  @Test
  void testAddNullTransactionThrowsException() {
    IllegalArgumentException exception = 
    assertThrows(IllegalArgumentException.class, () -> transArchive.add(null));
    assertEquals("transaction can't be null", exception.getMessage());
  }

  @Test
  void testIsEmptyReturnsFalseWhenTransactionsExist() {
    assertFalse(transArchive.isEmpty());
  }

  @Test
  void testIsEmptyReturnsTrueWhenNoTransactionsExist() {
    TransactionArchive emptyArchive = new TransactionArchive();
    assertTrue(emptyArchive.isEmpty());
  }

  @Test
  void testGetTransactionsWithValidWeekReturnsTransactions() {
    assertEquals(2, transArchive.getTransactions(10).size());
  }

  @Test
  void testGetTransactionsWithNegativeWeekThrowsException() {
    IllegalArgumentException exception = 
    assertThrows(IllegalArgumentException.class, () -> transArchive.getTransactions(-1));
    assertEquals("week can't be negative", exception.getMessage());
  }

  @Test
  void testGetPurchasesWithValidWeekReturnsPurchases() {
    assertEquals(1, transArchive.getPurchases(10).size());
  }

  @Test
  void testGetPurchasesWithNegativeWeekThrowsException() {
    IllegalArgumentException exception = 
    assertThrows(IllegalArgumentException.class, () -> transArchive.getPurchases(-1));
    assertEquals("week can't be negative", exception.getMessage());
  }

  @Test
  void testGetPurchasesWithNoPurchasesForWeekReturnsEmptyList() {
    assertTrue(transArchive.getPurchases(20).isEmpty());
  }

  @Test
  void testGetSalesWithValidWeekReturnsSales() {
    assertEquals(1, transArchive.getSales(10).size());
  }

  @Test
  void testGetSalesWithNegativeWeekThrowsException() {
    IllegalArgumentException exception = 
    assertThrows(IllegalArgumentException.class, () -> transArchive.getSales(-1));
    assertEquals("week can't be negative", exception.getMessage());
  }

  @Test
  void testGetPurchasesWithNoSalesForWeekReturnsEmptyList() {
    assertTrue(transArchive.getSales(20).isEmpty());
  }

  @Test
  void testGetTransactionsWithNoTransactionsForWeekReturnsEmptyList() {
    assertTrue(transArchive.getTransactions(20).isEmpty());
  }

  @Test
  void testCountDistinctWeeksReturnsCorrectCount() {
    assertEquals(1, transArchive.countDistinctWeeks());
  }

  @Test
  void testCountDistinctWeeksWithMultipleWeeksReturnsCorrectCount() {
    Transaction transaction4 = new Purchase(share1, 20);
    Transaction transaction5 = new Sale(share2, 25);
    transArchive.add(transaction4);
    transArchive.add(transaction5);
    assertEquals(3, transArchive.countDistinctWeeks());
  }

  @Test
  void testCountDistinctWeeksWithNoTransactionsReturnsZero() {
    TransactionArchive emptyArchive = new TransactionArchive();
    assertEquals(0, emptyArchive.countDistinctWeeks());
  }
}