package edu.ntnu.idi.idatt2003.group18v26.view.components.rows;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SellButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.NameDisplay;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;

public class ShareItem extends GridPane {
  private DisplayType symDisp;
  private DisplayType nameDisp;
  private DisplayType qtyDisp;
  private DisplayType purchasePriceDisp;
  private DisplayType currentValueDisp;
  private SellButton sellBtn;
  private BorderPane sellWrapper;
  
  public ShareItem(String shareSymbol, String shareName, String shareQty, String purchasePrice, String currentValue) {
    super();
    getStyleClass().add("page");
    this.symDisp = new DisplayType();
    this.symDisp.setDisplayText(shareSymbol);
    this.nameDisp = new DisplayType();
    this.nameDisp.setDisplayText(shareName);
    this.qtyDisp = new DisplayType();
    this.qtyDisp.setDisplayText(shareQty);
    this.purchasePriceDisp = new DisplayType();
    this.purchasePriceDisp.setDisplayText(purchasePrice);
    this.currentValueDisp = new DisplayType();
    this.currentValueDisp.setDisplayText(currentValue);
    this.sellBtn = new SellButton(shareSymbol);
    this.sellBtn.maxWidth(Double.MAX_VALUE);
    this.sellBtn.maxHeight(Double.MAX_VALUE);
    this.sellBtn.setMinWidth(Region.USE_COMPUTED_SIZE);
    this.sellWrapper = new BorderPane();
    this.sellWrapper.setCenter(this.sellBtn);

    DisplayType[] displays = new DisplayType[] {this.symDisp, this.nameDisp, this.qtyDisp, this.purchasePriceDisp, this.currentValueDisp};
    ColumnConstraints columnConstraint = new ColumnConstraints();
    columnConstraint.setPercentWidth(100);
    for (int i = 0; i < displays.length; i++) {
      add(displays[i], i, 0, 1, 1);
      getColumnConstraints().add(columnConstraint);
    }
    add(this.sellWrapper, displays.length, 0, 1, 1);
    getColumnConstraints().add(columnConstraint);
    RowConstraints rowConstraint = new RowConstraints();
    rowConstraint.setPercentHeight(100);
    getRowConstraints().add(rowConstraint);
    
  }
}
