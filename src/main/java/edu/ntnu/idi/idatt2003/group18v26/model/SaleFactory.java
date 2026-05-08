package edu.ntnu.idi.idatt2003.group18v26.model;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Sale;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;

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

  @Override
  public Transaction createTransaction() {
    return new Sale(share, week);
  }
}
