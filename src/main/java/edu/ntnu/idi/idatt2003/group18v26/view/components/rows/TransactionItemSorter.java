package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;


import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionCostRewardSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionPriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionQuantitySortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionStockSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionTypeSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionWeekSortButton;
import java.util.Arrays;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * Class for the transaction item sorter component in the application.
 */
public class TransactionItemSorter extends GridPane {
  private TransactionWeekSortButton weekBtn;
  private TransactionTypeSortButton typeBtn;
  private TransactionStockSortButton stockBtn;
  private TransactionQuantitySortButton qtyBtn;
  private TransactionPriceSortButton priceBtn;
  private TransactionCostRewardSortButton costRewardBtn;

  /**
   * Constructs a new TransactionItemSorter.
   */
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
