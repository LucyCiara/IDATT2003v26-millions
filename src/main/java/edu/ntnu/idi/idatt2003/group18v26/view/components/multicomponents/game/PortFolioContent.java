package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.searchbars.PortfolioSearchBar;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.searchbars.SearchBar;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows.ShareItemSorter;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows.ShareRows;
import javafx.scene.layout.VBox;

public class PortFolioContent extends VBox {
  private SearchBar search;
  private ShareItemSorter sorter;
  private ShareRows shares;

  public PortFolioContent() {
    this.search = new PortfolioSearchBar();
    this.sorter = new ShareItemSorter();
    this.shares = new ShareRows();
    getChildren().addAll(this.search, this.sorter, this.shares);
  }

  public void addShare(String shareSymbol, String shareName, String shareQty, String purchasePrice, String currentValue) {
    this.shares.addItem(shareSymbol, shareName, shareQty, purchasePrice, currentValue);
  }

  public void clearShares() {
    this.shares.clear();
  }

  public void updateShares(String symbol) {

  }
}
