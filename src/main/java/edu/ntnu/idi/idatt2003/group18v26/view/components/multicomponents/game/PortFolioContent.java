package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.searchbars.PortfolioSearchBar;
import javafx.scene.layout.VBox;

public class PortFolioContent extends VBox {
  private PortfolioSearchBar search;
  private ShareItemSorter sorter;
  private ShareRows shares;

  public PortFolioContent() {
    this.search = new PortfolioSearchBar();
    this.sorter = new ShareItemSorter();
    this.shares = new ShareRows();
  }
}
