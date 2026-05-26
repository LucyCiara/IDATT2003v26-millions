package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class ShareSymbolSortButton extends ButtonType {
  public ShareSymbolSortButton() {
    super();
    setText("Symbol");
    setOnAction(e -> GameController.getInstance().fetchSymbolSortedPortfolio(true));
  }
}
