package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;


import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.BuyButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.StockQuantityField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


/**
 * Class for the stock item component in the application.
 */
public class StockItem extends GridPane {
  private DisplayType symDisp;
  private DisplayType nameDisp;
  private DisplayType purchasePriceDisp;
  private StockQuantityField stockQtyField;
  private BuyButton buyBtn;
  
  /**
   * Constructs a new StockItem.
   *
   * @param stockSymbol the symbol of the stock
   * @param stockName the name of the stock
   * @param purchasePrice the purchase price of the stock
   */
  public StockItem(String stockSymbol, String stockName, String purchasePrice) {
    super();
    getStyleClass().add("page");
    this.symDisp = new DisplayType();
    this.symDisp.setDisplayText(stockSymbol);
    this.nameDisp = new DisplayType();
    this.nameDisp.setDisplayText(stockName);
    this.purchasePriceDisp = new DisplayType();
    this.purchasePriceDisp.setDisplayText(purchasePrice);
    this.stockQtyField = new StockQuantityField();
    this.buyBtn = new BuyButton(stockSymbol, this.stockQtyField);
    this.buyBtn.setMaxWidth(Double.MAX_VALUE);
    this.buyBtn.setMaxHeight(Double.MAX_VALUE);

    DisplayType[] displays = new DisplayType[] {this.symDisp, 
      this.nameDisp, this.purchasePriceDisp};
    ColumnConstraints columnConstraint = new ColumnConstraints();
    columnConstraint.setPercentWidth(100);
    for (int i = 0; i < displays.length; i++) {
      add(displays[i], i, 0, 1, 1);
      getColumnConstraints().add(columnConstraint);
    }
    add(this.stockQtyField, displays.length, 0, 1, 1);
    getColumnConstraints().add(columnConstraint);
    add(this.buyBtn, displays.length + 1, 0, 1, 1);
    getColumnConstraints().add(columnConstraint);
    RowConstraints rowConstraint = new RowConstraints();
    rowConstraint.setPercentHeight(100);
    getRowConstraints().add(rowConstraint);
  }
}
