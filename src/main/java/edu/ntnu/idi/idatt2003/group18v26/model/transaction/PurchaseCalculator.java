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

  /**
   * {@inheritDoc}
   *
   * <p>For purchases, the gross amount equals
   * purchase price multiplied by quantity.
   */
  @Override
  public BigDecimal calculateGross() {
    return purchasePrice.multiply(quantity);
  }

  /**
   * {@inheritDoc}
   *
   * <p>For purchases, the commission is 0.5% of the gross amount.
   */
  @Override
  public BigDecimal calculateCommission() {
    return calculateGross().multiply(new BigDecimal("0.005"));
  }

  /**
   * {@inheritDoc}
   *
   * <p>Purchase transactions are not taxed,
   * therefore this method always returns {@code BigDecimal.ZERO}.
   */
  @Override
  public BigDecimal calculateTax() {
    return BigDecimal.ZERO;
  }

  /**
   * {@inheritDoc}
   *
   * <p>The total amount for a purchase equals
   * gross plus commission.
   */
  @Override
  public BigDecimal calculateTotal() {
    return calculateGross().add(calculateCommission()).add(calculateTax());
  }
}
