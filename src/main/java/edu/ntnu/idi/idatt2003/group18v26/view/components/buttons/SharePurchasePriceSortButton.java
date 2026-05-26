package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class SharePurchasePriceSortButton extends ButtonType {
  public SharePurchasePriceSortButton() {
    super();
    setText("Purchase Price");
    setOnAction(e -> GameController.getInstance().fetchPurchasePriceSortedPortfolio(true));
  }
}
