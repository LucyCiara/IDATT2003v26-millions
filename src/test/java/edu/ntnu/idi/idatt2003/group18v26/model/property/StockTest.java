package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockTest {
  private static final int PRICES_TO_ADD = 6;
  private String testSymbol;
  private String testCompany;
  private BigDecimal testPrice;
  private BigDecimal wrongPrice;
  private List<BigDecimal> prices;

  @BeforeEach
  void setUp() {
    this.testSymbol = "TTC";
    this.testCompany = "TestINC";
    this.testPrice = new BigDecimal("20051910.142113020518");
    this.wrongPrice = new BigDecimal("2318151407.142113020518");

    Random random = new Random();
    this.prices = new ArrayList<BigDecimal>();
    for (Double price : random.doubles().limit(PRICES_TO_ADD).toArray()) {
      this.prices.add(new BigDecimal(price * 10000));
    }
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
    constructorTest(null, testCompany, testPrice, "symbol can't be null");
  }

  @Test
  void constructorWithBlankSymbolThrowsException() {
    constructorTest("", testCompany, testPrice, "symbol can't be blank");
  }

  @Test
  void constructorWithNullCompanyThrowsException() {
    constructorTest(testSymbol, null, testPrice, "company can't be null");
  }

  @Test
  void constructorWithBlankCompanyThrowsException() {
    constructorTest(testSymbol, "", testPrice, "company can't be blank");
  }

  @Test
  void constructorWithNullPriceThrowsException() {
    constructorTest(testSymbol, testCompany, null, "salesPrice can't be null");
  }

  @Test
  void constructorWithZeroPriceThrowsException() {
    constructorTest(testSymbol, testCompany, BigDecimal.ZERO,
        "salesPrice must be larger than 0");
  }

  @Test
  void constructorWithNegativePriceThrowsException() {
    constructorTest(testSymbol, testCompany, new BigDecimal("-1"),
        "salesPrice must be larger than 0");
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

    assertEquals("newPrice can't be null", exception.getMessage());
  }

  @Test
  void addNewNegativeSalesPriceThrowsException() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> stock.addNewSalesPrice(BigDecimal.ZERO)
    );

    assertEquals("newPrice must be larger than 0", exception.getMessage());
}

  @Test
  void toStringReturnsCorrectFormat() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);

    String expected =
        testSymbol + " (" + testCompany + ") - current price: " + testPrice;

    assertEquals(expected, stock.toString());
  }

  @Test
  void getHistoricalPricesGetsAllAndOnlyPrices() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);
    assertEquals(1, stock.getHistoricalPrices().size());
    for (BigDecimal price : this.prices) {
      stock.addNewSalesPrice(price);
    }
    assertEquals(prices.size() + 1, stock.getHistoricalPrices().size());
    assertEquals(0, stock.getHistoricalPrices().getFirst().compareTo(testPrice));
    for (int i = 1; i < stock.getHistoricalPrices().size(); i++) {
      assertEquals(0, stock.getHistoricalPrices().get(i).compareTo(this.prices.get(i - 1)));
    }
  }

  @Test
  void getHighestPriceReturnsHighestPrice() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);
    for (BigDecimal price : this.prices) {
      stock.addNewSalesPrice(price);
    }
    BigDecimal highest = stock.getHighestPrice();
    for (BigDecimal price : stock.getHistoricalPrices()) {
      assertNotEquals(-1, highest.compareTo(price));
    }
  }

  @Test
  void getLowestPriceReturnsLowestPrice() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);
    for (BigDecimal price : this.prices) {
      stock.addNewSalesPrice(price);
    }
    BigDecimal lowest = stock.getLowestPrice();
    for (BigDecimal price : stock.getHistoricalPrices()) {
      assertNotEquals(1, lowest.compareTo(price));
    }
  }

  @Test
  void getLatestPriceGetsLatestPriceChange() {
    Stock stock = new Stock(testSymbol, testCompany, testPrice);
    assertEquals(BigDecimal.ZERO, stock.getLatestPriceChange());
    for (BigDecimal price : this.prices) {
      stock.addNewSalesPrice(price);
      assertEquals(
          price.subtract(stock.getHistoricalPrices().get(stock.getHistoricalPrices().size() - 2)),
          stock.getLatestPriceChange()
      );
    }
  }
}
