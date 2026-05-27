package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class SellButton extends ButtonType {  
  public SellButton(String shareSymbol) {
    super();
    setId("sell");
    setText("Sell");
    setOnAction(e -> GameController.getInstance().sellShare(shareSymbol));
  }
}
