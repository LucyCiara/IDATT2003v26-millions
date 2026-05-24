package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class TransactionHistoryButton extends ToggleButton {
  public TransactionHistoryButton() {
    super();
    setText("Transaction History");
    setOnAction(e -> NavigationController.getInstance().selectTransactionHistory());
  }
}
