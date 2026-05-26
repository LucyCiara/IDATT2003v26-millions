package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.title;

import javafx.scene.layout.StackPane;

/**
 * Class for the background image component in the title screen of the application.
 */
public class BackgroundImage extends StackPane {
  /**
   * Constructs a new BackgroundImage.
   */
  public BackgroundImage() {
    super();
    setId("background-image");
    getChildren().add(new MainMenuButtons());
  }
}
