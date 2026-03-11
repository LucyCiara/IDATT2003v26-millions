package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;

public class PurchaseCalculatorTest {

  private Share testShare;
  private Share wrongShare;

  @BeforeEach
  void setUp() {
    Stock testStock = new Stock("TST", "TestCompany", new BigDecimal("50.00"));

    testShare = new Share(
        testStock,
        new BigDecimal("10"),
        new BigDecimal("100.00")
    );

    wrongShare = new Share(
        testStock,
        new BigDecimal("5"),
        new BigDecimal("150.00")
    );
  }

  @Test
  void constructorCreatesCalculatorSuccessfully() {
    assertDoesNotThrow(() -> new PurchaseCalculator(testShare));
  }

  @Test
  void constructorWithNullShareThrowsException() {
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> new PurchaseCalculator(null)
    );

    assertEquals("Share cannot be null", exception.getMessage());
  }

  @Test
  void calculateGrossReturnsCorrectValue() {
    PurchaseCalculator calculator = new PurchaseCalculator(testShare);

    BigDecimal expected = testShare.purchasePrice()
        .multiply(testShare.quantity());

    assertEquals(0, calculator.calculateGross().compareTo(expected));
  }

  @Test
  void calculateCommissionReturnsCorrectValue() {
    PurchaseCalculator calculator = new PurchaseCalculator(testShare);

    BigDecimal expectedGross = testShare.purchasePrice()
        .multiply(testShare.quantity());

    BigDecimal expectedCommission =
        expectedGross.multiply(new BigDecimal("0.005"));

    assertEquals(0, calculator.calculateCommission().compareTo(expectedCommission));
  }


  @Test
  void calculateTaxReturnsZero() {
    PurchaseCalculator calculator = new PurchaseCalculator(testShare);
    assertEquals(BigDecimal.ZERO, calculator.calculateTax());
  }


  @Test
  void calculateTotalReturnsCorrectValue() {
    PurchaseCalculator calculator = new PurchaseCalculator(testShare);

    BigDecimal expectedGross = testShare.purchasePrice().multiply(testShare.quantity());

    BigDecimal expectedCommission = expectedGross.multiply(new BigDecimal("0.005"));

    BigDecimal expectedTotal = expectedGross.add(expectedCommission);

    assertEquals(0, calculator.calculateTotal().compareTo(expectedTotal));
  }


  @Test
  void calculationsDifferForDifferentShares() {
    PurchaseCalculator calculator1 = new PurchaseCalculator(testShare);
    PurchaseCalculator calculator2 = new PurchaseCalculator(wrongShare);

    assertNotEquals(
        calculator1.calculateTotal(),
        calculator2.calculateTotal()
    );
  }
}