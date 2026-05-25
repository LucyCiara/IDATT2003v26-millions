package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class ShareSellAllButton extends ButtonType {
  public ShareSellAllButton() {
    super();
    setText("Sell All");
    setOnAction(e -> GameController.getInstance().sellAllShares());
  }
}
