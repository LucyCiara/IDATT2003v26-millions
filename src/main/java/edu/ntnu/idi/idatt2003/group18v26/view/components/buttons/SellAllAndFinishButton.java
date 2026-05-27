package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class SellAllAndFinishButton extends ButtonType {
  public SellAllAndFinishButton() {
    super();
    this.setId("sell");
    setText("Sell everything and finish");
    setOnAction(e -> GameController.getInstance().sellAllAndFinish());
  }
}
