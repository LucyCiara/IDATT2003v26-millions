package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;
import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.GameBottom;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.GameHeader;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.PortFolioContent;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.StockContent;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.StockMarketContent;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.TransactionHistoryContent;
import javafx.scene.layout.BorderPane;

public class GamePage extends BorderPane implements GameObserver {

  private GameHeader header;
  private GameBottom bottom;
  private PortFolioContent portfolioContent;
  private StockMarketContent stockMarketContent;
  private TransactionHistoryContent transHistContent;
  private StockContent stockContent;

  public GamePage() {
    getStyleClass().add("page");
    this.header = new GameHeader();
    this.bottom = new GameBottom();
    this.portfolioContent = new PortFolioContent();
    this.stockMarketContent = new StockMarketContent();
    this.transHistContent = new TransactionHistoryContent();
    setTop(this.header);
    setBottom(this.bottom);
    this.selectPortfolio();
  }

  public void selectPortfolio() {
    this.header.selectPortfolio();
    setCenter(this.portfolioContent);
  }

  public void selectStockMarket() {
    this.header.selectStockMarket();
    setCenter(this.stockMarketContent);
  }

  public void selectTransactionHistory() {
    this.header.selectTransactionHistory();
    setCenter(this.transHistContent);
  }

  public void addShare(String shareSymbol, String shareName, String shareQty, String purchasePrice, String currentValue) {
    this.portfolioContent.addShare(shareSymbol, shareName, shareQty, purchasePrice, currentValue);
  }

  public void clearShares() {
    this.portfolioContent.clearShares();
  }

  public void addStock(String stockSymbol, String stockName, String purchasePrice) {
    this.stockMarketContent.addStock(stockSymbol, stockName, purchasePrice);
  }

  public void clearStock() {
    this.stockMarketContent.clearStocks();
  }

  public void addTransaction(String week, String transactionType, String stock, String quantity, String price, String costReward) {
    this.transHistContent.addTransaction(week, transactionType, stock, quantity, price, costReward);
  }

  public void clearTransactions() {
    this.transHistContent.clearTransactions();
  }

  public void selectStock(String symbol) {
    this.stockContent = new StockContent(symbol);
    setCenter(this.stockContent);
  }

  public boolean stockContentIsInitialized() {
    return this.stockContent != null ? true : false;
  }

  @Override
  public void onWeekAdvanced(int newWeek) {
    this.bottom.updateWeek(newWeek);
    this.header.updateStatus();
    this.stockMarketContent.updateGainersAndLosers();
  }

  @Override
  public void onStockPriceChanged(String symbol) {
    this.stockMarketContent.updateStocks(symbol);
    GameController.getInstance().fetchRefreshPortfolio();
    if (this.stockContent != null) {
      this.stockContent.update(symbol);
    }
    this.bottom.updateNetWorth();
  }

  @Override
  public void onPurchaseCompleted(String symbol, String quantity) {
    GameController.getInstance().fetchRefreshTransactionHistory();
  }

  @Override
  public void onSaleCompleted(String symbol, String quantity) {
    GameController.getInstance().fetchRefreshTransactionHistory();
  }

  @Override
  public void onMoneyChanged(String newBalance) {
    this.header.updateMoney();
    this.bottom.updateNetWorth();
  }

  @Override
  public void onPortfolioChanged() {
    GameController.getInstance().fetchRefreshPortfolio();
  }

 
  public void initGamePage(String playerName) {
    this.header.updateName(playerName);
    this.header.updateMoney();
    this.header.updateStatus();
    this.bottom.updateNetWorth();
    this.bottom.updateWeek(0);
  }
}
