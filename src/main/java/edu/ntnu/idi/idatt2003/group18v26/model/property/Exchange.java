package edu.ntnu.idi.idatt2003.group18v26.model.property;

import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Purchase;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Sale;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * A class for exchanging shares/stocks.
 */
public class Exchange {
  private String name; // The name of the exchange.
  private int week = 0; // The week number.
  private HashMap<String, Stock> stockMap; // A HashMap of symbol keys connecting to Stock values.
  private Random random; // A random generator.
  private List<GameObserver> observers = new ArrayList<>();


  /**
   * Constructs an exchange with an exchange name and a list of Stocks.
   * 
   * @param name The name of the exchange.
   * @param stocks A list of stocks that can be traded on the Exchange.
   */
  public Exchange(String name, List<Stock> stocks) {
    ParameterValidator.stringChecker(name, "name");
    ParameterValidator.objectChecker(stocks, "stocks");
    this.name = name;
    this.stockMap = new HashMap<String, Stock>();
    stocks.forEach(stock -> this.stockMap.put(stock.getSymbol(), stock));
    this.random = new Random();
  }

  public String getName() {
    return this.name;
  }

  public int getWeek() {
    return this.week;
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
   */
  public Transaction buy(String symbol, BigDecimal quantity, Player player) {
    ParameterValidator.stringChecker(symbol, "symbol");
    ParameterValidator.bigDecimalChecker(quantity, "quantity");
    ParameterValidator.objectChecker(player, "player");
    Transaction purchase = new Purchase(
        new Share(
          this.stockMap.get(symbol), quantity, this.stockMap.get(symbol).getSalesPrice()
        ),
        this.week
    );
    purchase.commit(player);
    notifyPurchaseCompleted(symbol, quantity);
    return purchase;
  }

  /**
   * A method for selling a Stock.
   * 
   * @param share The symbol of the Stock to sell.
   * @param player The quantity of the Stock to sell.
   * @return The performed Transaction.
   */
  public Transaction sell(Share share, Player player) {
    ParameterValidator.objectChecker(share, "share");
    ParameterValidator.objectChecker(player, "player");
    Transaction sale = new Sale(
        share,
        this.week);
    sale.commit(player);
    notifySaleCompleted(share.stock().getSymbol(), share.quantity());

    return sale;
  }

  /**
   * A method for advancing the week.
   * Will add new prices to the stocks in the Exchange.
   */
  public void advance() {
    for (Stock stock : this.stockMap.values()) {
      stock.addNewSalesPrice(
          stock.getSalesPrice().multiply(
            new BigDecimal(this.random.nextDouble() + 0.5)
          )
      );
      notifyStockPriceChanged(stock.getSymbol());
    }
    this.week++;

    notifyWeekAdvanced();
  }

  /**
   * A method for getting a sorted list of stocks in descending order of profitability.
   * 
   * @param limit The length of the list to return. Limit 3 will show the 3 most profitable stocks.
   * @return A list of stocks in descending order of profitability.
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

  public void addObserver(GameObserver observer) {
    this.observers.add(observer);
  }

  public void removeObserver(GameObserver observer) {
    this.observers.remove(observer);
  }

  /**
   * Notify all observers that the week has advanced.
   */
  private void notifyWeekAdvanced() {
    for (GameObserver observer : observers) {
      observer.onWeekAdvanced(this.week);
    }
  }

  /**
   * Notify all observers that a stock price changed.
   * @param symbol The symbol of the stock that changed price
   */
  private void notifyStockPriceChanged(String symbol) {
      for (GameObserver observer : observers) {
          observer.onStockPriceChanged(symbol);
      }
  }

  /**
   * Notify all observers that a purchase has been completed.
   * @param symbol The symbol of the stock that was purchased
   * @param quantity The quantity of the stock that was purchased
   */
  private void notifyPurchaseCompleted(String symbol, BigDecimal quantity) {
    for (GameObserver observer : observers) {
        observer.onPurchaseCompleted(symbol, quantity.toString());
    }
  }

  /**
   * Notify all observers that a sale has been completed.
   * @param symbol The symbol of the stock that was sold
   * @param quantity The quantity of the stock that was sold
   */
  private void notifySaleCompleted(String symbol, BigDecimal quantity) {
    for (GameObserver observer : observers) {
        observer.onSaleCompleted(symbol, quantity.toString());
    }
  }
}
