package edu.ntnu.idi.idatt2003.group18v26.model.property;

import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.TransactionFactory;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.PurchaseFactory;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleFactory;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class for exchanging shares/stocks.
 */
public class Exchange {
  private String name; // The name of the exchange.
  private int week = 0; // The week number.
  private HashMap<String, Stock> stockMap; // A HashMap of symbol keys connecting to Stock values.
  private List<Stock> stockList;
  private Random random; // A random generator.
  private List<GameObserver> observers = new ArrayList<>();
  private static final Logger logger
      = LoggerFactory.getLogger(Exchange.class);

  /**
   * Constructs an exchange with an exchange name and a list of Stocks.
   *
   * @param name The name of the Exchange.
   * @param stocks A list of stocks that can be traded on the Exchange.
   */
  public Exchange(String name, List<Stock> stocks) {
    ParameterValidator.stringChecker(name, "name");
    ParameterValidator.objectChecker(stocks, "stocks");
    this.name = name;
    this.stockMap = new HashMap<String, Stock>();
    stocks.forEach(stock -> this.stockMap.put(stock.getSymbol(), stock));
    this.random = new Random();
    this.stockList = stocks;
  }

  /**
   * A method for getting the name of the Exchange.
   *
   * @return The name of the Exchange.
   */
  public String getName() {
    return this.name;
  }

  /**
   * A method for getting the current week number of the Exchange.
   *
   * @return The current week number of the Exchange.
   */
  public int getWeek() {
    return this.week;
  }

  /**
   * A method for getting a list of all the stocks in the Exchange.
   *
   * @return A list of all the stocks in the Exchange.
   */
  public List<Stock> getStocks() {
    return new ArrayList<>(this.stockMap.values());
  }

  /**
   * A method for checking if the Exchange has the given stock.
   *
   * @param symbol The symbol of the Stock to check.
   * @return A boolean for whether the Exchange has the stock or not.
   * @throws IllegalArgumentException if the symbol is null or blank.
   */
  public boolean hasStock(String symbol) {
    ParameterValidator.stringChecker(symbol, "symbol");
    return this.stockMap.containsKey(symbol);
  }

  /**
   * A method for getting the Stock of the given symbol.
   *
   * @param symbol The symbol of the stock to get.
   * @return The Stock with the symbol.
   * @throws IllegalArgumentException if the symbol is null or blank.
   */
  public Stock getStock(String symbol) {
    ParameterValidator.stringChecker(symbol, "symbol");
    return this.stockMap.get(symbol);
  }

  /**
   * A method for finding stocks based on a search term.
   *
   * @param searchTerm A term that's a part of either the symbol or name of the stock.
   * @return A list of the stocks that meet the search term requirements.
   */
  public List<Stock> findStocks(String searchTerm) {
    ParameterValidator.stringChecker(searchTerm, "searchTerm");
    List<Stock> resultStocks = new ArrayList<Stock>();
    for (Stock stock : this.stockMap.values()) {
      if (stock.getSymbol().toLowerCase().contains(searchTerm.toLowerCase())
          || stock.getCompany().toLowerCase().contains(searchTerm.toLowerCase())) {
        resultStocks.add(stock);
      }
    }
    return resultStocks;
  }

  /**
   * A method for buying a Stock.
   *
   * @param symbol The symbol of the Stock to buy.
   * @param quantity The quantity of Stock to buy.
   * @param player The player to buy the Stock.
   * @return The performed Transaction.
   * @throws IllegalArgumentException if the symbol, quantity is null, or player is invalid.
   */
  public Transaction buy(String symbol, BigDecimal quantity, Player player) {
    ParameterValidator.stringChecker(symbol, "symbol");
    ParameterValidator.bigDecimalChecker(quantity, "quantity");
    ParameterValidator.objectChecker(player, "player");
    logger.info("Buy order: {} x{} at week {}", symbol, quantity, this.week);
    Stock stock = this.stockMap.get(symbol);
    Share share = new Share(stock, quantity, stock.getSalesPrice());
    
    TransactionFactory factory = new PurchaseFactory(share, this.week);
    Transaction purchase = factory.createTransaction();
    purchase.commit(player);
    logger.info("Purchase completed: {} x{}", symbol, quantity);
    notifyPurchaseCompleted(symbol, quantity);
    return purchase;
  }

  /**
   * A method for selling a Stock.
   *
   * @param share The symbol of the Stock to sell.
   * @param player The quantity of the Stock to sell.
   * @return The performed Transaction.
   * @throws IllegalArgumentException if the share or player is null.
   */
  public Transaction sell(Share share, Player player) {
    ParameterValidator.objectChecker(share, "share");
    ParameterValidator.objectChecker(player, "player");
    logger.info("Sell order: {} x{} at week {}", 
        share.stock().getSymbol(), share.quantity(), this.week);
    
    TransactionFactory factory = new SaleFactory(share, this.week);
    Transaction sale = factory.createTransaction();

    sale.commit(player);
    logger.info("Sale completed: {} x{}", share.stock().getSymbol(), share.quantity());
    notifySaleCompleted(share.stock().getSymbol(), share.quantity());
    return sale;
  }

