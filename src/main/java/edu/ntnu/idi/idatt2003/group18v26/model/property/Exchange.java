package edu.ntnu.idi.idatt2003.group18v26.model.property;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Purchase;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Sale;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;

public class Exchange {
  private String name;
  private int week;
  private HashMap<String, Stock> stockMap;
  private Random random;

  public Exchange(String name, List<Stock> stocks) {
    ParameterValidator.stringChecker(name, "name");
    ParameterValidator.objectChecker(stocks, "stocks");
    this.name = name;
    stocks.forEach(stock -> this.stockMap.put(stock.getSymbol(), stock));
  }

  public String getName() {
    return this.name;
  }

  public int getWeek() {
    return this.week;
  }

  public boolean hasStock(String symbol) {
    return this.stockMap.containsKey(symbol);
  }

  public Stock getStock(String symbol) {
    return this.stockMap.get(symbol);
  }

  public List<Stock> findStocks(String searchTerm) {
    ParameterValidator.stringChecker(searchTerm, "searchTerm");
    List<Stock> resultStocks = new ArrayList<Stock>();
    for (Stock stock : this.stockMap.values()) {
      if (stock.getSymbol().contains(searchTerm) || stock.getCompany().contains(searchTerm)) {
        resultStocks.add(stock);
      }
    }
    return resultStocks;
  }

  public Transaction buy(String symbol, BigDecimal quantity, Player player) {
    ParameterValidator.stringChecker(symbol, "symbol");
    ParameterValidator.bigDecimalChecker(quantity, "quantity");
    ParameterValidator.objectChecker(player, "player");
    Transaction purchase = new Purchase(
        new Share(this.stockMap.get(symbol), quantity, this.stockMap.get(symbol).getSalesPrice()), this.week);
    purchase.commit(player);
    return purchase;
  }

  public Transaction sell(Share share, Player player) {
    ParameterValidator.objectChecker(share, "share");
    ParameterValidator.objectChecker(player, "player");
    Transaction sale = new Sale(
        share,
        this.week);
    sale.commit(player);
    return sale;
  }

  public void advance() {
    for (Stock stock : this.stockMap.values()) {
      stock.addNewSalesPrice(stock.getSalesPrice().multiply(new BigDecimal(this.random.nextDouble() + 0.5)));
    }
    this.week++;
  }
}
