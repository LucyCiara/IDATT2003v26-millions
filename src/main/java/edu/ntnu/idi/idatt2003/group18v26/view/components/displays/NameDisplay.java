package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class NameDisplay extends DisplayType {
  public NameDisplay() {
    super(GameController.getInstance().getPlayerName());
  }
}
