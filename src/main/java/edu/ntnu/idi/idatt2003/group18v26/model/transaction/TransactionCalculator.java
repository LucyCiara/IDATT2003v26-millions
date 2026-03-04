package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import java.math.BigDecimal;

/**
 * Interface for calculating the gross, commission, tax, and total for a
 * transaction.
 * Implementations of this interface will provide specific calculations for
 * different types of transactions, such as purchases and sales.
 * The methods in this interface are designed to be used by the Transaction
 * class to calculate the financial details of a transaction based on its type
 * and properties.
 * Implementing classes should ensure that the calculations are accurate and
 * reflect the business rules for the specific transaction type they represent.
 */
public interface TransactionCalculator {
  /**
   * Calculates the gross amount of the transaction
   * before deductions.
   *
   * @return gross amount
   */
  public BigDecimal calculateGross();

  /**
   * Calculates the commission charged for the transaction.
   *
   * @return commission amount
   */
  public BigDecimal calculateCommission();

  /**
   * Calculates the tax applied to the transaction.
   *
   * @return tax amount
   */
  public BigDecimal calculateTax();

  /**
   * Calculates the final total amount after commission and tax.
   *
   * @return total settlement amount
   */
  public BigDecimal calculateTotal();
}
