package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.AdvanceWeekButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.WeekDisplay;
import javafx.scene.layout.VBox;

public class WeekWidget extends VBox {
  private WeekDisplay weekDisp;
  private AdvanceWeekButton advWeekBtn;

  public WeekWidget() {
    setMaxWidth(200);
    this.weekDisp = new WeekDisplay();
    this.advWeekBtn = new AdvanceWeekButton();
    this.advWeekBtn.setMaxWidth(Double.MAX_VALUE);
    getChildren().addAll(this.weekDisp, this.advWeekBtn);
  }

  public void updateWeek(int week) {
    this.weekDisp.update(week);
  }
}
