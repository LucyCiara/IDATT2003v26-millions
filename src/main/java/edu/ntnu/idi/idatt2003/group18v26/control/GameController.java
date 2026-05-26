package edu.ntnu.idi.idatt2003.group18v26.control;

import edu.ntnu.idi.idatt2003.group18v26.model.filehandling.CsvStockReader;
import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Exchange;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Sale;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.filehandling.CsvStockReader;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Exchange;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Sale;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import javafx.scene.control.Button;

public class GameController implements GameObserver {
  private static GameController instance;

  private static NavigationController nav;

  private static final DecimalFormat dispDF = new DecimalFormat("0.00");
  private static final int roundingNum = 2;
  private static final RoundingMode roundingMode = RoundingMode.HALF_UP; 

  private boolean symbolToggleShare = false;
  private boolean companyNameToggleShare = false;
  private boolean quantityToggleShare = false;
  private boolean purchasePriceToggleShare = false;
  private boolean currentValueToggleShare = false;
  private String lastShareSort = "None";
  private boolean symbolToggleStock = false;
  private boolean companyNameToggleStock = false;
  private boolean purchasePriceToggleStock = false;
  private String lastStockSort = "None";
  private boolean weekToggleTransaction = false;
  private boolean typeToggleTransaction = false;
  private boolean stockToggleTransaction = false;
  private boolean quantityToggleTransaction = false;
  private boolean priceToggleTransaction = false;
  private boolean costRewardToggleTransaction = false;
  private String lastTransactionSort = "Week";
  
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
        
      } catch (URISyntaxException e) {
        nav.createWarningPopup("Unexpected exception. Might be caused by sp500.csv missing.");
        logger.error("Unexpected URI exception. Might be caused by sp500.csv missing.", e);
      }
    }
    nav.updateGamePage();
    nav.updateGainersAndLosers();
    this.fetchRefreshPortfolio();
    this.fetchRefreshStockMarket();
    this.fetchRefreshTransactionHistory();
    nav.showGamePage();
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
    return dispDF.format(this.player.getMoney().setScale(roundingNum, roundingMode));
  }

  public String getPlayerStatus() {
    return this.player.getStatus();
  }

  public void advanceWeek() {
    this.exchange.advance();
  }

  public String getNetWorth() {
    return this.player.getNetWorth().setScale(roundingNum, roundingMode).toString();
  }

  public String getPortfolioWorth() {
    return this.player.getPortfolio().getNetWorth().setScale(roundingNum, roundingMode).toString();
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
        share.quantity().setScale(roundingNum, roundingMode).toString(),
        "$" + share.purchasePrice().setScale(roundingNum, roundingMode).toString(),
        String.format(
          "$%s ($%s)",
          new SaleCalculator(share).calculateTotal().setScale(roundingNum, roundingMode).toString(),
          share.stock().getSalesPrice().setScale(roundingNum, roundingMode)
        )
      );
    }
  }

  private void fillStocks(List<Stock> stocks) {
    for (Stock stock : stocks) {
      nav.addStockToStockMarket(
        stock.getSymbol(),
        stock.getCompany(),
        "$" + stock.getSalesPrice().setScale(roundingNum, roundingMode).toString()
      );
    }
  }

  private void fillStocks(List<Stock> stocks, int limit) {
    for (int i = 0; i < limit && i < stocks.size(); i++) {
      Stock stock = stocks.get(i);
      nav.addStockToStockMarket(
        stock.getSymbol(),
        stock.getCompany(),
        "$" + stock.getSalesPrice().setScale(roundingNum, roundingMode).toString()
      );
    }
  }

  private void fillTransaction(List<Transaction> transactions) {
    logger.debug("Filling rows with transactions");
    for (Transaction transaction : transactions) {
      logger.debug("Filling transaction of share {}", transaction.getShare().stock().getSymbol());
      nav.addTransactionToTransactionHistory(
        Integer.toString(transaction.getWeek()),
        transaction.getClass().getSimpleName(),
        transaction.getShare().stock().getSymbol(),
        transaction.getShare().quantity().setScale(roundingNum, roundingMode).toString(),
        "$" + transaction.getShare().purchasePrice().setScale(roundingNum, roundingMode).toString(),
        "$" + transaction.getCalculator().calculateTotal().setScale(roundingNum, roundingMode).toString()
      );
    }
  }

  public void fetchPortfolio() {
    this.lastShareSort = "None";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getShares();
    this.fillShares(shares);
    logger.debug("Portfolio refreshed");
  }

  public void fetchStockMarket() {
    this.lastStockSort = "None";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStock();
    this.fillStocks(stocks, 10);
  }

  private List<Transaction> getTransactions() {
    List<Transaction> transactions = new ArrayList<Transaction>();
    for (int counter = this.player.getTransactionArchive().countDistinctWeeks(), i = 0; counter > 0; i++) {
      List<Transaction> batch = (this.player.getTransactionArchive().getTransactions(i));
      if (batch.size() > 0) {
        logger.debug("Found transactions {}", batch);
        transactions.addAll(batch);
        counter--;
      }
    }
    return transactions;
  }

  public void fetchWeekTransactionHistory(boolean toggle) {
    logger.debug("Fetching transactions by week");
    this.lastTransactionSort = "Week";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions();
    if (this.weekToggleTransaction) {
      transactions = transactions.reversed();
    }
    if (toggle) {
      this.weekToggleTransaction = !this.weekToggleTransaction;
    }
    this.fillTransaction(transactions);
  }

  public void fetchTypeTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Type";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = getTransactions();
    List<Transaction> transactionsPurchases
      = transactions.stream().filter(t -> t.getClass().getSimpleName().equals("Purchase")).toList();
    List<Transaction> transactionsSales
      = transactions.stream().filter(t -> t.getClass().getSimpleName().equals("Sale")).toList();
    transactions.clear();
    transactions.addAll(transactionsPurchases);
    transactions.addAll(transactionsSales);
    if (this.weekToggleTransaction) {
      transactions = transactions.reversed();
    }
    if (toggle) {
      this.weekToggleTransaction = !this.weekToggleTransaction;
    }
    this.fillTransaction(transactions);
  }

  public void fetchSymbolSortedPortfolio(boolean toggle) {
    this.lastShareSort = "Symbol";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesBySymbol();
    if (this.symbolToggleShare) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.symbolToggleShare = !this.symbolToggleShare;
    }
    this.fillShares(shares);
  }

  public void fetchSymbolSortedStockMarket(boolean toggle) {
    this.lastStockSort = "Symbol";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStockBySymbol();
    if (this.symbolToggleStock) {
      stocks = stocks.reversed();
    }
    if (toggle) {
      this.symbolToggleStock = !this.symbolToggleStock;
    }
    this.fillStocks(stocks);
  }

  public void fetchStockSortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Stock";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
      (t1, t2) -> t1.getShare().stock().getSymbol().compareTo(t2.getShare().stock().getSymbol())
    ).toList();
    if (this.stockToggleTransaction) {
      transactions = transactions.reversed();
    }
    if (toggle) {
      this.stockToggleTransaction = !this.stockToggleTransaction;
    }
    this.fillTransaction(transactions);
  }

  public void fetchCompanySortedPortfolio(boolean toggle) {
    this.lastShareSort = "Company";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByCompany();
    if (this.companyNameToggleShare) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.companyNameToggleShare = !this.companyNameToggleShare;
    }
    this.fillShares(shares);
  }

  public void fetchCompanySortedStockMarket(boolean toggle) {
    this.lastStockSort = "Company";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStockByCompany();
    if (this.companyNameToggleStock) {
      stocks = stocks.reversed();
    }
    if (toggle) {
      this.companyNameToggleStock = !this.companyNameToggleStock;
    }
    this.fillStocks(stocks);
  }

  public void fetchQuantitySortedPortfolio(boolean toggle) {
    this.lastShareSort = "Quantity";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByQuantity();
    if (this.quantityToggleShare) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.quantityToggleShare = !this.quantityToggleShare;
    }
    this.fillShares(shares);
  }

  public void fetchQuantitySortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Quantity";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
      (t1,t2) -> t1.getShare().quantity().compareTo(t2.getShare().quantity())
    ).toList();
    if (this.quantityToggleTransaction) {
      transactions = transactions.reversed();
    }
    if (toggle) {
      this.quantityToggleTransaction = !this.quantityToggleTransaction;
    }
    this.fillTransaction(transactions);
  }

  public void fetchPurchasePriceSortedPortfolio(boolean toggle) {
    this.lastShareSort = "Purchase Price";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByPurchasePrice();
    if (this.purchasePriceToggleShare) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.purchasePriceToggleShare = !this.purchasePriceToggleShare;
    }
    this.fillShares(shares);
  }

  public void fetchPurchasePriceSortedStockMarket(boolean toggle) {
    this.lastStockSort = "Purchase Price";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStockByPrice();
    if (this.purchasePriceToggleStock) {
      stocks = stocks.reversed();
    }
    if (toggle) {
      this.purchasePriceToggleStock = !this.purchasePriceToggleStock;
    }
    this.fillStocks(stocks);
  }

  public void fetchPriceSortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Price";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
      (t1,t2) -> t1.getShare().purchasePrice().compareTo(t2.getShare().purchasePrice())
    ).toList();
    if (this.priceToggleTransaction) {
      transactions = transactions.reversed();
    }
    if (toggle) {
      this.priceToggleTransaction = !this.priceToggleTransaction;
    }
    this.fillTransaction(transactions);
  }

  public void fetchCurrentValueSortedPortfolio(boolean toggle) {
    this.lastShareSort = "Current Value";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByCurrentValue();
    if (this.currentValueToggleShare) {
      shares = shares.reversed();
    }
    if (toggle) {
      this.currentValueToggleShare = !this.currentValueToggleShare;
    }
    this.fillShares(shares);
  }

  public void fetchCostRewardSortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "CostReward";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
      (t1,t2) -> t1.getCalculator().calculateTotal().compareTo(t2.getCalculator().calculateTotal())
    ).toList();
    if (this.costRewardToggleTransaction) {
      transactions = transactions.reversed();
    }
    if (toggle) {
      this.costRewardToggleTransaction = !this.costRewardToggleTransaction;
    }
    this.fillTransaction(transactions);
  }

  private void fetchRefreshPortfolio() {
    logger.debug("Refreshing portfolio");
    if (this.lastShareSort.equals("None")) {
      this.fetchPortfolio();
    } else if (this.lastShareSort.equals("Symbol")) {
      this.fetchSymbolSortedPortfolio(false);
    } else if (this.lastShareSort.equals("Company")) {
      this.fetchCompanySortedPortfolio(false);
    } else if (this.lastShareSort.equals("Quantity")) {
      this.fetchQuantitySortedPortfolio(false);
    } else if (this.lastShareSort.equals("Purchase Price")) {
      this.fetchPurchasePriceSortedPortfolio(false);
    } else if (this.lastShareSort.equals("Current Value")) {
      this.fetchCurrentValueSortedPortfolio(false);
    } else {
      logger.warn("Impossible state achieved");
      this.fetchPortfolio();
    }
  }

  private void fetchRefreshStockMarket() {
    logger.debug("Refreshing stock market");
    if (this.lastStockSort.equals("None")) {
      this.fetchStockMarket();
    } else if (this.lastStockSort.equals("Symbol")) {
      this.fetchSymbolSortedStockMarket(false);
    } else if (this.lastStockSort.equals("Company")) {
      this.fetchCompanySortedStockMarket(false);
    } else if (this.lastStockSort.equals("Purchase Price")) {
      this.fetchPurchasePriceSortedStockMarket(false);
    } else {
      logger.warn("Impossible state achieved");
      this.fetchPortfolio();
    }
  }

  private void fetchRefreshTransactionHistory() {
    logger.debug("Refreshing transaction history");
    if (this.lastTransactionSort.equals("Week")) {
      this.fetchWeekTransactionHistory(false);
    } else if (this.lastTransactionSort.equals("Type")) {
      this.fetchTypeTransactionHistory(false);
    } else if (this.lastTransactionSort.equals("Stock")) {
      this.fetchStockSortedTransactionHistory(false);
    } else if (this.lastTransactionSort.equals("Quantity")) {
      this.fetchQuantitySortedTransactionHistory(false);
    } else if (this.lastTransactionSort.equals("Price")) {
      this.fetchPriceSortedTransactionHistory(false);
    } else if (this.lastTransactionSort.equals("CostReward")) {
      this.fetchCostRewardSortedTransactionHistory(false);
    } else {
      logger.warn("Impossible state achieved");
      this.fetchWeekTransactionHistory(false);
    }
  }

  public void sellShare(String symbol) {
    this.exchange.sell(this.player.getPortfolio().getShare(symbol), this.player);
  }

  public void buyShare(String symbol, String quantity) {
    try {
      this.exchange.buy(symbol, new BigDecimal(quantity), this.player);
    } catch (Exception e) {
      nav.createErrorPopup("Quantity must be a valid number.");
    }
  }

  public void sellAllShares() {
    logger.debug("Selling all shares");
    for (Share share : new ArrayList<>(this.player.getPortfolio().getShares())) {
      this.exchange.sell(share, this.player);
    }
  }

  public void searchShare(String query) {
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().search(query);
    this.fillShares(shares);
  }

  public void searchStock(String query) {
    nav.clearStockMarketStocks();
    if (query.isBlank()) {
      this.fetchStockMarket();
    } else {
      List<Stock> stocks = this.exchange.findStocks(query);
      this.fillStocks(stocks);
    }
  }

  public void searchTransaction(String query) {
    nav.clearTransactionHistoryTransactions();
    List<Transaction> results = this.getTransactions().stream()
      .filter(t -> Integer.toString(t.getWeek()).contains(query)
          || t.getClass().getSimpleName().toLowerCase().contains(query.toLowerCase())
          || t.getShare().stock().getSymbol().toLowerCase().contains(query.toLowerCase())
          || t.getShare().quantity().toString().contains(query)
          || t.getShare().purchasePrice().toString().contains(query)
          || t.getCalculator().calculateTotal().toString().contains(query)
      ).toList();
    this.fillTransaction(results);
  }

  public String getGainerSymbol(int i) {
    return this.exchange.getGainers(i+1).get(i).getSymbol();
  }

  public String getGainerGain(int i) {
    return this.exchange.getGainers(i+1).get(i).getLatestPriceChange().setScale(roundingNum, roundingMode).toString();
  }

  public String getLoserSymbol(int i) {
    return this.exchange.getLosers(i+1).get(i).getSymbol();
  }

  public String getLoserGain(int i) {
    return this.exchange.getLosers(i+1).get(i).getLatestPriceChange().setScale(roundingNum, roundingMode).toString();
  }

  public void sellAllAndFinish() {
    this.sellAllShares();
    String[] lines = new String[] {
      "Player:",
      this.getPlayerName(),
      "",
      "Status:",
      this.getPlayerStatus(),
      "",
      "Total Money:",
      this.getMoney(),
      "",
      "Total Number of trades:",
      Integer.toString(this.getTransactions().size()),
      "",
      "Number of Weeks:",
      this.getWeek()
    };
    System.out.println("---------");
    for (String line : lines) {
      System.out.println(line);
    }
    System.out.println("---------");
    Platform.exit();
  }

  @Override
  public void onWeekAdvanced(int newWeek) {
    System.out.println("test");
    nav.updateGamePage();
    this.fetchRefreshPortfolio();
    this.fetchRefreshStockMarket();
    nav.updateGainersAndLosers();
  }

  @Override
  public void onStockPriceChanged(String symbol) {
    nav.updateGamePage();
  }

  @Override
  public void onPurchaseCompleted(String symbol, String quantity) {
    nav.updateGamePage();
    this.fetchRefreshTransactionHistory();
  }

  @Override
  public void onSaleCompleted(String symbol, String quantity) {
    nav.updateGamePage();
    this.fetchRefreshTransactionHistory();
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