  /**
   * A method for advancing the week.
   * Will add new prices to the stocks in the Exchange.
   */
  public void advance() {
    logger.info("Advancing week: {} → {}", this.week, this.week + 1);
    for (Stock stock : this.stockMap.values()) {
      stock.addNewSalesPrice(
          stock.getSalesPrice().multiply(
            new BigDecimal(this.random.nextDouble() + 0.5)
          )
      );
      notifyStockPriceChanged(stock.getSymbol());
    }
    this.week++;
    logger.info("Week advanced successfully to: {}", this.week);
    notifyWeekAdvanced();
  }

  /**
   * A method for getting a sorted list of stocks in descending order of profitability.
   *
   * @param limit The length of the list to return. Limit 3 will show the 3 most profitable stocks.
   * @return A list of stocks in descending order of profitability.
   * @throws IllegalArgumentException if the limit < 1 or exceeds stocks in the exchange.
   */
  public List<Stock> getGainers(int limit) {
    ParameterValidator.limitChecker(
        limit,
        "limit",
        this.stockMap.values().size(),
        "number of stocks"
    );
    return this.stockMap.values().stream().sorted(
      (s1, s2) -> s1.getLatestPriceChange().compareTo(s2.getLatestPriceChange())
    ).toList().reversed().subList(0, limit);
  }

  /**
   * A method for getting a sorted list of stocks in ascending order of profitability.
   *
   * @param limit The length of the list to return. Limit 3 will show the 3 least profitable stocks.
   * @return A list of stocks in ascending order of profitability.
   * @throws IllegalArgumentException if the limit < 1 or exceeds stocks in the exchange.
   */
  public List<Stock> getLosers(int limit) {
    ParameterValidator.limitChecker(
        limit,
        "limit",
        this.stockMap.values().size(),
        "number of stocks"
    );
    return this.stockMap.values().stream().sorted(
      (s1, s2) -> s1.getLatestPriceChange().compareTo(s2.getLatestPriceChange())
    ).toList().subList(0, limit);
  }

  public List<Stock> getAllStock() {
    return this.stockList;
  }

  public List<Stock> getAllStockBySymbol() {
    return this.stockList.stream()
      .sorted((s1, s2) -> s1.getSymbol().compareTo(s2.getSymbol())).toList();
  }

  public List<Stock> getAllStockByCompany() {
    return this.stockList.stream()
      .sorted((s1, s2) -> s1.getCompany().compareTo(s2.getCompany())).toList();
  }

  public List<Stock> getAllStockByPrice() {
    return this.stockList.stream()
      .sorted((s1, s2) -> s1.getSalesPrice().compareTo(s2.getSalesPrice())).toList();
  }

  /**
   * Adds an observer to be notified of exchange changes.
   *
   * @param observer The observer to add
   */
  public void addObserver(GameObserver observer) {
    logger.debug("Observer added: {}", observer.getClass().getSimpleName());
    this.observers.add(observer);
  }

  /**
   * Removes an observer from being notified of exhange changes.
   *
   * @param observer The observer to remove
   */
  public void removeObserver(GameObserver observer) {
    logger.debug("Observer removed: {}", observer.getClass().getSimpleName());
    this.observers.remove(observer);
  }

  /**
   * Notify all observers that the week has advanced.
   */
  private void notifyWeekAdvanced() {
    logger.debug("Notifying observers: week advanced to {}", this.week);
    observers.forEach(observer -> observer.onWeekAdvanced(this.week));
  }

  /**
   * Notify all observers that a stock price changed.
   *
   * @param symbol The symbol of the stock that changed price
   */
  private void notifyStockPriceChanged(String symbol) {
    logger.debug("Notifying observers: stock price changed - {}", symbol);
    observers.forEach(observer -> observer.onStockPriceChanged(symbol));
  }

  /**
   * Notify all observers that a purchase has been completed.
   *
   * @param symbol The symbol of the stock that was purchased
   * @param quantity The quantity of the stock that was purchased
   */
  private void notifyPurchaseCompleted(String symbol, BigDecimal quantity) {
    logger.debug("Notifying observers: purchase completed - {} x{}", symbol, quantity);
    observers.forEach(observer -> observer.onPurchaseCompleted(symbol, quantity.toString()));
  }

  /**
   * Notify all observers that a sale has been completed.
   *
   * @param symbol The symbol of the stock that was sold
   * @param quantity The quantity of the stock that was sold
   */
  private void notifySaleCompleted(String symbol, BigDecimal quantity) {
    logger.debug("Notifying observers: sale completed - {} x{}", symbol, quantity);
    observers.forEach(observer -> observer.onSaleCompleted(symbol, quantity.toString()));
  }
}
