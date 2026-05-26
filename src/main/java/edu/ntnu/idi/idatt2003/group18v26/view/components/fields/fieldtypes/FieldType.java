package edu.ntnu.idi.idatt2003.group18v26.view.components.fields.fieldtypes;

import edu.ntnu.idi.idatt2003.group18v26.view.components.LabelStandard;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public abstract class FieldType extends VBox {
  private TextField inputText;

  protected FieldType(String LabelStandardText, String promptText) {
    this.inputText = new TextField();
    this.inputText.setPromptText(promptText);
    this.getChildren().addAll(new LabelStandard(LabelStandardText), this.inputText);
    this.inputText.getStyleClass().add("field");
  }

  protected String getInput() {
    return this.inputText.getText();
  }

  public void clearField() {
    this.inputText.clear();
  }
}
