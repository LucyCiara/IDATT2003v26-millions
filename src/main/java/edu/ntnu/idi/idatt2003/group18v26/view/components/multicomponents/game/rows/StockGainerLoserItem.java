package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

import edu.ntnu.idi.idatt2003.group18v26.view.components.displays.DisplayType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class StockGainerLoserItem extends GridPane {
  private DisplayType symDisp;
  private DisplayType gainDisp;
  
  public StockGainerLoserItem(String stockSymbol, String stockGain) {
    super();
    getStyleClass().add("page");
    this.symDisp = new DisplayType();
    this.symDisp.setDisplayText(stockSymbol);
    this.gainDisp = new DisplayType();
    this.gainDisp.setDisplayText(stockGain);

    DisplayType[] displays = new DisplayType[] {this.symDisp, this.gainDisp};
    ColumnConstraints columnConstraint = new ColumnConstraints();
    columnConstraint.setPercentWidth(100);
    for (int i = 0; i < displays.length; i++) {
      add(displays[i], i, 0, 1, 1);
      getColumnConstraints().add(columnConstraint);
    }
    RowConstraints rowConstraint = new RowConstraints();
    rowConstraint.setPercentHeight(100);
    getRowConstraints().add(rowConstraint);
  }
}