package edu.ntnu.idi.idatt2003.group18v26.model.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

public abstract class Transaction {
  private Share share;
  private int week;
  private TransactionCalculator calculator;
  protected boolean committed;

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

  public abstract void commit(Player player);

}
