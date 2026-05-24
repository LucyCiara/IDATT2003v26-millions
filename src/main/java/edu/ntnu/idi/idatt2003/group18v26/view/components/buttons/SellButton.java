package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class SellButton extends ButtonType {  
  public SellButton(String shareSymbol) {
    super();
    setText("Sell");
    setOnAction(e -> System.out.println("Sell" + shareSymbol));
  }
}
