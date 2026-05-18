package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class NewGameButton extends ButtonType {
  public NewGameButton() {
    super();
    super.setText("New Game");
    super.setOnAction(e -> NavigationController.getInstance().showNewGamePanel());
  }
}
