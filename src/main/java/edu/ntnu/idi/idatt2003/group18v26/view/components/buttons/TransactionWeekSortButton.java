package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class TransactionWeekSortButton extends ButtonType {
  public TransactionWeekSortButton() {
    super();
    setText("Week");
    setOnAction(e -> GameController.getInstance().fetchWeekTransactionHistory(true));
  }
}
