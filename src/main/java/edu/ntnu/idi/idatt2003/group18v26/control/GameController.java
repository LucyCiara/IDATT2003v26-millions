package edu.ntnu.idi.idatt2003.group18v26.control;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;

import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.filehandling.CsvStockReader;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Exchange;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Sale;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import javafx.scene.control.Button;

public class GameController implements GameObserver {
  private static GameController instance;

  private static NavigationController nav;

  private boolean symbolToggle = false;
  private boolean companyNameToggle = false;
  private boolean quantityToggle = false;
  private boolean purchasePriceToggle = false;
  private boolean currentValueToggle = false;
  private String lastSort = "None";
  
  private static final Logger logger
      = LoggerFactory.getLogger(GameController.class);

  private CsvStockReader reader;
  private Exchange exchange;
  private Player player;

  private GameController() {
    this.reader = new CsvStockReader();
  }

  public static GameController getInstance() {
    if (instance == null) {
      instance = new GameController();
      nav = NavigationController.getInstance();
    }
    return instance;
  }

  private void setExchange(File file) {
    if (file != null) {
      nav.changeOpenFileButton(file.getName());
      try {
        this.exchange = new Exchange(
          file.getName(),
          this.reader.readStocks(file.toPath())
        );
        this.exchange.addObserver(this);
      } catch (IOException e) {
        nav.createErrorPopup("File must be a readable CSV.");
        this.setExchange(null);
      }
    } else {
      nav.changeOpenFileButton(null);
      this.exchange = null;
    }
  }

  public void setExchangeFromFile() {
    File file = nav.getFileDialogue();
    setExchange(file);
  }

  private void createPlayer() {
    String playerName = nav.getPlayerName();
    String playerStartMoney = nav.getPlayerStartMoney();
    if (playerName == null || playerStartMoney == null) {
      nav.createWarningPopup("Enter player name and starting money in fields to proceed.");
    } else {
      BigDecimal playerStartMoneyBigDec;
      try {
        playerStartMoneyBigDec = new BigDecimal(playerStartMoney);
        if (playerStartMoneyBigDec.compareTo(BigDecimal.ZERO) <= 0) {
          nav.createWarningPopup("Starting money must be more than 0.");
        } else {
          this.player = new Player(playerName, playerStartMoneyBigDec);
          this.player.addObserver(this);
          this.player.getPortfolio().addObserver(this);
          System.out.println(String.format("%s, %5f", player.getName(), player.getMoney()));
        }
      } catch (Exception e) {
        nav.createWarningPopup("Starting money must be a valid decimal number.");
      }
    }
  }

  public String getPlayerName() {
    return this.player.getName();
  }

  public void onGameStart() {
    this.createPlayer();
    if (this.exchange == null) {
      URL url = GameController.class.getResource("/sp500.csv");
      File file;
      try {
        file = new File(url.toURI());
        this.setExchange(file);
        nav.updateGamePage();
        nav.showGamePage();
        for (int i = 0; i < 20; i++) {
          this.exchange.buy(this.exchange.getGainers(20).get(i).getSymbol(), new BigDecimal("1"), this.player); // TODO: remove these lines
        }
      } catch (URISyntaxException e) {
        nav.createWarningPopup("Unexpected exception. Might be caused by sp500.csv missing.");
        logger.error("Unexpected URI exception. Might be caused by sp500.csv missing.", e);
      }
    }
  }

  public void onNewGame() {
    this.player = null;
    this.setExchange(null);
    nav.clearNewGameFields();
    nav.showNewGamePanel();
  }

  public void onClearFile() {
    this.setExchange(null);
  }

  public String getMoney() {
    return this.player.getMoney().toString();
  }

  public String getPlayerStatus() {
    return this.player.getStatus();
  }

  public void advanceWeek() {
    this.exchange.advance();
  }

  public String getNetWorth() {
    return this.player.getNetWorth().toString();
  }

  public String getPortfolioWorth() {
    return this.player.getPortfolio().getNetWorth().toString();
  }

  public String getWeek() {
    return Integer.toString(this.exchange.getWeek());
  }

  public List<String> getPortfolioShareNames() {
    List<String> outputShares = new ArrayList<>();
    for (Share share : this.player.getPortfolio().getShares()) {
      outputShares.add(share.stock().getSymbol() + " " + share.stock().getCompany());
    }
    return outputShares;
  }

  private void fillShares(List<Share> shares) {
    for (Share share : shares) {
      nav.addShareToPortfolio(
        share.stock().getSymbol(),
        share.stock().getCompany(),
        share.quantity().toString(),
        share.purchasePrice().toString(),
        new SaleCalculator(share).calculateTotal().toString()
      );
    }
  }

  public void fetchPortfolio() {
    this.lastSort = "None";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getShares();
    this.fillShares(shares);
    logger.debug("Portfolio refreshed");
  }

