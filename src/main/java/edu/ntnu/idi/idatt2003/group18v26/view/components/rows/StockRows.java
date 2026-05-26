package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

public class StockRows extends RowType {
  public StockRows() {
    super();
  }

  public void addItem(String stockSymbol, String stockName, String purchasePrice) {
    this.getContents().getChildren().add(
        new StockItem(stockSymbol, stockName, purchasePrice)
    );
  }
}