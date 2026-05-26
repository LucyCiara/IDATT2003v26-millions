package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import javafx.scene.control.Button;

/**
 * Abstract class for buttons in the application.
 * It extends the JavaFX Button class and 
 * provides a common style and ID for all buttons in the application.
 */
public abstract class ButtonType extends Button {
  protected ButtonType() {
    setId("button1");
    getStyleClass().add("button");
  }

}
