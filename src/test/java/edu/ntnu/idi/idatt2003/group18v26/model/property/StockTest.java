package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockTest {

  private String testSymbol;
  private String testCompany;
  private BigDecimal testPrice;
  private BigDecimal wrongPrice;

  @BeforeEach
  void setUp() {
    testSymbol = "TTC";
    testCompany = "TestINC";
    testPrice = new BigDecimal("20051910.142113020518");
    wrongPrice = new BigDecimal("2318151407.142113020518");
  }

  private void constructorTest(
      String symbol,
      String company,
      BigDecimal price,
      String expectedMessage) {

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Stock(symbol, company, price)
    );

    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void constructorCreatesValidStock() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);

    assertEquals(testSymbol, stock.getSymbol());
    assertEquals(testCompany, stock.getCompany());
    assertEquals(testPrice, stock.getSalesPrice());
  }

  @Test
  void constructorWithNullSymbolThrowsException() {
    constructorTest(null, testCompany, testPrice, "symbol cannot be blank");
  }

  @Test
  void constructorWithBlankSymbolThrowsException() {
    constructorTest("", testCompany, testPrice, "symbol cannot be blank");
  }

  @Test
  void constructorWithNullCompanyThrowsException() {
    constructorTest(testSymbol, null, testPrice, "company cannot be blank");
  }

  @Test
  void constructorWithBlankCompanyThrowsException() {
    constructorTest(testSymbol, "", testPrice, "company cannot be blank");
  }

  @Test
  void constructorWithNullPriceThrowsException() {
    constructorTest(testSymbol, testCompany, null, "salesPrice cannot be null");
  }

  @Test
  void constructorWithZeroPriceThrowsException() {
    constructorTest(testSymbol, testCompany, BigDecimal.ZERO,
        "salesPrice must be greater than zero");
  }

  @Test
  void constructorWithNegativePriceThrowsException() {
    constructorTest(testSymbol, testCompany, new BigDecimal("-1"),
        "salesPrice must be greater than zero");
  }

  @Test
  void getSymbolReturnsCorrectSymbol() {
      Stock stock = new Stock(testSymbol, testCompany, testPrice);

      assertEquals(testSymbol, stock.getSymbol());
  }

  @Test
  void getCompanyReturnsCorrectCompany() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);

    assertEquals(testCompany, stock.getCompany());
  }

  @Test
  void getSalesPriceReturnsInitialPrice() {
      Stock stock = new Stock(testSymbol, testCompany, testPrice);

      assertEquals(testPrice, stock.getSalesPrice());
  }

  @Test
  void getSalesPriceReturnsUpdatedPriceAfterAddingNewPrice() {
    Stock stock = new Stock(testSymbol, testCompany, wrongPrice);

    stock.addNewSalesPrice(testPrice);

    assertEquals(testPrice, stock.getSalesPrice());
  }



  @Test
  void addNewPriceUpdatesCurrentPrice() {
    Stock stock = new Stock(testSymbol, testCompany, wrongPrice);

    stock.addNewSalesPrice(testPrice);

    assertEquals(testPrice, stock.getSalesPrice());
  }

  @Test
  void addNewNullSalesPriceThrowsException() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);

    IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class,
          () -> stock.addNewSalesPrice(null)
    );

    assertEquals("The new price cannot be null", exception.getMessage());
  }

  @Test
  void addNewNegativeSalesPriceThrowsException() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> stock.addNewSalesPrice(BigDecimal.ZERO)
    );

    assertEquals("The new price must be greater than zero", exception.getMessage());
}

  @Test
  void toStringReturnsCorrectFormat() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);

    String expected =
        testSymbol + " (" + testCompany + ") - current price: " + testPrice;

    assertEquals(expected, stock.toString());
  }
}
