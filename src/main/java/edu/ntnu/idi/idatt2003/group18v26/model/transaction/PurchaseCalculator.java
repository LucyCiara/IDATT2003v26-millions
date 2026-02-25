package edu.ntnu.idi.idatt2003.group18v26.model.transaction;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import java.math.BigDecimal;
/**
 * The PurchaseCalculator class calculates the gross amount, commission, tax, and total for a purchase transaction.
 * It implements the TransactionCalculator interface and uses the purchase price and quantity from a Share object to
 * perform the calculations.
 */
public class PurchaseCalculator implements TransactionCalculator{
  private BigDecimal purchasePrice;
  private BigDecimal quantity;

  public PurchaseCalculator(Share share) {
    if (purchasePrice == null || purchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("purchasePrice must be a positive number");
    }
    if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("quantity must be a positive number");
    }
    this.purchasePrice = share.purchasePrice();
    this.quantity = share.quantity();
  }

  @Override
  public BigDecimal calculateGross() {
    return purchasePrice.multiply(quantity);
  }

  @Override
  public BigDecimal calculateCommission() {
    return calculateGross().multiply(new BigDecimal("0.005"));
  }

  @Override
  public BigDecimal calculateTax() {
    return BigDecimal.ZERO;
  }

  @Override
  public BigDecimal calculateTotal() {
    return calculateGross().add(calculateCommission()).add(calculateTax());
  }
}
