package edu.ntnu.idi.idatt2003.group18v26.model.persistence;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Exchange;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Portfolio;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Translator between complex Model objects and simple Snapshots.
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
    ParameterValidator.objectChecker(player, "player");
    ParameterValidator.objectChecker(exchange, "exchange");
    logger.info("Serializing game state for player: {}", player.getName());
    
    GameSnapshot snapshot = new GameSnapshot();
    snapshot.playerName = player.getName();
    snapshot.playerMoney = player.getMoney();
    snapshot.startingMoney = player.getStartingMoney();
    snapshot.playerPortfolio = extractPortfolio(player.getPortfolio());
    snapshot.week = exchange.getWeek();
    snapshot.stockCompany = extractStockCompanyRelations(exchange);
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
   * Extract stock price history from exchange.
   * Maps each stock symbol to list of its historical prices.
   *
   * @param exchange The exchange with stock data
   * @return Map of symbol → price history list
   */
  private Map<String, List<BigDecimal>> extractStockPriceHistory(Exchange exchange) {
    Map<String, List<BigDecimal>> history = new HashMap<>();
    
    exchange.getStocks().forEach(stock -> {
      history.put(stock.getSymbol(), new ArrayList<>(stock.getHistoricalPrices()));
    });
    return history;
  }

  private Map<String, String> extractStockCompanyRelations(Exchange exchange) {
    Map<String, String> symbolCompany = new HashMap<>();

    exchange.getStocks().forEach(stock -> {
      symbolCompany.put(stock.getSymbol(), stock.getCompany());
    });
    return symbolCompany;
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
          transaction.getShare().purchasePrice(),
          transaction.getCalculator().calculateTotal(),
          transaction.getWeek()
      );
      transactions.add(snap);
    }
    
    return transactions;
  }
}