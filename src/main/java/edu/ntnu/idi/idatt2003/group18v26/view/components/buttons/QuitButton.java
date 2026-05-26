package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import javafx.application.Platform;

public class QuitButton extends ButtonType {
  public QuitButton() {
    super();
    setText("Quit");
    setOnAction(e -> Platform.exit());
  }
}