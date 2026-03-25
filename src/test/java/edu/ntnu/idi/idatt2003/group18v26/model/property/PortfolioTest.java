package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;

public class PortfolioTest {

  private Portfolio portfolio;
  private Share testShare;
  private Share testShare2;
  private Share wrongShare;

  @BeforeEach
  void setUp() {
    portfolio = new Portfolio();

    testShare = new Share(
        new Stock("TTC", "TestINC", new BigDecimal("100")),
        new BigDecimal("10"),
        new BigDecimal("100"));

    testShare2 = new Share(
        new Stock("ABC", "AnotherINC", new BigDecimal("200")),
        new BigDecimal("5"),
        new BigDecimal("200"));

    wrongShare = new Share(
        new Stock("WRG", "WrongINC", new BigDecimal("300")),
        new BigDecimal("1"),
        new BigDecimal("300"));
  }

  @Test
  void constructorCreatesEmptyPortfolio() {
    assertTrue(portfolio.getShares().isEmpty());
  }

  @Test
  void addShareAddsShareSuccessfully() {
    assertTrue(portfolio.addShare(testShare));
    assertTrue(portfolio.contains(testShare));
  }

  @Test
  void addNullShareThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> portfolio.addShare(null));
    assertEquals("share cannot be null", exception.getMessage());
  }

  @Test
  void removeShareRemovesExistingShare() {
    portfolio.addShare(testShare);
    assertTrue(portfolio.removeShare(testShare));
    assertFalse(portfolio.contains(testShare));
  }

  @Test
  void removeWrongShareReturnsFalse() {
    portfolio.addShare(testShare);
    assertFalse(portfolio.removeShare(wrongShare));
  }

  @Test
  void removeNullShareThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> portfolio.removeShare(null));
    assertEquals("share cannot be null", exception.getMessage());
  }

  @Test
  void getShareReturnsCorrectShare() {
    portfolio.addShare(testShare);
    Share result = portfolio.getShare("TTC");
    assertEquals(testShare, result);
  }

  @Test
  void getShareReturnsNullIfNotFound() {
    portfolio.addShare(testShare);
    assertNull(portfolio.getShare("NOT_EXISTING"));
  }

  @Test
  void getShareWithNullThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> portfolio.getShare(null));
    assertEquals("symbol cannot be null or blank", exception.getMessage());
  }

  @Test
  void getShareWithBlankThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> portfolio.getShare(""));
    assertEquals("symbol cannot be null or blank", exception.getMessage());
  }

  @Test
  void getSharesReturnsAllShares() {
    portfolio.addShare(testShare);
    portfolio.addShare(testShare2);

    List<Share> shares = portfolio.getShares();

    assertEquals(2, shares.size());
    assertTrue(shares.contains(testShare));
    assertTrue(shares.contains(testShare2));
  }

  @Test
  void containsReturnsTrueForExistingShare() {
    portfolio.addShare(testShare);
    assertTrue(portfolio.contains(testShare));
  }

  @Test
  void containsReturnsFalseForNonExistingShare() {
    portfolio.addShare(testShare);
    assertFalse(portfolio.contains(wrongShare));
  }

  @Test
  void containsNullThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> portfolio.contains(null));
    assertEquals("The share cannot be null", exception.getMessage());
  }

  @Test
  void getNetWorthGetsExpectedSum() {
    portfolio.addShare(testShare);
    BigDecimal worth1 = new SaleCalculator(testShare).calculateTotal();
    assertEquals(0, portfolio.getNetWorth().compareTo(worth1));
    portfolio.addShare(testShare2);
    BigDecimal worth2 = worth1.add(new SaleCalculator(testShare2).calculateTotal());
    assertEquals(0, portfolio.getNetWorth().compareTo(worth2));
  }
}
