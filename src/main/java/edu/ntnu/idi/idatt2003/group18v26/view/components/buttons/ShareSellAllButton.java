package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class ShareSellAllButton extends ButtonType {
  public ShareSellAllButton() {
    super();
    setText("Sell All");
    setOnAction(e -> System.out.println("Sell All"));
  }
}
