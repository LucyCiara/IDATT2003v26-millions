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

public class TransactionItem extends GridPane {
  private DisplayType weekDisp;
  private DisplayType transTypeDisp;
  private DisplayType stockDisp;
  private DisplayType qtyDisp;
  private DisplayType priceDisp;
  private DisplayType costRewardDisp;

  public TransactionItem(String week, String transactionType, String stock, String quantity, String price, String costReward) {
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
