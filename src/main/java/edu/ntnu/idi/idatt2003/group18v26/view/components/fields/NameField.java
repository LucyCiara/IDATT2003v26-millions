package edu.ntnu.idi.idatt2003.group18v26.view.components.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NameField extends FieldType {
  public NameField() {
    super("Name:", "Satoru Gojo");
  }

  public String getNameInput() {
    return super.getInput();
  }
}
