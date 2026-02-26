package edu.ntnu.idi.idatt2003.group18v26.transaction;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;

public class Transaction {
  private Share share;
  private int week;
  private TransactionCalculator calculator;
  protected boolean committed;

  protected Transaction(Share share, int week, TransactionCalculator calculator) {
    this.share = share;
    this.week = week;
    this.calculator = calculator;
  }

  public Share getShare() {
    return this.share;
  }

  public int getWeek() {
    return this.week;
  }

  public TransactionCalculator getCalculator() {
    return this.TransactionCalculator;
  }

  public boolean isCommited() {
    return this.committed;
  }

  public void commit(Player player) {
  }

}
