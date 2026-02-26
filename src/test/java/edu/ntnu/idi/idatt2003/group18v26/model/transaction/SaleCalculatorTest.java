package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;

public class SaleCalculatorTest {

  private static final Stock TEST_STOCK =
      new Stock("TST", "TestCompany", new BigDecimal("150.00"));

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
      new SaleCalculator(share);
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

  @Test
  public void calculateGrossReturnsCorrectValue() {
    SaleCalculator calculator = new SaleCalculator(TEST_SHARE);
    BigDecimal expectedGross = TEST_STOCK.getSalesPrice().multiply(TEST_QUANTITY);
    assertTrue(calculator.calculateGross().compareTo(expectedGross) == 0);
  }

private void calculationComparer(SaleCalculator calculator, Share share, boolean negativeTest) {
  BigDecimal gross = share.stock().getSalesPrice().multiply(share.quantity());
  BigDecimal commission = gross.multiply(new BigDecimal("0.01"));
  BigDecimal purchaseCost = share.purchasePrice().multiply(share.quantity());
  BigDecimal profit = gross.subtract(purchaseCost);

  BigDecimal tax = BigDecimal.ZERO;
  if (profit.compareTo(BigDecimal.ZERO) > 0) {
    tax = profit.multiply(new BigDecimal("0.3"));
  }

  BigDecimal total = gross.subtract(commission).subtract(tax);

  boolean correct =
      calculator.calculateGross().equals(gross)
      && calculator.calculateCommission().equals(commission)
      && calculator.calculateTax().equals(tax)
      && calculator.calculateTotal().equals(total);

  assertFalse(correct == negativeTest);
}

@Test
public void calculateMethodsReturnCorrectValuesWithProfit() {
  SaleCalculator calculator = new SaleCalculator(TEST_SHARE);
  this.calculationComparer(calculator, TEST_SHARE, false);
}

@Test
  public void calculateMethodsReturnCorrectValuesWithLoss() {
  Stock lossStock = new Stock("LOS", "LossCompany", new BigDecimal("50.00"));
  Share lossShare = new Share(lossStock, TEST_QUANTITY, TEST_PURCHASE_PRICE);

  SaleCalculator calculator = new SaleCalculator(lossShare);
  this.calculationComparer(calculator, lossShare, false);
}

@Test
public void calculateMethodsReturnIncorrectValuesWithWrongShare() {
  SaleCalculator calculator = new SaleCalculator(WRONG_SHARE);
  this.calculationComparer(calculator, TEST_SHARE, true);
}

}