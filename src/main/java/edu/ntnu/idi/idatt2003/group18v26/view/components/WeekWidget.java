package edu.ntnu.idi.idatt2003.group18v26.view.components;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.AdvanceWeekButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.WeekDisplay;
import javafx.scene.layout.VBox;

public class WeekWidget extends VBox {
  private WeekDisplay weekDisp;
  private AdvanceWeekButton advWeekBtn;

  public WeekWidget() {
    double width = this.getWidth();
    this.weekDisp = new WeekDisplay();
    this.weekDisp.setWidthRestriction(width);
    this.advWeekBtn = new AdvanceWeekButton();
    this.advWeekBtn.setWidthRestriction(width); 
  }

  public void updateWeek() {
    this.weekDisp.update();
  }
}
