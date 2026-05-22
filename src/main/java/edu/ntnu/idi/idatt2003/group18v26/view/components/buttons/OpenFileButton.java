package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class OpenFileButton extends ButtonType {
  public OpenFileButton() {
    super();
    super.setText("Open stock CSV...");
    super.setOnAction(e -> GameController.getInstance().setExchangeFromFile());
  }

  public void changeTextToFile(String fileName) {
    if (fileName != null) {
      super.setText(fileName);
    } else {
      super.setText("Open stock CSV...");
    }
  }
}
