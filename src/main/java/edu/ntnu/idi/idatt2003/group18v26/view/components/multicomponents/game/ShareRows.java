package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ShareRows extends ScrollPane {
  private VBox contents;
  public ShareRows() {
    this.contents = new VBox();
  }

  public void clear() {
    this.contents.getChildren().clear();
  }

  public void addItem(String shareSymbol, String shareName, String shareQty, String purchasePrice, String currentValue) {
    this.getChildren().add(
        new ShareItem(shareSymbol, shareName, shareQty, purchasePrice, currentValue)
    );
  }
}
