package edu.ntnu.idi.idatt2003.group18v26.view.components.fields.searchbars;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StockMarketSearchBar extends SearchBar {
  public StockMarketSearchBar() {
    super();
    setEvent(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
          GameController.getInstance().searchStock(getText());
        }
      }
    });
  }
}
