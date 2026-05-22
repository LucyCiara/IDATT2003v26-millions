package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public abstract class ButtonType extends Button {
  protected ButtonType() {
    setId("button1");
    getStyleClass().add("button");
  }

  public void setWidthRestriction(double width) {
    setMinWidth(width);
    setMaxWidth(width);
    setWrapText(true);
  }

  public void setHeightRestriction(double height) {
    setMinHeight(height);
    setMaxHeight(height);
  }
  
}
