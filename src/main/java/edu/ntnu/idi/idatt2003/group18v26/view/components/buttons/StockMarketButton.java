package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class StockMarketButton extends ToggleButton {
  public StockMarketButton() {
    super();
    setText("Stock Market");
    setOnAction(e -> NavigationController.getInstance().selectStockMarket());
  }
}
