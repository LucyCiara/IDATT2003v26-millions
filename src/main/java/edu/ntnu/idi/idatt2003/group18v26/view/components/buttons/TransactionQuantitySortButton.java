package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class TransactionQuantitySortButton extends ButtonType {
  public TransactionQuantitySortButton() {
    super();
    setText("Quantity");
    setOnAction(e -> GameController.getInstance().fetchQuantitySortedTransactionHistory(true));
  }
}
