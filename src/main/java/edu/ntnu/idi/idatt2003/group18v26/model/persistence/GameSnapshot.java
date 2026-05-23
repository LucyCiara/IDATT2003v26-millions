package edu.ntnu.idi.idatt2003.group18v26.model.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Represents a snapshot of the game state at a particular point in time.
 */
public class GameSnapshot {
  public String playerName;
  public BigDecimal playerMoney;
  public BigDecimal startingMoney;
  public List<ShareSnapshot> playerPortfolio;
  public int week;
  public Map<String, List<BigDecimal>> stockPriceHistory;
  public List<TransactionSnapshot> transactions;

  public GameSnapshot() {
  }

  /**
   * Constructs a GameSnapshot with the provided parameters.
   *
   * @param playerName The name of the player.
   * @param playerMoney The current money of the player.
   * @param startingMoney The starting money of the player at the beginning of the game.
   * @param playerPortfolio The player's portfolio of shares.
   * @param week The current week in the game.
   * @param stockPriceHistory The history of stock prices.
   * @param transactions The list of transactions.
   */
  public GameSnapshot(String playerName, BigDecimal playerMoney, 
                      BigDecimal startingMoney, List<ShareSnapshot> playerPortfolio, 
                      int week,
                      Map<String, List<BigDecimal>> stockPriceHistory, 
                      List<TransactionSnapshot> transactions) {
    this.playerName = playerName;
    this.playerMoney = playerMoney;
    this.startingMoney = startingMoney;
    this.playerPortfolio = playerPortfolio;
    this.week = week;
    this.stockPriceHistory = stockPriceHistory;
    this.transactions = transactions;
  }
}
