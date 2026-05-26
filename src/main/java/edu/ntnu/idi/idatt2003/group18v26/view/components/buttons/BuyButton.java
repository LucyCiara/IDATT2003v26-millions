package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.StockQuantityField;

public class BuyButton extends ButtonType {
  public BuyButton(String symbol, StockQuantityField stockQtyField) {
    super();
    setText("Buy");
    setOnAction(e -> NavigationController.getInstance().onBuy(symbol, stockQtyField));
  }
}
