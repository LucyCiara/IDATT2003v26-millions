package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game;

import javafx.scene.layout.VBox;

/**
 * Class for the game header component, which includes the info panel and navigator.
 */
public class GameHeader extends VBox {
  private GameHeaderInfoPanel infoPanel;
  private GameHeaderNavigator navigator;
  
  /**
   * Constructs a new GameHeader.
   */
  public GameHeader() {
    this.infoPanel = new GameHeaderInfoPanel();
    this.navigator = new GameHeaderNavigator();
    getChildren().addAll(this.infoPanel, this.navigator);
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

  public void updateName(String name) {
    this.infoPanel.updateName(name);
  }

  public void updateMoney() {
    this.infoPanel.updateMoney();
  }

  public void updateStatus() {
    this.infoPanel.updateStatus();
  }
}
