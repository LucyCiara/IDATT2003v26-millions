package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

/**
 * An abstract class for creating transactions.
 */
public abstract class Transaction {
  private Share share;
  private int week;
  private TransactionCalculator calculator;
  protected boolean committed;

  /**
   * The constructor sets information like the share to perform the Transaction on, the week it is
   * done, and the TransactionCalculator used.
   * 
   * @param share The share to perform the Transaction on.
   * @param week The week the Transaction is done.
   * @param calculator The TransactionCalculator for calculating purchase/sale cost.
   */
  protected Transaction(Share share, int week, TransactionCalculator calculator) {
    if (week < 0) {
      throw new IllegalArgumentException("week can't be negative");
    }
    this.share = share;
    this.week = week;
    this.calculator = calculator;
    this.committed = false;
    this.committed = false;
  }

  public Share getShare() {
    return this.share;
  }

  public int getWeek() {
    return this.week;
  }

  public TransactionCalculator getCalculator() {
    return this.calculator;
  }

  public boolean isCommitted() {
    return this.committed;
  }

  /**
   * Commits the Transaction on a given player.
   * 
   * @param player The player to perform the Transaction on.
   */
  public abstract void commit(Player player);

}
