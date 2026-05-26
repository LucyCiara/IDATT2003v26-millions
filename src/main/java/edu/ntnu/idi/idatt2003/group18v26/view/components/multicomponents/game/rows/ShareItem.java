package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SellButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 * Class for the share item component in the application.
 */
public class ShareItem extends GridPane {
  private DisplayType symDisp;
  private DisplayType nameDisp;
  private DisplayType qtyDisp;
  private DisplayType purchasePriceDisp;
  private DisplayType currentValueDisp;
  private SellButton sellBtn;
  private BorderPane sellWrapper;

  /**
   * Constructs a new ShareItem with the given parameters.
   *
   * @param shareSymbol the symbol of the share
   * @param shareName the name of the share
   * @param shareQty the quantity of the share
   * @param purchasePrice the purchase price of the share
   * @param currentValue the current value of the share
   */
  public ShareItem(String shareSymbol, String shareName, 
      String shareQty, String purchasePrice, String currentValue) {
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
    this.sellBtn.setMaxWidth(Double.MAX_VALUE);
    this.sellBtn.setMaxHeight(Double.MAX_VALUE);
    this.sellBtn.setMinWidth(Region.USE_COMPUTED_SIZE);
    this.sellWrapper = new BorderPane();
    this.sellWrapper.setCenter(this.sellBtn);

    DisplayType[] displays = new DisplayType[] { this.symDisp, 
      this.nameDisp, this.qtyDisp, this.purchasePriceDisp,
      this.currentValueDisp };
    double percentWidth = 100.0 / 6;

    for (int i = 0; i < displays.length; i++) {
      ColumnConstraints cc = new ColumnConstraints();
      cc.setPercentWidth(percentWidth);
      cc.setFillWidth(true);
      add(displays[i], i, 0, 1, 1);
      getColumnConstraints().add(cc);
    }

    ColumnConstraints sellColConstraint = new ColumnConstraints();
    sellColConstraint.setPercentWidth(percentWidth);
    sellColConstraint.setFillWidth(true);
    add(this.sellWrapper, displays.length, 0, 1, 1);
    getColumnConstraints().add(sellColConstraint);
    RowConstraints rowConstraint = new RowConstraints();
    rowConstraint.setPercentHeight(100);
    getRowConstraints().add(rowConstraint);
  }
}
