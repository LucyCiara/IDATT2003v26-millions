package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class SaveButton extends ButtonType {
  public SaveButton() {
    super();
    setText("Save");
    setOnAction(e -> GameController.getInstance().save());
  }
}
