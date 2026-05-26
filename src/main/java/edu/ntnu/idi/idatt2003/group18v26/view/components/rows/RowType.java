package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class RowType extends ScrollPane {
  private VBox contents;
  public RowType() {
    this.contents = new VBox();
    this.contents.getStyleClass().add("page");
    this.contents.setFillWidth(true);
    getStyleClass().add("page");
    setContent(this.contents);
    setHbarPolicy(ScrollBarPolicy.NEVER);
    setFitToWidth(true);
  }

  public void clear() {
    this.contents.getChildren().clear();
  }

  public VBox getContents() {
    return this.contents;
  }
}