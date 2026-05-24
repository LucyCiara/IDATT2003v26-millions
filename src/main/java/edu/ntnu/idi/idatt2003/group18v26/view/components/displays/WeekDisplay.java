package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class WeekDisplay extends DisplayType {
  public WeekDisplay() {
    super();
  }

  public void update() {
    this.setDisplayText(GameController.getInstance().getWeek());
  }
}
