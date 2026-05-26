package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class NetWorthDisplay extends DisplayType {
  public NetWorthDisplay() {
    super();
  }

  public void update() {
    this.setDisplayText("Your net worth: $" + GameController.getInstance().getNetWorth());
  }
}