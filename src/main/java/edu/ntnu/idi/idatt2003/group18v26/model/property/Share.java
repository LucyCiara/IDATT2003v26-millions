package edu.ntnu.idi.idatt2003.group18v26.model.property;

import java.math.BigDecimal;

/**
 * Represents a purchased share in a given stock.
 * <p>
 * A share stores the stock that was bought, the quantity purchased, and
 * the purchase price per unit at the time of the transaction.
 */
public class Share {
  private final Stock stock;
  private final BigDecimal quantity;
  private final BigDecimal purchasePrice;

  /**
   * Creates a new {@code Share}.
   *
   * @param stock         the stock that was purchased (must not be null)
   * @param quantity      the number of units purchased (must be positive)
   * @param purchasePrice the price per unit at the time of purchase (must not be
   *                      null)
   *
   * @throws IllegalArgumentException if {@code stock} is null
   * @throws IllegalArgumentException if {@code quantity} is null or not strictly
   *                                  positive
   * @throws IllegalArgumentException if {@code purchasePrice} is null
   */
  public Share(Stock stock, BigDecimal quantity, BigDecimal purchasePrice) {
    if (stock == null) {
      throw new IllegalArgumentException("Stock cannot  be null");
    }
    if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("quantity must be a positive number");
    }
    if (purchasePrice == null || purchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("purchasePrice must be a positive number");
    }
    this.stock = stock;
    this.quantity = quantity;
    this.purchasePrice = purchasePrice;
  }

  /**
   * Returns the stock associated with this share.
   * 
   * @return stock
   */
  public Stock getStock() {
    return this.stock;
  }

  /**
   * Returns the quantity purchased.
   * 
   * @return this.quantity
   */
  public BigDecimal getQuantity() {
    return this.quantity;
  }

  /**
   * Returns the purchase price per unit at the time of purchase.
   * 
   * @return purchasePrice
   */
  public BigDecimal getPurchasePrice() {
    return this.purchasePrice;
  }
}
