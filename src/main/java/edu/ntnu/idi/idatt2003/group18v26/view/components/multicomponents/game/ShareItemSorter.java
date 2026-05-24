package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareCurrentValueSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareNameSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SharePurchasePriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareQuantitySortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareSellAllButton;
import javafx.scene.layout.HBox;

public class ShareItemSorter extends HBox {
  private ShareNameSortButton nameSortBtn;
  private ShareQuantitySortButton qtySortBtn;
  private SharePurchasePriceSortButton ppSortBtn;
  private ShareCurrentValueSortButton currentValSortBtn;
  private ShareSellAllButton sellAllBtn;

  public ShareItemSorter() {
    this.nameSortBtn = new ShareNameSortButton();
    this.qtySortBtn = new ShareQuantitySortButton();
    this.ppSortBtn = new SharePurchasePriceSortButton();
    this.currentValSortBtn = new ShareCurrentValueSortButton();
    this.sellAllBtn = new ShareSellAllButton(); 
  }
}
