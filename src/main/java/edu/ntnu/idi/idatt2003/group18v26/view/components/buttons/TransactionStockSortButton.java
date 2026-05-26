package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class TransactionStockSortButton extends ButtonType {
  public TransactionStockSortButton() {
    super();
    setText("Stock");
    setOnAction(e -> GameController.getInstance().fetchStockSortedTransactionHistory(true));
  }
}
