package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.searchbars.StockMarketSearchBar;
import edu.ntnu.idi.idatt2003.group18v26.view.components.rows.GainersAndLosers;
import edu.ntnu.idi.idatt2003.group18v26.view.components.rows.StockItemSorter;
import edu.ntnu.idi.idatt2003.group18v26.view.components.rows.StockRows;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/**
 * Class for the stock market content component
 * which includes the stock area and gainers and losers.
 */
public class StockMarketContent extends GridPane {
  private VBox stockArea;
  private GainersAndLosers gainersAndLosers;
  private StockMarketSearchBar searchBar;
  private StockItemSorter stockSorter;
  private StockRows stockRows;
  
  /**
   * Constructs a new StockMarketContent.
   */
  public StockMarketContent() {
    super();
    this.stockArea = new VBox();
    this.gainersAndLosers = new GainersAndLosers();
    this.searchBar = new StockMarketSearchBar();
    this.stockSorter = new StockItemSorter();
    this.stockRows = new StockRows();
    this.stockArea.getChildren().addAll(this.searchBar, this.stockSorter, this.stockRows);
    add(this.stockArea, 0, 0, 1, 1);
    add(this.gainersAndLosers, 1, 0, 1, 1);
    ColumnConstraints left = new ColumnConstraints();
    left.setPercentWidth(75);
    ColumnConstraints right = new ColumnConstraints();
    right.setPercentWidth(25);
    getColumnConstraints().addAll(left, right);
    RowConstraints all = new RowConstraints();
    all.setPercentHeight(100);
    getRowConstraints().add(all);
  }

  public void addStock(String stockSymbol, String stockName, String purchasePrice) {
    this.stockRows.addItem(stockSymbol, stockName, purchasePrice);
  }

  public void clearStocks() {
    this.stockRows.clear();
  }

  public void updateGainersAndLosers() {
    this.gainersAndLosers.update();
  }
}
