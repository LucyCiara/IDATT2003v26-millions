package edu.ntnu.idi.idatt2003.group18v26.view.components;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.StockMarketButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.PortfolioButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.TransactionHistoryButton;
import javafx.scene.layout.HBox;

public class GameHeaderNavigator extends HBox {
  private PortfolioButton portfolioBtn;
  private StockMarketButton marketBtn;
  private TransactionHistoryButton transHistBtn;

  public GameHeaderNavigator() {
    this.portfolioBtn = new PortfolioButton();
    this.marketBtn = new StockMarketButton();
    this.transHistBtn = new TransactionHistoryButton();
    getChildren().addAll(portfolioBtn, marketBtn, transHistBtn);
  }

  private void unselect() {
    this.portfolioBtn.setNotSelected();
    this.marketBtn.setNotSelected();
    this.transHistBtn.setNotSelected();
  }

  public void selectPortfolio() {
    this.unselect();
    this.portfolioBtn.setSelected();
  }

  public void selectStockMarket() {
    this.unselect();
    this.marketBtn.setSelected();
  }

  public void selectTransactionHistory() {
    this.unselect();
    this.transHistBtn.setSelected();
  }
}
