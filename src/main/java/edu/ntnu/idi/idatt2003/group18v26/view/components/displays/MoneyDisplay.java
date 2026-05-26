package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class MoneyDisplay extends DisplayType {
  public MoneyDisplay() {
    super();
  }

  public void update() {
    setDisplayText("$" + GameController.getInstance().getMoney());
  }
}
