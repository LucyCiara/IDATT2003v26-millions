package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.newgame;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.CloseButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StartButton;
import javafx.scene.layout.HBox;

public class NewGameButtons extends HBox {
  public NewGameButtons() {
    this.getChildren().addAll(new StartButton(), new CloseButton());
  }
}
