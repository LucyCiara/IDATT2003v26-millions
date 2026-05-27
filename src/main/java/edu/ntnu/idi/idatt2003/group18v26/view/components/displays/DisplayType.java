package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Class for display components in the application.
 */
public class DisplayType extends BorderPane {
  private Label text;

  /**
   * Constructs a new DisplayType.
   */
  public DisplayType() {
    getStyleClass().add("nonInteractive");
    this.text = new Label();
    setCenter(this.text);
    setMaxWidth(Double.MAX_VALUE);
  }

  public void setDisplayText(String text) {
    this.text.setText(text);
  }

  public String getDisplayText() {
    return this.text.getText();
  }

}
