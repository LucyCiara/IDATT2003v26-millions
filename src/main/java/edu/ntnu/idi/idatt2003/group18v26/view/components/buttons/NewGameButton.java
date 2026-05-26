package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

/**
 * A button for starting a new game.
 */
public class NewGameButton extends ButtonType {
  /**
   * Constructs a new NewGameButton.
   */
  public NewGameButton() {
    super();
    setText("New Game");
    setOnAction(e -> GameController.getInstance().onNewGame());
  }
}
