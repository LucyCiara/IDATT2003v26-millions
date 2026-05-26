package edu.ntnu.idi.idatt2003.group18v26.view.components.fields;

import javafx.scene.control.TextField;

/**
 * Class for the stock quantity field in the application.
 */
public class StockQuantityField extends TextField {
  /**
   * Constructs a new StockQuantityField.
   */
  public StockQuantityField() {
    super();
    setPromptText("Qty");
    setMaxWidth(Double.MAX_VALUE);
  }

  public void clearField() {
    clear();
  }
}
