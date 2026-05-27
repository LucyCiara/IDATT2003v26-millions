package edu.ntnu.idi.idatt2003.group18v26.model;

import edu.ntnu.idi.idatt2003.group18v26.model.property.Portfolio;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.TransactionArchive;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A class for the player. */
public class Player {
  private String name;
  private BigDecimal startingMoney;
  private BigDecimal money;
  private Portfolio portfolio;
  private TransactionArchive transArchive;
  private List<GameObserver> observers = new ArrayList<>();
  private static final Logger logger
      = LoggerFactory.getLogger(Player.class);

  /**
   * The constructor, which takes a name and the starting money.
   *
   * @param name          The name of the player.
   * @param startingMoney The starting money for the player.
   */
  public Player(String name, BigDecimal startingMoney) {
    ParameterValidator.stringChecker(name, "name");
    ParameterValidator.bigDecimalChecker(startingMoney, "startingMoney");
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
    ParameterValidator.bigDecimalChecker(moneyToAdd, "moneyToAdd");
    this.money = this.money.add(moneyToAdd);
    logger.debug("Player {} gained {}, new balance: {}", this.name, moneyToAdd, this.money);
    notifyMoneyChanged();
  }

  /**
   * A method to reduce the money of the player.
   *
   * @param moneyToWithdraw The amount of money to withdraw.
   */
  public void withdrawMoney(BigDecimal moneyToWithdraw) {
    ParameterValidator.bigDecimalChecker(moneyToWithdraw, "moneyToWithdraw");
    this.money = this.money.subtract(moneyToWithdraw);
    logger.debug("Player {} withdrew {}, new balance: {}", this.name, moneyToWithdraw, this.money);
    notifyMoneyChanged();
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

  /**
   * Method for getting the player's net worth.
   *
   * @return The player's net worth
   */
  public BigDecimal getNetWorth() {
    return this.money.add(this.portfolio.getNetWorth());
  }

  /**
   * Gets the current status of the player, based on months of active trade and lifetime profit.
   *
   * @return The name of the player's current status.
   */
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

  /**
   * Adds an observer to be notified of Player changes.
   *
   * @param observer The observer to add
   */
  public void addObserver(GameObserver observer) {
    logger.debug("Observer added: {}", observer.getClass().getSimpleName());
    this.observers.add(observer);
  }

  /**
   * Removes an observer from being notified of Player changes.
   *
   * @param observer The observer to remove
   */
  public void removeObserver(GameObserver observer) {
    logger.debug("Observer removed: {}", observer.getClass().getSimpleName());
    this.observers.remove(observer);
  }

  /**
   * Notify all observers that the Money has changed.
   */
  private void notifyMoneyChanged() {
    logger.debug("Notifying observers: money changed to {}", this.money);
    observers.forEach(observer -> observer.onMoneyChanged(this.money.toString()));
  }

  
}
