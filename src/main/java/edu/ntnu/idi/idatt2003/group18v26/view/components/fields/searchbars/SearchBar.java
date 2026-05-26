package edu.ntnu.idi.idatt2003.group18v26.view.components.fields.searchbars;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * Abstract class for search bars in the application.
 * It extends the JavaFX TextField class and 
 * provides a common style and prompt text for all search bars in the application.
 */
public abstract class SearchBar extends TextField {

  protected SearchBar() {
    getStyleClass().add("field");
    setPromptText("Search...");
  }

  public void setEvent(EventHandler<? super KeyEvent> e) {
    setOnKeyPressed(e);
  }

}
