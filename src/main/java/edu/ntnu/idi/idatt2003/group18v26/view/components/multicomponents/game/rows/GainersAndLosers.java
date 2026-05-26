package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.rows;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;
import edu.ntnu.idi.idatt2003.group18v26.view.components.LabelStandard;
import javafx.scene.layout.VBox;

public class GainersAndLosers extends VBox {
  private LabelStandard gainerText;
  private StockGainerLoserRow gainers;
  private LabelStandard loserText;
  private StockGainerLoserRow losers;
  
  public GainersAndLosers() {
    this.gainers = new StockGainerLoserRow();
    this.gainerText = new LabelStandard("This week's gainers:");
    this.losers = new StockGainerLoserRow();
    this.loserText = new LabelStandard("This week's losers:");
    getChildren().addAll(this.gainerText, this.gainers, this.loserText, this.losers);
  }

  public void update() {
    this.gainers.clear();
    this.losers.clear();
    GameController gameCont = GameController.getInstance();
    for (int i = 0; i < 3; i++) {
      this.gainers.addItem(gameCont.getGainerSymbol(i), gameCont.getGainerGain(i));
    }
    for (int i = 0; i < 3; i++) {
      this.losers.addItem(gameCont.getLoserSymbol(i), gameCont.getLoserGain(i));
    }
  }
}
