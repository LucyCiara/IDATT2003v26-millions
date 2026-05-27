package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.MenuButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.MoneyDisplay;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.NameDisplay;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.StatusDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Class for the game header info panel component.
 */
public class GameHeaderInfoPanel extends HBox {
  private NameDisplay nameDisp;
  private MoneyDisplay moneyDisp;
  private StatusDisplay statusDisp;
  private MenuButton menuBtn;

  /**
   * Constructs a new GameHeaderInfoPanel.
   */
  public GameHeaderInfoPanel() {
    this.nameDisp = new NameDisplay();
    this.moneyDisp = new MoneyDisplay();
    this.statusDisp = new StatusDisplay();
    this.menuBtn = new MenuButton();
    this.menuBtn.setMaxWidth(Double.MAX_VALUE);
    this.getChildren().addAll(nameDisp, moneyDisp, statusDisp, menuBtn);
    this.getChildren().forEach(obj -> setHgrow(obj, Priority.ALWAYS));
  }

  public void updateName(String name) {
    this.nameDisp.update(name);
  }

  public void updateMoney() {
    this.moneyDisp.update();
  }

  public void updateStatus() {
    this.statusDisp.update();
  }

}
