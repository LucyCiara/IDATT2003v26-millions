package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class TransactionRows extends RowType {
  public TransactionRows() {
    super();
  }

  public void addItem(String week, String transactionType, String stock, String quantity, String price, String costReward) {
    this.getContents().getChildren().add(
      new TransactionItem(week, transactionType, stock, quantity, price, costReward)
    );
  }

}
