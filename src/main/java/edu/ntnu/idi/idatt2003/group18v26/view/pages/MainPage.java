package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.BackgroundImage;
import edu.ntnu.idi.idatt2003.group18v26.view.components.MainMenuButtons;
import javafx.scene.layout.BorderPane;

public class MainPage extends BorderPane {
  public MainPage() {
    getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    getStyleClass().add("page");
    setCenter(new BackgroundImage());
  }
}
