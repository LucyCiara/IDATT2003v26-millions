package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class TransactionCostRewardSortButton extends ButtonType {
  public TransactionCostRewardSortButton() {
    super();
    setText("Cost/Reward");
    setOnAction(e -> GameController.getInstance().fetchCostRewardSortedTransactionHistory(true));
  }
}
