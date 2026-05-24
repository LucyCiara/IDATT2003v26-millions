package edu.ntnu.idi.idatt2003.group18v26.view.components.displays;

import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public abstract class DisplayType extends ScrollPane {
  private Text text;
  protected DisplayType() {
    this.text = new Text();
    this.text.setTextAlignment(TextAlignment.CENTER);
  }

  public void setText(String text) {
    this.text.setText(text);
  }

  public void setWidthRestriction(double width) {
    setMinWidth(width);
    setMaxWidth(width);
  }
}
