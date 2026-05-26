package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.GameBottom;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.GameHeader;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.PortFolioContent;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.StockMarketContent;
import javafx.scene.layout.BorderPane;

public class GamePage extends BorderPane {
  private GameHeader header;
  private GameBottom bottom;
  private PortFolioContent portfolioContent;
  private StockMarketContent stockMarketContent;

  public GamePage() {
    getStyleClass().add("page");
    this.header = new GameHeader();
    this.bottom = new GameBottom();
    this.portfolioContent = new PortFolioContent();
    this.stockMarketContent = new StockMarketContent();
    setTop(this.header);
    setBottom(this.bottom);
    this.selectPortfolio();
  }

  public void updateInfo() {
    this.header.updateInfo();
    this.bottom.updateInfo();
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

  public void updateGainersAndLosers() {
    this.stockMarketContent.updateGainersAndLosers();
  }
}
