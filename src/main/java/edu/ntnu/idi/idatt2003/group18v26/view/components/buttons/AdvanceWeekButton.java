package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class AdvanceWeekButton extends ButtonType {
  public AdvanceWeekButton() {
    super();
    setText("Advance Week");
    setOnAction(e -> GameController.getInstance().advanceWeek());
  }
}
