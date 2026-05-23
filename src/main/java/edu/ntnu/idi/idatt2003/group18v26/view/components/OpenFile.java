package edu.ntnu.idi.idatt2003.group18v26.view.components;


import edu.ntnu.idi.idatt2003.group18v26.view.components.LabelStandard;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ClearFileButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.OpenFileButton;

import javafx.scene.layout.VBox;

public class OpenFile extends VBox {
  private OpenFileButton openFileBtn;
  private ClearFileButton clearFileBtn;

  public OpenFile() {
    this.openFileBtn = new OpenFileButton();
    this.clearFileBtn = new ClearFileButton();
    this.getChildren().addAll(
        new LabelStandard("Select CSV from files (Optional):"),
        this.openFileBtn,
        this.clearFileBtn
    );
  }

  public void changeOpenFileButton(String fileName) {
    this.openFileBtn.changeTextToFile(fileName);
  }

}
