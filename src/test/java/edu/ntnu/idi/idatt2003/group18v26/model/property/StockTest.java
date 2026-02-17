package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class StockTest {
  private static final String TEST_SYMBOL = "TTC";
  private static final String TEST_COMPANY = "TestINC";
  private static final BigDecimal TEST_PRICE = new BigDecimal("20051910.142113020518");
  private static final String WRONG_SYMBOL = "WGC";
  private static final String WRONG_COMPANY = "WrongINC";
  private static final BigDecimal WRONG_PRICE = new BigDecimal("2318151407.142113020518");

  private void constructorTest(String symbol, String company, BigDecimal price, boolean negativeTest) {
    boolean exceptionThrown = negativeTest;
    try {
      new Stock(symbol, company, price);
    } catch (Exception e) {
      exceptionThrown = !negativeTest;
    } finally {
      assertFalse(exceptionThrown);
    }
  }

  @Test
  public void constructorThrowsNoException() {
    this.constructorTest(TEST_SYMBOL, TEST_COMPANY, TEST_PRICE, false);
  }

  @Test
  public void constructorWithNullSymbolThrowsException() {
    this.constructorTest(null, TEST_COMPANY, TEST_PRICE, true);
  }

  @Test
  public void constructorWithNullCompanyThrowsException() {
    this.constructorTest(TEST_SYMBOL, null, TEST_PRICE, true);
  }

  @Test
  public void constructorWithNullPriceThrowsException() {
    this.constructorTest(TEST_SYMBOL, TEST_COMPANY, null, true);
  }

  @Test
  public void constructorWithEmptySymbolThrowsException() {
    this.constructorTest("", TEST_COMPANY, TEST_PRICE, true);
  }

  @Test
  public void constructorWithEmptyCompanyThrowsException() {
    this.constructorTest(TEST_SYMBOL, "", TEST_PRICE, true);
  }

  @Test
  public void constructorWithZeroPurchasePriceThrowsException() {
    this.constructorTest(TEST_SYMBOL, TEST_COMPANY, new BigDecimal(0), true);
  }

  @Test
  public void constructorWithNegativePurchasePriceThrowsException() {
    this.constructorTest(TEST_SYMBOL, TEST_COMPANY, new BigDecimal(-1), true);
  }

  private void getMethodComparer(Stock testStock, boolean negativeTest) {
    if (testStock.getSymbol().equals(TEST_SYMBOL) && testStock.getCompany().equals(TEST_COMPANY)
        && testStock.getSalesPrice().equals(TEST_PRICE)) {
      assertFalse(negativeTest);
    }
  }

  @Test
  public void getMethodsReturnCorrectInformation() {

    this.getMethodComparer(new Stock(TEST_SYMBOL, TEST_COMPANY, TEST_PRICE), false);
  }

  @Test
  public void getSymbolReturnsOnlyCorrectSymbol() {
    this.getMethodComparer(new Stock(WRONG_SYMBOL, TEST_COMPANY, TEST_PRICE), true);
  }

  @Test
  public void getCompanyReturnsOnlyCorrectCompany() {
    this.getMethodComparer(new Stock(TEST_SYMBOL, WRONG_COMPANY, TEST_PRICE), true);
  }

  @Test
  public void getPriceReturnsOnlyCorrectPrice() {
    this.getMethodComparer(new Stock(TEST_SYMBOL, TEST_COMPANY, WRONG_PRICE), true);
  }

  @Test
  public void getPriceGetsNewPriceAfterAdding() {
    Stock testStock = new Stock(TEST_SYMBOL, TEST_COMPANY, WRONG_PRICE);
    testStock.addNewSalesPrice(TEST_PRICE);
    this.getMethodComparer(testStock, false);
  }

  @Test
  public void addNewNullSalesPriceThrowsException() {
    Stock testStock = new Stock(TEST_SYMBOL, TEST_COMPANY, TEST_PRICE);
    boolean exceptionThrown = false;
    try {
      testStock.addNewSalesPrice(null);
    } catch (Exception e) {
      exceptionThrown = true;
    } finally {
      assertTrue(exceptionThrown);
    }
  }

  @Test
  public void toStringThrowsNoException() {
    Stock testStock = new Stock(TEST_SYMBOL, TEST_COMPANY, TEST_PRICE);
    boolean exceptionThrown = false;
    boolean matches = false;
    try {
      matches = testStock.toString().equals(TEST_SYMBOL + " (" + TEST_COMPANY + ") - current price: " + TEST_PRICE);
    } catch (Exception e) {
      exceptionThrown = true;
    } finally {
      assertFalse(exceptionThrown || !matches);
    }
  }
}
