package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.searchbars.TransactionSearchBar;
import edu.ntnu.idi.idatt2003.group18v26.view.components.rows.TransactionItem;
import edu.ntnu.idi.idatt2003.group18v26.view.components.rows.TransactionItemSorter;
import edu.ntnu.idi.idatt2003.group18v26.view.components.rows.TransactionRows;
import javafx.scene.layout.VBox;

public class TransactionHistoryContent extends VBox {
  private TransactionSearchBar searchBar;
  private TransactionItemSorter transSort;
  private TransactionRows transRows;

  public TransactionHistoryContent() {
    this.searchBar = new TransactionSearchBar();
    this.transSort = new TransactionItemSorter();
    this.transRows = new TransactionRows();
    getChildren().addAll(this.searchBar, this.transSort, this.transRows);
  }

  public void addTransaction(String week, String transactionType, String stock, String quantity, String price, String costReward) {
    this.transRows.addItem(week, transactionType, stock, quantity, price, costReward);
  }

  public void clearTransactions() {
    this.transRows.clear();
  }
  
}
