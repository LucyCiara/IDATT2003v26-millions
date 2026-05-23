package edu.ntnu.idi.idatt2003.group18v26.model.percistence;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Exchange;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Portfolio;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Translator between complex Model objects and simple Snapshots.
 * 
 * Responsibility: Extract data from Player/Exchange domain objects
 * into simple DTOs suitable for JSON serialization.
 * 
 * SRP: Only serialization logic (extraction).
 * DRY: All extraction centralized here, not duplicated in Reader/Writer.
 * Separation of Concerns: Model stays pure, unaware of JSON.
 */
public class GameSerializer {
  private static final Logger logger = LoggerFactory.getLogger(GameSerializer.class);

  /**
   * Convert complex Model objects to simple Snapshot for serialization.
   *
   * @param player The Player object with portfolio and balance
   * @param exchange The Exchange object with stock prices
   * @return GameSnapshot containing extracted data
   */
  public GameSnapshot toSnapshot(Player player, Exchange exchange) {
    logger.info("Serializing game state for player: {}", player.getName());
    
    GameSnapshot snapshot = new GameSnapshot();
    snapshot.playerName = player.getName();
    snapshot.playerMoney = player.getMoney();
    snapshot.startingMoney = player.getStartingMoney();
    snapshot.playerPortfolio = extractPortfolio(player.getPortfolio());
    snapshot.week = exchange.getWeek();
    snapshot.currentStockPrices = extractCurrentStockPrices(exchange);
    snapshot.stockPriceHistory = extractStockPriceHistory(exchange);
    snapshot.transactions = extractTransactions(player);

    logger.debug("Serialization complete. Snapshot has {} portfolio items and {} transactions",
            snapshot.playerPortfolio.size(), snapshot.transactions.size());
    
    return snapshot;
  }

  /**
   * Extract portfolio shares into list of ShareSnapshots.
   *
   * @param portfolio The player's portfolio
   * @return List of share snapshots
   */
  private List<ShareSnapshot> extractPortfolio(Portfolio portfolio) {
    List<ShareSnapshot> shares = new ArrayList<>();
    
    for (Share share : portfolio.getShares()) {
      ShareSnapshot snap = new ShareSnapshot(
          share.stock().getSymbol(),
          share.quantity(),
          share.purchasePrice()
      );
      shares.add(snap);
    }
    
    return shares;
  }

  /**
   * Extract current stock prices from exchange.
   *
   * @param exchange The exchange with stock data
   * @return Map of symbol → current price
   */
  private Map<String, BigDecimal> extractCurrentStockPrices(Exchange exchange) {
    Map<String, BigDecimal> prices = new HashMap<>();
    
    exchange.getStocks().forEach(stock -> 
        prices.put(stock.getSymbol(), stock.getSalesPrice())
    );
    
    return prices;
  }

  /**
   * Extract stock price history from exchange.
   * Maps each stock symbol to list of its historical prices.
   *
   * @param exchange The exchange with stock data
   * @return Map of symbol → price history list
   */
  private Map<String, List<BigDecimal>> extractStockPriceHistory(Exchange exchange) {
    Map<String, List<BigDecimal>> history = new HashMap<>();
    
    exchange.getStocks().forEach(stock -> {
      history.put(stock.getSymbol(), stock.getHistoricalPrices());
    });
    return history;
  }

  /**
   * Extract transaction history into list of TransactionSnapshots.
   *
   * @param player The player whose transactions to extract
   * @return List of transaction snapshots
   */
  private List<TransactionSnapshot> extractTransactions(Player player) {
    List<TransactionSnapshot> transactions = new ArrayList<>();
    
    List<Transaction> playerTransactions = player.getTransactionArchive().getAllTransactions();
    
    for (Transaction transaction : playerTransactions) {
      String type = transaction.getClass().getSimpleName().toUpperCase();
      TransactionSnapshot snap = new TransactionSnapshot(
          type,
          transaction.getShare().stock().getSymbol(),
          transaction.getShare().quantity(),
          transaction.getCalculator().calculateTotal()
      );
      transactions.add(snap);
    }
    
    return transactions;
  }
}