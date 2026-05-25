package edu.ntnu.idi.idatt2003.group18v26.view.components.searchbars;

import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.ShareRows;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public abstract class SearchBar extends TextField {

  protected SearchBar() {
    getStyleClass().add("field");
    setPromptText("Search...");
  }

  public void setEvent(EventHandler<? super KeyEvent> e) {

    setOnKeyPressed(e);
  }

}
