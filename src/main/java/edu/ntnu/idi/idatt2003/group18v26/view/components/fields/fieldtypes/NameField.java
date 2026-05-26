package edu.ntnu.idi.idatt2003.group18v26.view.components.fields.fieldtypes;


/**
 * Class for the name field in the application.
 */
public class NameField extends FieldType {
  public NameField() {
    super("Name:", "Satoru Gojo");
  }

  public String getNameInput() {
    return getInput();
  }
}
