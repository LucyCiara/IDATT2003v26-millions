package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DisplayType extends BorderPane {
  private Label text;
  public DisplayType() {
    getStyleClass().add("nonInteractive");
    this.text = new Label();
    setCenter(this.text);
  }

  public void setDisplayText(String text) {
    this.text.setText(text);
  }

  public void setWidthRestriction(double width) {
    setMinWidth(width);
    setMaxWidth(width);
  }
}
