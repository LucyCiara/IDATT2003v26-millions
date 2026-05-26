package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.menu.MenuButtons;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Class for the menu panel component in the application.
 */
public class MenuPanel extends StackPane {

  /**
   * Constructs a new MenuPanel.
   */
  public MenuPanel() {
    Pane blackout = new Pane();
    blackout.setId("pause");
    getChildren().addAll(blackout, new MenuButtons());
  }
}
