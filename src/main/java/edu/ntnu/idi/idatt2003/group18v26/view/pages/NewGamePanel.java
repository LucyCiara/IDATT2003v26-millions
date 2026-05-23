package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.NewGameButtons;
import edu.ntnu.idi.idatt2003.group18v26.view.components.NewGameFields;
import edu.ntnu.idi.idatt2003.group18v26.view.components.NewGameHeader;
import javafx.scene.layout.BorderPane;

public class NewGamePanel extends BorderPane {
  private NewGameFields newGameFields;

  public NewGamePanel() {
    this.newGameFields = new NewGameFields();

    getStyleClass().add("page");
    toFront();
    setMaxSize(250, 350);
    setTop(new NewGameHeader());
    setCenter(this.newGameFields);
    setBottom(new NewGameButtons());
  }

  public void changeOpenFileButton(String fileName) {
    this.newGameFields.changeOpenFileButton(fileName);
  }

  public String getPlayerName() {
    return this.newGameFields.getPlayerName();
  }

  public String getPlayerStartMoney() {
    return this.newGameFields.getPlayerStartMoney();
  }

  public void clearFields() {
    this.newGameFields.clearFields();
  }
}
