package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.TransactionFactory;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

/**
 * Concrete factory for creating a Purchase transaction.
 */
public class PurchaseFactory extends TransactionFactory {
  private Share share;
  private int week;

  public PurchaseFactory(Share share, int week) {
    this.share = share;
    this.week = week;
  }

  /**
   * Creates a Purchase transaction using the provided Share and week information.
   *
   * @return A Purchase transaction object initialized with the specified Share and week.
   */
  @Override
  public Transaction createTransaction() {
    return new Purchase(share, week);
  }
  
}
