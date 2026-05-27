package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

import java.util.HashMap;

/**
 * Class for the share rows component in the application.
 */
public class ShareRows extends RowType {
  
  public ShareRows() {
    super();
  }

  /**
   * Adds a new share item to the rows.
   *
   * @param shareSymbol the symbol of the share
   * @param shareName the name of the share
   * @param shareQty the quantity of the share
   * @param purchasePrice the purchase price of the share
   * @param currentValue the current value of the share
   */
  public void addItem(String shareSymbol, String shareName, 
      String shareQty, String purchasePrice, String currentValue) {
    ShareItem item = new ShareItem(shareSymbol, shareName, shareQty, purchasePrice, currentValue);
    this.getContents().getChildren().add(item);
  }


}
