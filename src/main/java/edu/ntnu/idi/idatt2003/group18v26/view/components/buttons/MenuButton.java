package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class MenuButton extends ButtonType {
  public MenuButton() {
    super();
    setText("Menu");
    setOnAction(e -> NavigationController.getInstance().showMenu());
  }
}
