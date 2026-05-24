package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SellButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.NameDisplay;
import javafx.scene.layout.HBox;

public class ShareItem extends HBox {
  private DisplayType symDisp;
  private DisplayType nameDisp;
  private DisplayType qtyDisp;
  private DisplayType purchasePriceDisp;
  private DisplayType currentValueDisp;
  private ButtonType sellBtn;
  
  public ShareItem(String shareSymbol, String shareName, String shareQty, String purchasePrice, String currentValue) {
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
  }
}
