package edu.ntnu.idi.idatt2003.group18v26.view.components.searchbars;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public abstract class SearchBar extends VBox {
  private TextField inputBar;
  private ListView<String> results;

  protected SearchBar(List<String> items) {
    this.results = new ListView<>();
    this.results.getItems().addAll(items);
    this.inputBar = new TextField();
    this.inputBar.setPromptText("Search...");
    this.inputBar.textProperty().addListener((observable, oldValue, newValue) -> {
      this.results.getItems().clear();
      this.results.getItems().addAll(
          items.stream()
              .filter(item -> item.toLowerCase()
              .contains(newValue.toLowerCase())).collect(Collectors.toList())
      );
    });
    this.inputBar.getStyleClass().add("field");
    this.getChildren().addAll(this.inputBar, this.results);
  }
}