  private List<Share> getSharesBySymbol() {
    return this.player.getPortfolio().getShares().stream().sorted((s1,s2) -> s1.stock().getSymbol().compareTo(s2.stock().getSymbol())).toList();
  }

  private List<Share> getSharesByCompany() {
    return this.player.getPortfolio().getShares().stream().sorted((s1,s2) -> s1.stock().getCompany().compareTo(s2.stock().getCompany())).toList();
  }

  private List<Share> getSharesByQuantity() {
    return this.player.getPortfolio().getShares().stream().sorted((s1,s2) -> s1.quantity().compareTo(s2.quantity())).toList();
  }

  private List<Share> getSharesByPurchasePrice() {
    return this.player.getPortfolio().getShares().stream().sorted((s1,s2) -> s1.purchasePrice().compareTo(s2.purchasePrice())).toList();
  }

  private List<Share> getSharesByCurrentValue() {
    return this.player.getPortfolio().getShares().stream().sorted((s1,s2) -> new SaleCalculator(s1).calculateTotal().compareTo(new SaleCalculator(s2).calculateTotal())).toList();
  }

  public void fetchSymbolSortedPortfolio(boolean toggle) {
    this.lastSort = "Symbol";
    nav.clearPortfolioShares();
    List<Share> shares = this.getSharesBySymbol();
    if (this.symbolToggle) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.symbolToggle = !this.symbolToggle;
    }
    this.fillShares(shares);
  }

  public void fetchCompanySortedPortfolio(boolean toggle) {
    this.lastSort = "Company";
    nav.clearPortfolioShares();
    List<Share> shares = this.getSharesByCompany();
    if (this.companyNameToggle) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.companyNameToggle = !this.companyNameToggle;
    }
    this.fillShares(shares);
  }

  public void fetchQuantitySortedPortfolio(boolean toggle) {
    this.lastSort = "Quantity";
    nav.clearPortfolioShares();
    List<Share> shares = this.getSharesByQuantity();
    if (this.quantityToggle) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.quantityToggle = !this.quantityToggle;
    }
    this.fillShares(shares);
  }

  public void fetchPurchasePriceSortedPortfolio(boolean toggle) {
    this.lastSort = "Purchase Price";
    nav.clearPortfolioShares();
    List<Share> shares = this.getSharesByPurchasePrice();
    if (this.purchasePriceToggle) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.purchasePriceToggle = !this.purchasePriceToggle;
    }
    this.fillShares(shares);
  }

  public void fetchCurrentValueSortedPortfolio(boolean toggle) {
    this.lastSort = "Current Value";
    nav.clearPortfolioShares();
    List<Share> shares = this.getSharesByCurrentValue();
    if (this.currentValueToggle) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.currentValueToggle = !this.currentValueToggle;
    }
    this.fillShares(shares);
  }

  private void fetchRefreshPortfolio() {
    logger.debug("Refreshing portfolio");
    if (this.lastSort.equals("None")) {
      this.fetchPortfolio();
    } else if (this.lastSort.equals("Company")) {
      this.fetchCompanySortedPortfolio(false);
    } else if (this.lastSort.equals("Quantity")) {
      this.fetchQuantitySortedPortfolio(false);
    } else if (this.lastSort.equals("Purchase Price")) {
      this.fetchPurchasePriceSortedPortfolio(false);
    } else if (this.lastSort.equals("Current Value")) {
      this.fetchCurrentValueSortedPortfolio(false);
    } else {
      logger.warn("Impossible state achieved");
      this.fetchPortfolio();
    }
  }

  public void sellShare(String symbol) {
    this.exchange.sell(this.player.getPortfolio().getShare(symbol), this.player);
  }

  public void sellAllShares() {
    logger.debug("Selling all shares");
    for (Share share : new ArrayList<>(this.player.getPortfolio().getShares())) {
      this.exchange.sell(share, this.player);
    }
  }

  public void search(String query) {
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().search(query);
    this.fillShares(shares);
  }

  @Override
  public void onWeekAdvanced(int newWeek) {
    System.out.println("test");
    nav.updateGamePage();
    this.fetchRefreshPortfolio();
  }

  @Override
  public void onStockPriceChanged(String symbol) {
    nav.updateGamePage();
  }

  @Override
  public void onPurchaseCompleted(String symbol, String quantity) {
    nav.updateGamePage();
  }

  @Override
  public void onSaleCompleted(String symbol, String quantity) {
    nav.updateGamePage();
  }

  @Override
  public void onMoneyChanged(String newBalance) {
    nav.updateGamePage();
  }

  @Override
  public void onPortfolioChanged() {
    logger.debug("Was notified by portfolio change");
    nav.updateGamePage();
    this.fetchRefreshPortfolio();
  }
}
