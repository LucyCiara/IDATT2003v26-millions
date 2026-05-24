package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class TransactionHistoryButton extends ToggleButton {
  public TransactionHistoryButton() {
    super();
    setText("Transaction History");
    setOnAction(e -> System.out.println("Transaction History"));
  }
}
