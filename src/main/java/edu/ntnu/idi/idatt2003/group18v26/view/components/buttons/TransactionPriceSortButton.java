package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class TransactionPriceSortButton extends ButtonType {
  public TransactionPriceSortButton() {
    super();
    setText("Price");
    setOnAction(e -> GameController.getInstance().fetchPriceSortedTransactionHistory(true));
  }
}
