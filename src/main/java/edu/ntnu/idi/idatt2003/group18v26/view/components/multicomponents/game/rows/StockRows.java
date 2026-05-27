package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

import java.util.HashMap;

public class StockRows extends RowType {
  private HashMap<String, StockItem> items;

  public StockRows() {
    super();
    this.items = new HashMap<>();
  }

  public void addItem(String stockSymbol, String stockName, String purchasePrice) {
    this.items.putIfAbsent(stockSymbol, new StockItem(stockSymbol, stockName, purchasePrice));
    this.getContents().getChildren().add(items.get(stockSymbol));
  }

  public void update(String symbol) {
    this.items.get(symbol).updatePrice();
  }
}