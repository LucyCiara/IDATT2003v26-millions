package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.Arrays;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareCurrentValueSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareNameSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SharePurchasePriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareQuantitySortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareSellAllButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareSymbolSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionCostRewardSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionPriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionQuantitySortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionStockSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionTypeSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionWeekSortButton;
import javafx.geometry.HPos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TransactionItemSorter extends GridPane {
  private TransactionWeekSortButton weekBtn;
  private TransactionTypeSortButton typeBtn;
  private TransactionStockSortButton stockBtn;
  private TransactionQuantitySortButton qtyBtn;
  private TransactionPriceSortButton priceBtn;
  private TransactionCostRewardSortButton costRewardBtn;

  public TransactionItemSorter() {
    this.weekBtn = new TransactionWeekSortButton();
    this.typeBtn = new TransactionTypeSortButton();
    this.stockBtn = new TransactionStockSortButton();
    this.qtyBtn = new TransactionQuantitySortButton();
    this.priceBtn = new TransactionPriceSortButton();
    this.costRewardBtn = new TransactionCostRewardSortButton(); 

    ButtonType[] buttons = new ButtonType[] {
      this.weekBtn, this.typeBtn, this.stockBtn,
      this.qtyBtn, this.priceBtn, this.costRewardBtn
    };
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
