package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.GameBottom;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.GameHeader;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.game.PortFolioContent;
import javafx.scene.layout.BorderPane;

public class GamePage extends BorderPane {
  private GameHeader header;
  private GameBottom bottom;
  private PortFolioContent portfolioContent;

  public GamePage() {
    getStyleClass().add("page");
    this.header = new GameHeader();
    this.bottom = new GameBottom();
    this.portfolioContent = new PortFolioContent();
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
}
