package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class ClearFileButton extends ButtonType {
  public ClearFileButton() {
    super.setText("Clear");
    super.setOnAction(e -> GameController.getInstance().onClearFile());
  }
}
