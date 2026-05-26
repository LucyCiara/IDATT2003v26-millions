package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class TransactionTypeSortButton extends ButtonType {
  public TransactionTypeSortButton() {
    super();
    setText("Type");
    setOnAction(e -> GameController.getInstance().fetchTypeTransactionHistory(true));
  }
}
