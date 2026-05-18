package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class CloseButton extends ButtonType {
  public CloseButton() {
    super();
    super.setText("Back");
    super.setOnAction(e -> NavigationController.getInstance().hideLastPage());
  }
}
