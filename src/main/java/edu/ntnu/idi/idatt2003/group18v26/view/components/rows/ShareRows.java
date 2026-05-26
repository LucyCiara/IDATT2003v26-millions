package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ShareRows extends RowType {
  public ShareRows() {
    super();
  }

  public void addItem(String shareSymbol, String shareName, String shareQty, String purchasePrice, String currentValue) {
    this.getContents().getChildren().add(
        new ShareItem(shareSymbol, shareName, shareQty, purchasePrice, currentValue)
    );
  }

}
