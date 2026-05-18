package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.NewGameButtons;
import edu.ntnu.idi.idatt2003.group18v26.view.components.NewGameFields;
import edu.ntnu.idi.idatt2003.group18v26.view.components.NewGameHeader;
import javafx.scene.layout.BorderPane;

public class NewGamePanel extends BorderPane {
  public NewGamePanel() {
    getStyleClass().add("page");
    toFront();
    setMaxSize(200, 300);
    setTop(new NewGameHeader());
    setCenter(new NewGameFields());
    setBottom(new NewGameButtons());
  }
}
