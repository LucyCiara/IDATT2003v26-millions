package edu.ntnu.idi.idatt2003.group18v26.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Portfolio;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.TransactionArchive;

/** A class for the player. */
public class Player {
  private String name;
  private BigDecimal startingMoney;
  private BigDecimal money;
  private Portfolio portfolio;
  private TransactionArchive transArchive;

  /**
   * The constructor, which takes a name and the starting money.
   * 
   * @param name          The name of the player.
   * @param startingMoney The starting money for the player.
   */
  public Player(String name, BigDecimal startingMoney) {
    if (name == null) {
      throw new IllegalArgumentException("name can't be null");
    } else if (name.isBlank()) {
      throw new IllegalArgumentException("name can't be blank");
    } else if (startingMoney == null) {
      throw new IllegalArgumentException("startingMoney can't be null");
    } else if (startingMoney.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("startingMoney must be a positive non-zero number");
    }
    this.name = name;
    this.startingMoney = startingMoney;
    this.money = startingMoney;
    this.portfolio = new Portfolio();
    this.transArchive = new TransactionArchive();
  }

  /**
   * A method to get the name of the player.
   * 
   * @return Returns the name of the player.
   */
  public String getName() {
    return this.name;
  }

  /**
   * A method to get the money of the player.
   * 
   * @return Returns the money of the player.
   */
  public BigDecimal getMoney() {
    return this.money;
  }

  /**
   * A method to increase money to the player.
   * 
   * @param moneyToAdd The amount of money to add.
   */
  public void addMoney(BigDecimal moneyToAdd) {
    if (moneyToAdd == null) {
      throw new IllegalArgumentException("moneyToAdd can't be null");
    } else if (moneyToAdd.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("moneyToAdd must be a positive non-zero number");
    }
    this.money = this.money.add(moneyToAdd);
  }

  /**
   * A method to reduce the money of the player.
   * 
   * @param moneyToWithdraw The amount of money to withdraw.
   */
  public void withdrawMoney(BigDecimal moneyToWithdraw) {
    if (moneyToWithdraw == null) {
      throw new IllegalArgumentException("moneyToWithdraw can't be null");
    } else if (moneyToWithdraw.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("moneyToWithdraw must be a positive non-zero number");
    }
    this.money = this.money.subtract(moneyToWithdraw);
  }

  /**
   * A get-method for the player's portfolio object.
   * 
   * @return Returns the player's portfolio.
   */
  public Portfolio getPortfolio() {
    return this.portfolio;
  }

  /**
   * A get-method for the player's transaction archive object.
   * 
   * @return Returns the player's transaction archive.
   */
  public TransactionArchive getTransactionArchive() {
    return this.transArchive;
  }

  public BigDecimal getNetWorth() {
    return this.money.add(this.portfolio.getNetWorth());
  }

  public String getStatus() {
    int weeksOfTrade = this.getTransactionArchive().countDistinctWeeks();
    BigDecimal profit = this.getNetWorth().divide(this.startingMoney, 1, RoundingMode.FLOOR);
    profit = profit.subtract(BigDecimal.ONE);
    if (weeksOfTrade >= 20 && profit.compareTo(BigDecimal.ONE) >= 0) {
      return "Speculator";
    } else if (weeksOfTrade >= 10
        && profit.compareTo(new BigDecimal("0.2")) >= 0) {
      return "Investor";
    } else {
      return "Novice";
    }
  }
}
