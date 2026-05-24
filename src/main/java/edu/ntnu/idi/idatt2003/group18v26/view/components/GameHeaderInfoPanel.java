package edu.ntnu.idi.idatt2003.group18v26.view.components;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.MenuButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.MoneyDisplay;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.NameDisplay;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.StatusDisplay;
import javafx.scene.layout.HBox;

public class GameHeaderInfoPanel extends HBox {
  private NameDisplay nameDisp;
  private MoneyDisplay moneyDisp;
  private StatusDisplay statusDisp;
  private MenuButton menuBtn;

  public GameHeaderInfoPanel() {
    this.getChildren().addAll(nameDisp, moneyDisp, statusDisp, menuBtn);    
  }

  public void updateDisplays() {
    this.moneyDisp.update();
    this.statusDisp.update();
  }
}
