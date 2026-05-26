package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.BuyButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SellButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockInfoButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.NameDisplay;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.StockQuantityField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;

public class StockItem extends GridPane {
  private StockInfoButton symbBtn;
  private DisplayType nameDisp;
  private DisplayType purchasePriceDisp;
  private StockQuantityField stockQtyField;
  private BuyButton buyBtn;
  
  public StockItem(String stockSymbol, String stockName, String purchasePrice) {
    super();
    getStyleClass().add("page");
    this.symbBtn = new StockInfoButton(stockSymbol);
    this.nameDisp = new DisplayType();
    this.nameDisp.setDisplayText(stockName);
    this.purchasePriceDisp = new DisplayType();
    this.purchasePriceDisp.setDisplayText(purchasePrice);
    this.stockQtyField = new StockQuantityField();
    this.buyBtn = new BuyButton(stockSymbol, this.stockQtyField);
    this.buyBtn.setMaxWidth(Double.MAX_VALUE);
    this.buyBtn.setMaxHeight(Double.MAX_VALUE);

    ColumnConstraints cc = new ColumnConstraints();
    cc.setPercentWidth(100);
    add(symbBtn, 0, 0, 1, 1);
    getColumnConstraints().add(cc);
    DisplayType[] displays = new DisplayType[] {this.nameDisp, this.purchasePriceDisp};
    for (int i = 0; i < displays.length; i++) {
      add(displays[i], i+1, 0, 1, 1);
      getColumnConstraints().add(cc);
    }
    add (this.stockQtyField, displays.length, 0, 1, 1);
    getColumnConstraints().add(cc);
    add(this.buyBtn, displays.length+1, 0, 1, 1);
    getColumnConstraints().add(cc);
    RowConstraints rowConstraint = new RowConstraints();
    rowConstraint.setPercentHeight(100);
    getRowConstraints().add(rowConstraint);
  }
}
