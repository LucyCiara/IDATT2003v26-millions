package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class MainMenuButton extends ButtonType {
  public MainMenuButton() {
    super();
    setText("Main Menu");
    setOnAction(e -> NavigationController.getInstance().showTitlePage());
  }
}
