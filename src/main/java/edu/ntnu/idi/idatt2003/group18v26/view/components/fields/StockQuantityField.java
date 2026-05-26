package edu.ntnu.idi.idatt2003.group18v26.view.components.fields;

import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.fieldtypes.FieldType;
import javafx.scene.control.TextField;

public class StockQuantityField extends TextField {
  public StockQuantityField() {
    super();
    setPromptText("Qty");
    setMaxWidth(Double.MAX_VALUE);
  }

  public void clearField() {
    clear();
  }
}
