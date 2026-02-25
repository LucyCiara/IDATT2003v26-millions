package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;

public class PurchaseCalculatorTest {
  private static final Stock TEST_STOCK =
      new Stock("TST", "TestCompany", new BigDecimal("50.00"));

  private static final BigDecimal TEST_QUANTITY =
      new BigDecimal("10");

  private static final BigDecimal TEST_PURCHASE_PRICE =
      new BigDecimal("100.00");

  private static final Share TEST_SHARE =
      new Share(TEST_STOCK, TEST_QUANTITY, TEST_PURCHASE_PRICE);

  private static final Share WRONG_SHARE =
      new Share(TEST_STOCK, new BigDecimal("5"), new BigDecimal("200.00"));

  private void constructorTest(Share share, boolean negativeTest) {
    boolean exceptionThrown = negativeTest;
    try {
      new PurchaseCalculator(share);
    } catch (Exception e) {
      exceptionThrown = !negativeTest;
    } finally {
      assertFalse(exceptionThrown);
    }
  }

  @Test
  public void constructorThrowsNoException() {
    this.constructorTest(TEST_SHARE, false);
  }

  @Test
  public void constructorWithNullShareThrowsException() {
    this.constructorTest(null, true);
  }


}
