package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.newgame;

import java.util.HashMap;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ClearFileButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.OpenFileButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.NameField;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.StartingMoneyField;
import javafx.scene.layout.VBox;

public class NewGameFields extends VBox {
  private NameField nameField;
  private StartingMoneyField startMoneyField;
  private OpenFile openFile;

  public NewGameFields() {
    this.nameField = new NameField();
    this.startMoneyField = new StartingMoneyField();
    this.openFile = new OpenFile();
    getChildren().addAll(this.nameField, this.startMoneyField, this.openFile);
    this.setSpacing(10);
  }

  public void changeOpenFileButton(String fileName) {
    this.openFile.changeOpenFileButton(fileName);
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
