package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Class for the transaction item component in the application.
 */
public class TransactionItem extends GridPane {
  private DisplayType weekDisp;
  private DisplayType transTypeDisp;
  private DisplayType stockDisp;
  private DisplayType qtyDisp;
  private DisplayType priceDisp;
  private DisplayType costRewardDisp;

  /**
   * Constructs a new TransactionItem.
   *
   * @param week the week of the transaction
   * @param transactionType the type of the transaction
   * @param stock the stock involved in the transaction
   * @param quantity the quantity of the stock
   * @param price the price of the stock
   * @param costReward the cost or reward associated with the transaction
   */
  public TransactionItem(String week, String transactionType,
      String stock, String quantity, String price, String costReward) {
    super();
    getStyleClass().add("page");
    this.weekDisp = new DisplayType();
    this.weekDisp.setDisplayText(week);
    this.transTypeDisp = new DisplayType();
    this.transTypeDisp.setDisplayText(transactionType);
    this.stockDisp = new DisplayType();
    this.stockDisp.setDisplayText(stock);
    this.qtyDisp = new DisplayType();
    this.qtyDisp.setDisplayText(quantity);
    this.priceDisp = new DisplayType();
    this.priceDisp.setDisplayText(price);
    this.costRewardDisp = new DisplayType();
    this.costRewardDisp.setDisplayText(costReward);

    DisplayType[] displays = new DisplayType[] {
      this.weekDisp, this.transTypeDisp, this.stockDisp,
      this.qtyDisp, this.priceDisp, this.costRewardDisp
    };

    for (int i = 0; i < displays.length; i++) {
      ColumnConstraints cc = new ColumnConstraints();
      cc.setPercentWidth(100);
      cc.setFillWidth(true);
      add(displays[i], i, 0, 1, 1);
      getColumnConstraints().add(cc);
    }

    RowConstraints all = new RowConstraints();
    all.setPercentHeight(100);
    getRowConstraints().add(all);
  }

}
