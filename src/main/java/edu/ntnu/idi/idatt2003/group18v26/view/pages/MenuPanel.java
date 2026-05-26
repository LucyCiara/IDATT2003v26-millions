package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.menu.MenuButtons;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MenuPanel extends StackPane {
  public MenuPanel() {
    Pane blackout = new Pane();
    blackout.setId("pause");
    getChildren().addAll(blackout, new MenuButtons());
  }
}
