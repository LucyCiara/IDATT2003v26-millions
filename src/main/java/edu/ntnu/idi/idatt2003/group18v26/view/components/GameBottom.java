package edu.ntnu.idi.idatt2003.group18v26.view.components;

import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.NetWorthDisplay;
import javafx.scene.layout.BorderPane;

public class GameBottom extends BorderPane {
  private NetWorthDisplay netWorthDisp;
  private WeekWidget weekWidget;

  public GameBottom() {
    this.netWorthDisp = new NetWorthDisplay();
    this.weekWidget = new WeekWidget();
    setCenter(netWorthDisp);
    setRight(weekWidget);
  }
}
