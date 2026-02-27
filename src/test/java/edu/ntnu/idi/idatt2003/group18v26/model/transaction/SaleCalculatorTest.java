package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;

public class SaleCalculatorTest {

  private Share profitShare;
  private Share lossShare;

  @BeforeEach
  void setUp() {
    Stock profitStock = new Stock("TST", "TestCompany", new BigDecimal("150.00"));
    profitShare = new Share(
        profitStock,
        new BigDecimal("10"),
        new BigDecimal("100.00")
    );

    Stock lossStock = new Stock("LOS", "LossCompany", new BigDecimal("50.00"));
    lossShare = new Share(
        lossStock,
        new BigDecimal("10"),
        new BigDecimal("100.00")
    );
  }


  @Test
  void constructorCreatesCalculatorSuccessfully() {
    assertDoesNotThrow(() -> new SaleCalculator(profitShare));
  }

  @Test
  void constructorWithNullShareThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new SaleCalculator(null)
    );
    assertEquals("Share cannot be null", exception.getMessage());
  }



  @Test
  void calculateMethodsReturnCorrectValuesWithProfit() {
    SaleCalculator calculator = new SaleCalculator(profitShare);

    BigDecimal gross = profitShare.stock().getSalesPrice().multiply(profitShare.quantity());

    BigDecimal commission = gross.multiply(new BigDecimal("0.01"));

    BigDecimal purchaseCost = profitShare.purchasePrice().multiply(profitShare.quantity());

    BigDecimal profit = gross.subtract(purchaseCost);

    BigDecimal tax = profit.multiply(new BigDecimal("0.3"));

    BigDecimal total = gross.subtract(commission).subtract(tax);

    assertEquals(0, calculator.calculateGross().compareTo(gross));
    assertEquals(0, calculator.calculateCommission().compareTo(commission));
    assertEquals(0, calculator.calculateTax().compareTo(tax));
    assertEquals(0, calculator.calculateTotal().compareTo(total));
  }

  @Test
  void calculateMethodsReturnCorrectValuesWithLoss() {
    SaleCalculator calculator = new SaleCalculator(lossShare);

    BigDecimal gross = lossShare.stock().getSalesPrice().multiply(lossShare.quantity());

    BigDecimal commission = gross.multiply(new BigDecimal("0.01"));

    BigDecimal purchaseCost = lossShare.purchasePrice().multiply(lossShare.quantity());

    BigDecimal profit = gross.subtract(purchaseCost);

    BigDecimal tax = BigDecimal.ZERO;

    BigDecimal total = gross.subtract(commission).subtract(tax);

    assertTrue(profit.compareTo(BigDecimal.ZERO) < 0);
    assertEquals(0, calculator.calculateTax().compareTo(BigDecimal.ZERO));

    assertEquals(0, calculator.calculateGross().compareTo(gross));
    assertEquals(0, calculator.calculateCommission().compareTo(commission));
    assertEquals(0, calculator.calculateTotal().compareTo(total));
  }
}