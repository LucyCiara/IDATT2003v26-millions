package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class StartButton extends ButtonType {
  public StartButton() {
    super();
    setText("Done");
    setOnAction(e -> GameController.getInstance().onGameStart());
  }
}
