package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class ShareNameSortButton extends ButtonType {
  public ShareNameSortButton() {
    super();
    setText("Company Name");
    setOnAction(e -> GameController.getInstance().fetchCompanySortedPortfolio(true));
  }
}
