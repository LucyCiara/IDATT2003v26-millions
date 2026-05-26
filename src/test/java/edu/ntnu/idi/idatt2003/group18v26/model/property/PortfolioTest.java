package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Portfolio.
 */
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
    assertEquals("share can't be null", exception.getMessage());
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
    assertEquals("share can't be null", exception.getMessage());
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
    assertEquals("symbol can't be null", exception.getMessage());
  }

  @Test
  void getShareWithBlankThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> portfolio.getShare(""));
    assertEquals("symbol can't be blank", exception.getMessage());
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
    assertEquals("share can't be null", exception.getMessage());
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

  @Test
  void getSharesBySymbolReturnsSortedAlphabetically() {
    Share shareZ = new Share(new Stock("ZEBRA", "Z Corp", new BigDecimal("100")), 
        new BigDecimal("1"), new BigDecimal("100"));
    Share shareA = new Share(new Stock("APPLE", "A Corp", new BigDecimal("100")), 
        new BigDecimal("1"), new BigDecimal("100"));
    Share shareM = new Share(new Stock("MANGO", "M Corp", new BigDecimal("100")), 
        new BigDecimal("1"), new BigDecimal("100"));

    portfolio.addShare(shareZ);
    portfolio.addShare(shareA);
    portfolio.addShare(shareM);

    List<Share> sorted = portfolio.getSharesBySymbol();

    assertEquals(3, sorted.size());
    assertEquals("APPLE", sorted.get(0).stock().getSymbol());
    assertEquals("MANGO", sorted.get(1).stock().getSymbol());
    assertEquals("ZEBRA", sorted.get(2).stock().getSymbol());
  }

  @Test
  void getSharesByCompanyReturnsSortedAlphabetically() {
    Share shareZ = new Share(new Stock("Z", "Zebra Corp", new BigDecimal("100")), 
        new BigDecimal("1"), new BigDecimal("100"));
    Share shareA = new Share(new Stock("A", "Apple Inc", new BigDecimal("100")), 
        new BigDecimal("1"), new BigDecimal("100"));
    Share shareM = new Share(new Stock("M", "Mango Ltd", new BigDecimal("100")), 
        new BigDecimal("1"), new BigDecimal("100"));

    portfolio.addShare(shareZ);
    portfolio.addShare(shareA);
    portfolio.addShare(shareM);

    List<Share> sorted = portfolio.getSharesByCompany();

    assertEquals(3, sorted.size());
    assertEquals("Apple Inc", sorted.get(0).stock().getCompany());
    assertEquals("Mango Ltd", sorted.get(1).stock().getCompany());
    assertEquals("Zebra Corp", sorted.get(2).stock().getCompany());
  }

  @Test
  void getSharesByQuantityReturnsSortedNumerically() {
    Share share10 = new Share(new Stock("A", "A",
        new BigDecimal("100")), new BigDecimal("10"), new BigDecimal("100"));
    Share share5 = new Share(new Stock("B", "B",
        new BigDecimal("100")), new BigDecimal("5"), new BigDecimal("100"));
    Share share20 = new Share(new Stock("C", "C",
        new BigDecimal("100")), new BigDecimal("20"), new BigDecimal("100"));

    portfolio.addShare(share10);
    portfolio.addShare(share5);
    portfolio.addShare(share20);

    List<Share> sorted = portfolio.getSharesByQuantity();

    assertEquals(3, sorted.size());
    assertEquals(new BigDecimal("5"), sorted.get(0).quantity());
    assertEquals(new BigDecimal("10"), sorted.get(1).quantity());
    assertEquals(new BigDecimal("20"), sorted.get(2).quantity());
  }

  @Test
  void getSharesByPurchasePriceReturnsSortedNumerically() {
    Share share50 = new Share(new Stock("A", "A",
        new BigDecimal("50")), new BigDecimal("1"), new BigDecimal("50"));
    Share share100 = new Share(new Stock("B", "B",
        new BigDecimal("100")), new BigDecimal("1"), new BigDecimal("100"));
    Share share75 = new Share(new Stock("C", "C",
        new BigDecimal("75")), new BigDecimal("1"), new BigDecimal("75"));

    portfolio.addShare(share100);
    portfolio.addShare(share50);
    portfolio.addShare(share75);

    List<Share> sorted = portfolio.getSharesByPurchasePrice();

    assertEquals(3, sorted.size());
    assertEquals(new BigDecimal("50"), sorted.get(0).purchasePrice());
    assertEquals(new BigDecimal("75"), sorted.get(1).purchasePrice());
    assertEquals(new BigDecimal("100"), sorted.get(2).purchasePrice());
  }

  @Test
  void getSharesByCurrentValueReturnsSortedNumerically() {
    Share share225 = new Share(new Stock("A", "A",
        new BigDecimal("75")), new BigDecimal("3"), new BigDecimal("75"));
    Share share500 = new Share(new Stock("B", "B",
        new BigDecimal("50")), new BigDecimal("10"), new BigDecimal("50"));
    Share share750 = new Share(new Stock("C", "C",
        new BigDecimal("150")), new BigDecimal("5"), new BigDecimal("150"));

    portfolio.addShare(share500);
    portfolio.addShare(share225);
    portfolio.addShare(share750);

    List<Share> sorted = portfolio.getSharesByCurrentValue();

    assertEquals(3, sorted.size());
    assertEquals("A", sorted.get(0).stock().getSymbol());
    assertEquals("B", sorted.get(1).stock().getSymbol());
    assertEquals("C", sorted.get(2).stock().getSymbol());
  }

  @Test
  void searchBySymbolFindsCorrectShare() {
    portfolio.addShare(testShare);
    portfolio.addShare(testShare2);

    List<Share> result = portfolio.search("TTC");

    assertEquals(1, result.size());
    assertTrue(result.contains(testShare));
    assertFalse(result.contains(testShare2));
  }

  @Test
  void searchByCompanyNameFindsCorrectShare() {
    portfolio.addShare(testShare);
    portfolio.addShare(testShare2);

    List<Share> result = portfolio.search("TestINC");

    assertEquals(1, result.size());
    assertTrue(result.contains(testShare));
  }

  @Test
  void searchIsCaseInsensitive() {
    portfolio.addShare(testShare);

    List<Share> resultLower = portfolio.search("ttc");
    List<Share> resultUpper = portfolio.search("TTC");
    List<Share> resultMixed = portfolio.search("TtC");

    assertEquals(1, resultLower.size());
    assertEquals(1, resultUpper.size());
    assertEquals(1, resultMixed.size());
    assertTrue(resultLower.contains(testShare));
    assertTrue(resultUpper.contains(testShare));
    assertTrue(resultMixed.contains(testShare));
  }

  @Test
  void searchReturnsPartialMatches() {
    portfolio.addShare(testShare); 
    portfolio.addShare(testShare2);

    List<Share> result = portfolio.search("TT");

    assertEquals(1, result.size());
    assertTrue(result.contains(testShare));
  }

  @Test
  void searchWithNoMatchesReturnsEmptyList() {
    portfolio.addShare(testShare);
    portfolio.addShare(testShare2);

    List<Share> result = portfolio.search("NONEXISTENT");

    assertEquals(0, result.size());
  }

  @Test
  void searchByQuantityFindsCorrectShare() {
    Share shareQty7 = new Share(new Stock("Q1", "Qty Corp",
        new BigDecimal("50")), new BigDecimal("7"), new BigDecimal("150"));
    Share shareQty13 = new Share(new Stock("Q2", "Qty Ltd",
        new BigDecimal("60")), new BigDecimal("13"), new BigDecimal("160"));

    portfolio.addShare(shareQty7);
    portfolio.addShare(shareQty13);

    List<Share> result = portfolio.search("7");

    assertEquals(1, result.size());
    assertTrue(result.contains(shareQty7));
    assertFalse(result.contains(shareQty13));
  }

  @Test
  void searchByPurchasePriceFindsCorrectShare() {
    portfolio.addShare(testShare); 
    portfolio.addShare(testShare2); 
    portfolio.addShare(wrongShare); 

    List<Share> result = portfolio.search("200");

    assertEquals(1, result.size());
    assertTrue(result.contains(testShare2));
    assertFalse(result.contains(testShare));
    assertFalse(result.contains(wrongShare));
  }

  @Test
  void searchByTotalValueFindsCorrectShare() {
    Share shareVal1000a = new Share(new Stock("V1", "Value A",
        new BigDecimal("125")), new BigDecimal("8"), new BigDecimal("125"));
    Share shareVal1000b = new Share(new Stock("V2", "Value B",
        new BigDecimal("250")), new BigDecimal("4"), new BigDecimal("250"));
    Share shareVal666 = new Share(new Stock("V3", "Value C",
        new BigDecimal("333")), new BigDecimal("2"), new BigDecimal("333"));

    portfolio.addShare(shareVal1000a);
    portfolio.addShare(shareVal1000b);
    portfolio.addShare(shareVal666);

    List<Share> result = portfolio.search("1005");

    assertEquals(2, result.size());
    assertTrue(result.contains(shareVal1000a));
    assertTrue(result.contains(shareVal1000b));
    assertFalse(result.contains(shareVal666));
  }

  @Test
  void searchWithMultipleMatchesReturnsAll() {
    Share shareNotStartsWithT = new Share(
        new Stock("BELL", "Bell Corp", new BigDecimal("100")),
        new BigDecimal("5"),
        new BigDecimal("100"));

    portfolio.addShare(testShare);
    portfolio.addShare(shareNotStartsWithT);
    portfolio.addShare(testShare2);

    List<Share> result = portfolio.search("T");

    assertEquals(2, result.size());
    assertTrue(result.contains(testShare));
    assertFalse(result.contains(shareNotStartsWithT));
    assertTrue(result.contains(testShare2));
  }
}
