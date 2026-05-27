package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

public class WeekDisplay extends DisplayType {
  public WeekDisplay() {
    super();
  }

  public void update(int week) {
    this.setDisplayText(Integer.toString(week));
  }
}
