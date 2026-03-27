package edu.ntnu.idi.idatt2003.group18v26.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShareTest {
  private Stock testStock;
  private BigDecimal testQuantity;
  private BigDecimal testPurchasePrice;

  @BeforeEach
  void setUp() {
      testStock = new Stock("TTC", "TestINC", new BigDecimal("20051910.142113020518"));
      testQuantity = new BigDecimal("2005.1910");
      testPurchasePrice = new BigDecimal("20051910.142113020518");
    }

  private void constructorTest(Stock stock, BigDecimal quantity, BigDecimal purchasePrice, String expectedMessage) {
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class,
          () -> new Share(stock, quantity, purchasePrice)
      );
      assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void constructorCreatesValidShare() {
      Share share = new Share(testStock, testQuantity, testPurchasePrice);

      assertEquals(testStock, share.stock());
      assertEquals(testQuantity, share.quantity());
      assertEquals(testPurchasePrice, share.purchasePrice());
  }

  @Test
  void constructorWithNullStockThrowsException() {
    constructorTest(null, testQuantity, testPurchasePrice, "stock can't be null");
  }

  @Test
  void constructorWithNullQuantityThrowsException() {
      constructorTest(testStock, null, testPurchasePrice, "quantity can't be null");
  }

  @Test
  void constructorWithZeroQuantityThrowsException() {
    constructorTest(testStock, BigDecimal.ZERO, testPurchasePrice, "quantity must be larger than 0");
  }

  @Test
  void constructorWithNegativeQuantityThrowsException() {
    constructorTest(testStock, new BigDecimal("-1"), testPurchasePrice, "quantity must be larger than 0");
  }

  @Test
  void constructorWithNullPurchasePriceThrowsException() {
    constructorTest(testStock, testQuantity, null, "purchasePrice can't be null");
    }

  @Test
  void constructorWithZeroPurchasePriceThrowsException() {
    constructorTest(testStock, testQuantity, BigDecimal.ZERO, "purchasePrice must be larger than 0");
  }

  @Test
  void constructorWithNegativePurchasePriceThrowsException() {
    constructorTest(testStock, testQuantity, new BigDecimal("-1"), "purchasePrice must be larger than 0");
    }


  @Test
  void getStockReturnsCorrectStock() {
    Share share = new Share(testStock, testQuantity, testPurchasePrice);
    assertEquals(testStock, share.stock());
  }

  @Test
  void getQuantityReturnsCorrectQuantity() {
    Share share = new Share(testStock, testQuantity, testPurchasePrice);
    assertEquals(testQuantity, share.quantity());
  }

  @Test
  void getPurchasePriceReturnsCorrectPurchasePrice() {
  Share share = new Share(testStock, testQuantity, testPurchasePrice);
  assertEquals(testPurchasePrice, share.purchasePrice());
  }

}