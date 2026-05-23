package edu.ntnu.idi.idatt2003.group18v26.model.persistence;

import java.math.BigDecimal;

/**
 * Data Transfer Object for a single transaction (buy or sell).
 * Used when serializing transaction archive to JSON.
 */
public class TransactionSnapshot {
  public String type; // "PURCHASE" or "SALE"
  public String symbol;
  public BigDecimal quantity;
  public BigDecimal totalPrice;

  /**
   * Default constructor for Gson deserialization.
   */
  public TransactionSnapshot() {
  }

  /**
   * Constructor for creating transaction snapshots.
   */
  public TransactionSnapshot(String type, String symbol, 
      BigDecimal quantity, BigDecimal totalPrice) {
    this.type = type;
    this.symbol = symbol;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
  }
}