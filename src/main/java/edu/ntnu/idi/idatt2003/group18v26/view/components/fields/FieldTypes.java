package edu.ntnu.idi.idatt2003.group18v26.view.components.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public abstract class FieldTypes extends VBox {
  private TextField inputText;

  protected FieldTypes(String labelText, String promptText) {
    this.inputText = new TextField();
    this.inputText.setPromptText(promptText);
    this.getChildren().addAll(new Label(labelText), this.inputText);
  }

  public String getInput() {
    return this.inputText.getText();
  }
}
