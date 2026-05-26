package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.Arrays;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareCurrentValueSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareNameSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SharePurchasePriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareQuantitySortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareSellAllButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ShareSymbolSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockNameSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockPurchasePriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockSymbolSortButton;
import javafx.geometry.HPos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class StockItemSorter extends GridPane {
  private StockSymbolSortButton symSortBtn;
  private StockNameSortButton nameSortBtn;
  private StockPurchasePriceSortButton ppSortBtn;

  public StockItemSorter() {
    this.symSortBtn = new StockSymbolSortButton();
    this.nameSortBtn = new StockNameSortButton();
    this.ppSortBtn = new StockPurchasePriceSortButton();

    ButtonType[] buttons = new ButtonType[] {this.symSortBtn, this.nameSortBtn, this.ppSortBtn};
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
