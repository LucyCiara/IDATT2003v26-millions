package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

/** 
 * Class for the transaction cost/reward sort button component in the application.
 */
public class TransactionCostRewardSortButton extends ButtonType {

  /** 
   * Constructs a new TransactionCostRewardSortButton.
   */
  public TransactionCostRewardSortButton() {
    super();
    setText("Cost/Reward");
    setOnAction(e -> GameController.getInstance().fetchCostRewardSortedTransactionHistory(true));
  }
}
