package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PortfolioTest {
  private static final Share TEST_SHARE = new Share(new Stock("TTC", "TestINC", new BigDecimal("20051910.142113020518")),
      new BigDecimal("2005.1910"), new BigDecimal("20051910.142113020518"));

  private static final Share TEST_SHARE2 = new Share(new Stock("T2C", "TestCO", new BigDecimal("200519102.142113020518")),
      new BigDecimal("2005.19102"), new BigDecimal("200519102.142113020518"));

  private static final Share WRONG_SHARE = new Share(
      new Stock("WGC", "WrongINC", new BigDecimal("2318151407.142113020518")), new BigDecimal("2005.1910"),
      new BigDecimal("2318151407.142113020518"));

  @Test
  public void constructorThrowsNoException() {
    boolean exceptionThrown = false;
    try {
      new Portfolio();
    } catch (Exception e) {
      exceptionThrown = true;
    } finally {
      assertFalse(exceptionThrown);
    }
  }

  private void addShareTest(Share share, boolean negativeTest) {
    Portfolio portfolio = new Portfolio();
    boolean exceptionThrown = negativeTest;
    boolean success = true;
    try {
      success = portfolio.addShare(share);
    } catch (Exception e) {
      exceptionThrown = !negativeTest;
    } finally {
      assertFalse(exceptionThrown || !success);
    }
  }

  @Test
  public void addShareThrowsNoException() {
    this.addShareTest(TEST_SHARE, false);
  }

  @Test
  public void addNullShareThrowsException() {
    this.addShareTest(null, true);
  }

  private void removeShareTest(Share share, boolean exceptionTest, boolean failTest) {
    Portfolio portfolio = new Portfolio();
    portfolio.addShare(TEST_SHARE);
    boolean exceptionThrown = exceptionTest;
    boolean success = true;
    try {
      success = (portfolio.removeShare(share) ^ failTest);
    } catch (Exception e) {
      exceptionThrown = !exceptionTest;
    } finally {
      assertFalse(exceptionThrown || !success);
    }
  }

  @Test
  public void removeShareThrowsNoException() {
    this.removeShareTest(TEST_SHARE, false, false);
  }

  @Test
  public void removeNullShareThrowsException() {
    this.removeShareTest(null, true, false);
  }

  @Test
  public void removeWrongShareReturnsFalse() {
    this.removeShareTest(WRONG_SHARE, false, true);
  }

  private void getShareTest(String symbol, boolean exceptionTest, boolean failTest) {
    Portfolio portfolio = new Portfolio();
    boolean matching = true;
    boolean exceptionThrown = exceptionTest;
    try {
      portfolio.addShare(TEST_SHARE);
      Share outputShare = portfolio.getShare(symbol);
      matching = (TEST_SHARE.equals(outputShare) ^ failTest);
    } catch (Exception e) {
      exceptionThrown = !exceptionTest;
    } finally {
      assertFalse(exceptionThrown || !matching);
    }
  }

  @Test
  public void getShareThrowsNoException() {
    this.getShareTest(TEST_SHARE.stock().getSymbol(), false, false);
  }

  @Test
  public void getNullShareThrowsException() {
    this.getShareTest(null,  true, false);
  }

  @Test
  public void getEmptyShareThrowsException() {
    this.getShareTest("",  true, false);
  }

  @Test
  public void getWrongShareDoesNotMatch() {
    this.getShareTest(WRONG_SHARE.stock().getSymbol(), false, true);
  }

  private void getSharesTest(List<Share> shares, boolean failTest) {
    Portfolio portfolio = new Portfolio();
    boolean matching = true;
    boolean exceptionThrown = false;
    try {
      shares.forEach(
              portfolio::addShare);
      matching = (portfolio.getShares().equals(Arrays.asList(new Share[] {TEST_SHARE, TEST_SHARE2})) ^ failTest);
    } catch (Exception e) {
      exceptionThrown = true;
    }
    assertFalse(exceptionThrown || !matching);
  }

  @Test
  public void getSharesThrowsNoException() {
    this.getSharesTest(Arrays.asList(new Share[] { TEST_SHARE, TEST_SHARE2 }), false);
  }

  @Test
  public void getWrongSharesDoesNotMatch() {
    this.getSharesTest(Arrays.asList(new Share[] { WRONG_SHARE }), true);
  }

  private void containsTest(Share share, boolean exceptionTest, boolean failTest) {
    Portfolio portfolio = new Portfolio();
    boolean success = true;
    boolean exceptionThrown = exceptionTest;
    try {
      portfolio.addShare(TEST_SHARE);
      success = (portfolio.contains(share) ^ failTest);
    } catch (Exception e) {
      exceptionThrown = !exceptionTest;
    } finally {
      assertFalse(exceptionThrown || !success);
    }
  }

  @Test
  public void containsThrowsNoException() {
    this.containsTest(TEST_SHARE, false, false);
  }

  @Test
  public void containsNullThrowsException() {
    this.containsTest(null, true, false);
  }

  @Test
  public void containsWrongShareReturnsFalse() {
    this.containsTest(WRONG_SHARE, false, true);
  }

}
