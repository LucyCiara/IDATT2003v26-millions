package edu.ntnu.idi.idatt2003.group18v26.view.components;

import java.util.HashMap;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ClearFileButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.OpenFileButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.NameField;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.StartingMoneyField;
import javafx.scene.layout.VBox;

public class NewGameFields extends VBox {
  private NameField nameField;
  private StartingMoneyField startMoneyField;
  private OpenFileButton openFileBtn;
  private ClearFileButton clearFileBtn;

  public NewGameFields() {
    this.nameField = new NameField();
    this.startMoneyField = new StartingMoneyField();
    this.openFileBtn = new OpenFileButton();
    this.clearFileBtn = new ClearFileButton();
    getChildren().addAll(this.nameField, this.startMoneyField, this.openFileBtn, this.clearFileBtn);
  }

  public void changeOpenFileButton(String fileName) {
    this.openFileBtn.changeTextToFile(fileName);
  }

  public String getPlayerName() {
    return this.nameField.getNameInput();
  }

  public String getPlayerStartMoney() {
    return this.startMoneyField.getStartingMoney();
  }

  public void clearFields() {
    this.nameField.clearField();
    this.startMoneyField.clearField();
  }
}
