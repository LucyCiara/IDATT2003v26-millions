package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class ShareQuantitySortButton extends ButtonType {
  public ShareQuantitySortButton() {
    super();
    setText("Quantity");
    setOnAction(e -> GameController.getInstance().fetchQuantitySortedPortfolio(true));
  }
}
