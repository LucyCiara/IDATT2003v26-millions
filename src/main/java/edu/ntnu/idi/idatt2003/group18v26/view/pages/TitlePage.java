package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.title.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class TitlePage extends StackPane {
  public TitlePage() {
    getStyleClass().add("page");
    getChildren().add(new BackgroundImage());
  }
}
