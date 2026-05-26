package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.Arrays;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareCurrentValueSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareNameSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SharePurchasePriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareQuantitySortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareSellAllButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareSymbolSortButton;
import javafx.geometry.HPos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ShareItemSorter extends GridPane {
  private ShareSymbolSortButton symSortBtn;
  private ShareNameSortButton nameSortBtn;
  private ShareQuantitySortButton qtySortBtn;
  private SharePurchasePriceSortButton ppSortBtn;
  private ShareCurrentValueSortButton currentValSortBtn;
  private ShareSellAllButton sellAllBtn;

  public ShareItemSorter() {
    this.symSortBtn = new ShareSymbolSortButton();
    this.nameSortBtn = new ShareNameSortButton();
    this.qtySortBtn = new ShareQuantitySortButton();
    this.ppSortBtn = new SharePurchasePriceSortButton();
    this.currentValSortBtn = new ShareCurrentValueSortButton();
    this.sellAllBtn = new ShareSellAllButton(); 

    ButtonType[] buttons = new ButtonType[] {this.symSortBtn, this.nameSortBtn, this.qtySortBtn, this.ppSortBtn, this.currentValSortBtn, this.sellAllBtn};
    Arrays.asList(buttons).forEach(btn -> ((ButtonType) btn).setMaxWidth(Double.MAX_VALUE));
    ColumnConstraints columnConstraints = new ColumnConstraints();
    columnConstraints.setPercentWidth(100);
    for (int i = 0; i < buttons.length; i++) {
      add(buttons[i], i, 0, 1, 1);
      setHalignment(buttons[i], HPos.CENTER);
      getColumnConstraints().add(columnConstraints);
    }
  }
}
