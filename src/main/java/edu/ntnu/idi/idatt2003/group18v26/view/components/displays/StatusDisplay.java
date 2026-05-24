package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class StatusDisplay extends DisplayType {
  public StatusDisplay() {
    super();
    this.update();
  }

  public void update() {
    this.setText(GameController.getInstance().getPlayerStatus());
  }
}
