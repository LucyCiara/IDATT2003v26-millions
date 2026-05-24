package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class StockMarketButton extends ToggleButton {
  public StockMarketButton() {
    super();
    setText("Stock Market");
    setOnAction(e -> System.out.println("Stock Market"));
  }
}
