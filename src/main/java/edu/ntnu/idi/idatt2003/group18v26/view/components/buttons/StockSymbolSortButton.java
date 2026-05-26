package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class StockSymbolSortButton extends ButtonType {
  public StockSymbolSortButton() {
    super();
    setText("Symbol");
    setOnAction(e -> GameController.getInstance().fetchSymbolSortedStockMarket(true));
  }
}
