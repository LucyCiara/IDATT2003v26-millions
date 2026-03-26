package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import java.math.BigDecimal;

/**
 * The SaleCalculator class calculates the gross amount, commission, tax, and total for a sale
 * transaction.
 * It implements the TransactionCalculator interface and uses the purchase price, sales price, and
 * quantity from a Share object to perform the calculations. 
 * 
 * <p>The gross amount is calculated as:
 * current sales price × quantity.
 *
 * <p>A commission of 1% of the gross amount is applied.
 *
 * <p>Tax is 30% of the profit, where profit is defined as:
 * (gross - purchase cost).
 * No tax is applied if the profit is zero or negative.
 *
 * <p>The total amount is calculated as:
 * gross - commission - tax.
 */
public class SaleCalculator implements TransactionCalculator {
  private BigDecimal purchasePrice;
  private BigDecimal salesPrice;
  private BigDecimal quantity;

  /**
   * Sets the purchasePrice and quantity based on information in the Share.
   * 
   * @param share The share to calculate the purchase of. Must be non-null.
   */
  public SaleCalculator(Share share) {
    if (share == null) {
      throw new IllegalArgumentException("Share cannot be null");
    }
    this.purchasePrice = share.purchasePrice();
    this.salesPrice = share.stock().getSalesPrice();
    this.quantity = share.quantity();
  }

  /**
   * {@inheritDoc}
   *
   * <p>For sales, the gross amount equals
   * current sales price multiplied by quantity.
   */
  @Override
  public BigDecimal calculateGross() {
    return salesPrice.multiply(quantity);
  }

  /**
   * {@inheritDoc}
   *
   * <p>For sales, the commission is 1% of the gross amount.
   */
  @Override
  public BigDecimal calculateCommission() {
    return calculateGross().multiply(new BigDecimal("0.01"));
  }

  /**
   * {@inheritDoc}
   *
   * <p>The tax is 30% of the profit if the profit is positive.
   * Returns {@code BigDecimal.ZERO} if there is no profit.
   */
  @Override
  public BigDecimal calculateTax() {
    BigDecimal gross = calculateGross();
    BigDecimal purchaseCost = purchasePrice.multiply(quantity);
    BigDecimal profit = gross.subtract(purchaseCost);
    if (profit.compareTo(BigDecimal.ZERO) <= 0) {
      return BigDecimal.ZERO;
    }
    return profit.multiply(new BigDecimal("0.3"));
  }

  /**
   * {@inheritDoc}
   *
   * <p>The total amount for a sale equals
   * gross minus commission and tax.
   */
  @Override
  public BigDecimal calculateTotal() {
    return calculateGross()
          .subtract(calculateCommission())
          .subtract(calculateTax());
    }
}
