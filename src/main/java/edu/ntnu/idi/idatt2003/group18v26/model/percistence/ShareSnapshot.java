package edu.ntnu.idi.idatt2003.group18v26.model.percistence;

import java.math.BigDecimal;

/**
 * Data Transfer Object for a single share holding.
 * Used when serializing portfolio to JSON.
 */
public class ShareSnapshot {
  public String symbol;
  public int quantity;
  public BigDecimal purchasePrice;

  /**
   * Default constructor for Gson deserialization.
   */
  public ShareSnapshot() {
  }

  /**
   * Constructor for creating share snapshots.
   */
  public ShareSnapshot(String symbol, int quantity, BigDecimal purchasePrice) {
    this.symbol = symbol;
    this.quantity = quantity;
    this.purchasePrice = purchasePrice;
  }
}