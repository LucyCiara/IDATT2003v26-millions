package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class StatusDisplay extends DisplayType {
  public StatusDisplay() {
    super();
  }

  public void update() {
    this.setDisplayText(GameController.getInstance().getPlayerStatus());
  }
}
