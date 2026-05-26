package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

public class StockGainerLoserRow extends RowType {
  public StockGainerLoserRow() {
    super();
  }

  public void addItem(String stockSymbol, String stockGain) {
    this.getContents().getChildren().add(
        new StockGainerLoserItem(stockSymbol, stockGain)
    );
  }
}