package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

/** 
 * Class for the share symbol sort button component in the application.
 */
public class ShareSymbolSortButton extends ButtonType {

  /** 
   * Constructs a new ShareSymbolSortButton.
   */
  public ShareSymbolSortButton() {
    super();
    setText("Symbol");
    setOnAction(e -> GameController.getInstance().fetchSymbolSortedPortfolio(true));
  }
}
