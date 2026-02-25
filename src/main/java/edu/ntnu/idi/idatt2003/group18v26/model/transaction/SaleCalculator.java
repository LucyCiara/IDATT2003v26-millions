package edu.ntnu.idi.idatt2003.group18v26.model.transaction;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import java.math.BigDecimal;
/**
 * The SaleCalculator class calculates the gross amount, commission, tax, and total for a sale transaction.
 * It implements the TransactionCalculator interface and uses the purchase price, sales price, and quantity from
 * a Share object to perform the calculations. 
 */
public class SaleCalculator implements TransactionCalculator {
  private BigDecimal purchasePrice;
  private BigDecimal salesPrice;
  private BigDecimal quantity;

  public SaleCalculator(Share share) {
    if (purchasePrice == null || purchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("purchasePrice must be a positive number");
    }
    if (salesPrice == null || salesPrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("salesPrice cannot be null");
    }
    if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("quantity must be a positive number");
    }
    this.purchasePrice = share.purchasePrice();
    this.salesPrice = share.stock().getSalesPrice();
    this.quantity = share.quantity();
  }

  @Override
  public BigDecimal calculateGross() {
    return salesPrice.multiply(quantity);
  }

  @Override
  public BigDecimal calculateCommission() {
    return calculateGross().multiply(new BigDecimal("0.01"));
  }

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

  @Override
  public BigDecimal calculateTotal() {
    return calculateGross()
          .subtract(calculateCommission())
          .subtract(calculateTax());
    }
}
