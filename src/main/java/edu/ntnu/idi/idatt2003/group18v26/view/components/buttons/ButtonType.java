package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public abstract class ButtonType extends Button {
  protected ButtonType() {
    setId("button1");
    getStyleClass().add("button");
    setWrapText(true);
    setTextAlignment(TextAlignment.CENTER);
  }

}
