package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;


import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockNameSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockPurchasePriceSortButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockSymbolSortButton;
import java.util.Arrays;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * Class for the stock item sorter component in the application.
 */
public class StockItemSorter extends GridPane {
  private StockSymbolSortButton symSortBtn;
  private StockNameSortButton nameSortBtn;
  private StockPurchasePriceSortButton ppSortBtn;

  /**
   * Constructs a new StockItemSorter.
   */
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
