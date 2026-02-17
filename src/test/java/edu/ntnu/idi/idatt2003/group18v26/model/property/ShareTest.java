package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ShareTest {
  private static final Stock TEST_STOCK = new Stock("TTC", "TestINC", new BigDecimal("20051910.142113020518"));
  private static final BigDecimal TEST_QUANTITY = new BigDecimal("2005.1910");
  private static final BigDecimal TEST_PURCHASE_PRICE = new BigDecimal("20051910.142113020518");
  private static final Stock WRONG_STOCK = new Stock("WGC", "WrongINC", new BigDecimal("2318151407.142113020518"));
  private static final BigDecimal WRONG_QUANTITY = new BigDecimal("231815.1407");
  private static final BigDecimal WRONG_PURCHASE_PRICE = new BigDecimal("2318151407.142113020518");

  private void constructorTest(Stock stock, BigDecimal quantity, BigDecimal purchase_price, boolean negativeTest) {
    boolean exceptionThrown = negativeTest;
    try {
      new Share(stock, quantity, purchase_price);
    } catch (Exception e) {
      exceptionThrown = !negativeTest;
    } finally {
      assertFalse(exceptionThrown);
    }
  }

  @Test
  public void constructorThrowsNoException() {
    this.constructorTest(TEST_STOCK, TEST_QUANTITY, TEST_PURCHASE_PRICE, false);
  }

  @Test
  public void constructorWithNullStockThrowsException() {
    this.constructorTest(null, TEST_QUANTITY, TEST_PURCHASE_PRICE, true);
  }

  @Test
  public void constructorWithNullQuantityThrowsException() {
    this.constructorTest(TEST_STOCK, null, TEST_PURCHASE_PRICE, true);
  }

  @Test
  public void constructorWithNullPurchasePriceThrowsException() {
    this.constructorTest(TEST_STOCK, TEST_QUANTITY, null, true);
  }

  @Test
  public void constructorWithZeroQuantityThrowsException() {
    this.constructorTest(TEST_STOCK, new BigDecimal(0), TEST_PURCHASE_PRICE, true);
  }

  @Test
  public void constructorWithNegativeQuantityThrowsException() {
    this.constructorTest(TEST_STOCK, new BigDecimal(-1), TEST_PURCHASE_PRICE, true);
  }

  @Test
  public void constructorWithZeroPurchasePriceThrowsException() {
    this.constructorTest(TEST_STOCK, TEST_QUANTITY, new BigDecimal(0), true);
  }

  @Test
  public void constructorWithNegativePurchasePriceThrowsException() {
    this.constructorTest(TEST_STOCK, TEST_QUANTITY, new BigDecimal(-1), true);
  }

  private void getMethodComparer(Share testShare, boolean negativeTest) {
    if (testShare.stock().equals(TEST_STOCK) && testShare.quantity().equals(TEST_QUANTITY)
        && testShare.purchasePrice().equals(TEST_PURCHASE_PRICE)) {
      assertFalse(negativeTest);
    }
  }

  @Test
  public void getMethodsReturnCorrectInformation() {
    this.getMethodComparer(new Share(TEST_STOCK, TEST_QUANTITY, TEST_PURCHASE_PRICE), false);
  }

  @Test
  public void getStockReturnsOnlyCorrectStock() {
    this.getMethodComparer(new Share(WRONG_STOCK, TEST_QUANTITY, TEST_PURCHASE_PRICE), true);
  }

  @Test
  public void getQuantityReturnsOnlyCorrectQuantity() {
    this.getMethodComparer(new Share(TEST_STOCK, WRONG_QUANTITY, TEST_PURCHASE_PRICE), true);
  }

  @Test
  public void getPurchasePriceReturnsOnlyCorrectPurchasePrice() {
    this.getMethodComparer(new Share(TEST_STOCK, TEST_QUANTITY, WRONG_PURCHASE_PRICE), true);
  }

}
