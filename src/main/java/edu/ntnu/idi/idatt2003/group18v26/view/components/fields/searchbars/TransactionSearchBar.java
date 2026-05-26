package edu.ntnu.idi.idatt2003.group18v26.view.components.fields.searchbars;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TransactionSearchBar extends SearchBar {
  public TransactionSearchBar() {
    super();

    this.textProperty().addListener((observable, oldValue, newValue) -> {
      GameController.getInstance().searchShare(newValue);
    });

    setEvent(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
          GameController.getInstance().searchShare(getText()); // TODO: change to another method.
        }
      }
    });
  }
}
