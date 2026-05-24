package edu.ntnu.idi.idatt2003.group18v26.view.components;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameHeader extends VBox {
  private GameHeaderInfoPanel infoPanel;
  private GameHeaderNavigator navigator;
  
  public GameHeader() {
    this.infoPanel = new GameHeaderInfoPanel();
    this.navigator = new GameHeaderNavigator();
    getChildren().addAll(this.infoPanel, this.navigator);
  }

  public void updateInfo() {
    this.infoPanel.updateDisplays();
  }

  public void selectPortfolio() {
    this.navigator.selectPortfolio();
  }

  public void selectStockMarket() {
    this.navigator.selectStockMarket();
  }

  public void selectTransactionHistory() {
    this.navigator.selectTransactionHistory();
  }
}
