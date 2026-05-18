package edu.ntnu.idi.idatt2003.group18v26.view.components;

import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.NameField;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.StartingMoneyField;
import javafx.scene.layout.VBox;

public class NewGameFields extends VBox {
  public NewGameFields() {
    getChildren().addAll(new NameField(), new StartingMoneyField());
  }
}
