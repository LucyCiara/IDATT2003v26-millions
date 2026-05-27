package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class LoadButton extends ButtonType {
  public LoadButton() {
    super();
    setText("Load");
    setOnAction(e -> GameController.getInstance().load());
  }
}
