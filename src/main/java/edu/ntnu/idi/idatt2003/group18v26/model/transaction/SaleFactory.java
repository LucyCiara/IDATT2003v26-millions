package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.TransactionFactory;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

/**
 * Concrete factory for creating a Sale transaction.
 */
public class SaleFactory extends TransactionFactory {
  private Share share;
  private int week;

  public SaleFactory(Share share, int week) {
    this.share = share;
    this.week = week;
  }

  /**
   * Creates a Sale transaction using the provided Share and week information.
   *
   * @return A Sale transaction object initialized with the specified Share and week.
   */
  @Override
  public Transaction createTransaction() {
    return new Sale(share, week);
  }
}
