package edu.ntnu.idi.idatt2003.group18v26.view.components.fields.fieldtypes;

/**
 * Class for the starting money field in the application.
 */
public class StartingMoneyField extends FieldType {
  public StartingMoneyField() {
    super("Starting money:", "9000.00");
  }

  public String getStartingMoney() {
    return getInput();
  }
}