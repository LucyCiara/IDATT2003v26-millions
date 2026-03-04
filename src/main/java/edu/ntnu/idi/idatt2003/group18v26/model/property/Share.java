package edu.ntnu.idi.idatt2003.group18v26.model.property;

import java.math.BigDecimal;

/**
 * Represents a purchased share in a given stock.
 * <p>
 * A share stores the stock that was bought, the quantity purchased, and
 * the purchase price per unit at the time of the transaction.
 */
public record Share(Stock stock, BigDecimal quantity, BigDecimal purchasePrice) {
  /**
   * Creates a new {@code Share}.
   *
   * @param stock         the stock that was purchased (must not be null)
   * @param quantity      the number of units purchased (must be positive)
   * @param purchasePrice the price per unit at the time of purchase (must not be
   *                      null)
   * @throws IllegalArgumentException if {@code stock} is null
   * @throws IllegalArgumentException if {@code quantity} is null or not strictly
   *                                  positive
   * @throws IllegalArgumentException if {@code purchasePrice} is null
   */
  public Share {
    if (stock == null) {
      throw new IllegalArgumentException("Stock cannot be null");
    }
    if (quantity == null) {
      throw new IllegalArgumentException("quantity cannot be null");
    }
    if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("quantity must be a positive number");
    }
    if (purchasePrice == null) {
      throw new IllegalArgumentException("purchasePrice cannot be null");
    }
    if (purchasePrice.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("purchasePrice must be a positive number");
    }
  }

  /**
   * Returns the stock associated with this share.
   *
   * @return stock
   */
  @Override
  public Stock stock() {
    return this.stock;
  }

  /**
   * Returns the quantity purchased.
   *
   * @return this.quantity
   */
  @Override
  public BigDecimal quantity() {
    return this.quantity;
  }

  /**
   * Returns the purchase price per unit at the time of purchase.
   *
   * @return purchasePrice
   */
  @Override
  public BigDecimal purchasePrice() {
    return this.purchasePrice;
  }
}
