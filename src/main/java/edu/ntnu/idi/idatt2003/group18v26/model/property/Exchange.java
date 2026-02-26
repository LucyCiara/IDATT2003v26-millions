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

public class Exchange {
  private String name;
  private int week;
  private HashMap<String, Stock> stockMap;
  private Random random;

  public Exchange(String name, List<Stock> stocks) {
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
    List<Stock> resultStocks = new ArrayList<Stock>();
    for (Stock stock : this.stockMap.values()) {
      if (stock.getSymbol().contains(searchTerm) || stock.getCompany().contains(searchTerm)) {
        resultStocks.add(stock);
      }
    }
    return resultStocks;
  }

  public Transaction buy(String symbol, BigDecimal quantity, Player player) {
    Transaction purchase = new Purchase(
        new Share(this.stockMap.get(symbol), quantity, this.stockMap.get(symbol).getSalesPrice()), this.week);
    purchase.commit(player);
    return purchase;
  }

  public Transaction sell(String symbol, Player player) {
    Transaction sale = new Sale(
        new Share(this.stockMap.get(symbol), this.stockMap.get(symbol).getSalesPrice()),
        this.week);
    sale.commit(player);
    return sale;
  }
}
