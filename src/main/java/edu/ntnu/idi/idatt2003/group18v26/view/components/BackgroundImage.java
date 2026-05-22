package edu.ntnu.idi.idatt2003.group18v26.view.components;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class BackgroundImage extends StackPane {
  public BackgroundImage() {
    super();
    setId("background-image");
    getChildren().add(new MainMenuButtons());
  }
}
