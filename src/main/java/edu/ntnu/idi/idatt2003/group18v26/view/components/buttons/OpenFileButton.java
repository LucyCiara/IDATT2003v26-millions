package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class OpenFileButton extends ButtonType {
  private static final String defaultText = "Open file...";

  public OpenFileButton() {
    super();
    setText(defaultText);
    setOnAction(e -> GameController.getInstance().setExchangeFromFile());
  }

  public void changeTextToFile(String fileName) {
    if (fileName != null) {
      setText(fileName);
    } else {
      setText(defaultText);
    }
  }
}
