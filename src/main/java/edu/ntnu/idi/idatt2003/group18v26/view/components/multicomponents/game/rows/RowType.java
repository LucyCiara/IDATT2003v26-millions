package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Class for the row type component in the application,
 * which is a scrollable container for other components.
 */
public class RowType extends ScrollPane {
  private VBox contents;

  /**
   * Constructs a new RowType.
   */
  public RowType() {
    this.contents = new VBox();
    this.contents.getStyleClass().add("page");
    this.contents.setFillWidth(true);
    getStyleClass().add("page");
    setContent(this.contents);
    setHbarPolicy(ScrollBarPolicy.NEVER);
    setFitToWidth(true);
  }

  /**
   * Clears the contents of the row.
   */
  public void clear() {
    this.contents.getChildren().clear();
  }

  /**
   * Returns the VBox containing the contents of the row.
   *
   * @return the VBox containing the contents of the row
   */
  public VBox getContents() {
    return this.contents;
  }
}