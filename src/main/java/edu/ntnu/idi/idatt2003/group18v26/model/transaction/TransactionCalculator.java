package edu.ntnu.idi.idatt2003.group18v26.model.transaction;
import java.math.BigDecimal;

public interface TransactionCalculator {
  BigDecimal calculateGross();

  BigDecimal calculateCommission();

  BigDecimal calculateTax();

  BigDecimal calculateTotal();
}
