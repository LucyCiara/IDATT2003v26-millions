package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class ContinueButton extends ButtonType {
  public ContinueButton() {
    super();
    setText("Continue");
    setOnAction(e -> GameController.getInstance().load());
  }
}