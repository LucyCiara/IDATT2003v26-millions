package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class StockInfoButton extends ButtonType {
  public StockInfoButton(String stockSymbol) {
    super();
    setText(stockSymbol);
    setMaxWidth(Double.MAX_VALUE);
    setOnAction(e -> NavigationController.getInstance().selectStock(stockSymbol));
  }
}
