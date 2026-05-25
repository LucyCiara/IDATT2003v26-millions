package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class ShareCurrentValueSortButton extends ButtonType {
  public ShareCurrentValueSortButton() {
    super();
    setText("Current Value");
    setOnAction(e -> GameController.getInstance().fetchCurrentValueSortedPortfolio(true));
  }
}
