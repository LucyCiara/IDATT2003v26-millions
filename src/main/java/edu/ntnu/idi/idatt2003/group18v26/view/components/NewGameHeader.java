package edu.ntnu.idi.idatt2003.group18v26.view.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class NewGameHeader extends BorderPane {
  public NewGameHeader() {
    setPadding(new Insets(5,5,5,5));
    setCenter(new Label("To start your finance journey, enter your name and starting money."));
    getStyleClass().add("header");
  }
}
