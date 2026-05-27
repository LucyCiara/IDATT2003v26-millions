package edu.ntnu.idi.idatt2003.group18v26.control;

import edu.ntnu.idi.idatt2003.group18v26.model.GameObserver;
import edu.ntnu.idi.idatt2003.group18v26.model.Player;
import edu.ntnu.idi.idatt2003.group18v26.model.filehandling.CsvStockReader;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Exchange;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Share;
import edu.ntnu.idi.idatt2003.group18v26.model.property.Stock;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.PurchaseCalculator;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.SaleCalculator;
import edu.ntnu.idi.idatt2003.group18v26.model.transaction.Transaction;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The GameController class is responsible for managing the game logic
 * and interactions between the model and view components.
 * It implements the GameObserver interface 
 * to receive updates from the model and update the view accordingly.
 */
public class GameController implements GameObserver {
  private static GameController instance;

  private static NavigationController nav;

  private static final int roundingNum = 2;
  private static final RoundingMode roundingMode = RoundingMode.HALF_UP;

  private static final int stockLimit = 20;

  private boolean symbolToggleShare = true;
  private boolean companyNameToggleShare = true;
  private boolean quantityToggleShare = true;
  private boolean purchasePriceToggleShare = true;
  private boolean currentValueToggleShare = true;
  private String lastShareSort = "None";
  private boolean symbolToggleStock = true;
  private boolean companyNameToggleStock = true;
  private boolean purchasePriceToggleStock = true;
  private String lastStockSort = "None";
  private boolean weekToggleTransaction = true;
  private boolean typeToggleTransaction = true;
  private boolean stockToggleTransaction = true;
  private boolean quantityToggleTransaction = true;
  private boolean priceToggleTransaction = true;
  private boolean costRewardToggleTransaction = true;
  private String lastTransactionSort = "Week";

  private static final Logger logger = LoggerFactory.getLogger(GameController.class);

  private CsvStockReader reader;
  private Exchange exchange;
  private Player player;

  private GameController() {
    this.reader = new CsvStockReader();
  }

  /**
   * Returns the singleton instance of the GameController.
   *
   * @return the GameController instance
   */
  public static GameController getInstance() {
    if (instance == null) {
      instance = new GameController();
      nav = NavigationController.getInstance();
    }
    return instance;
  }

  /**
   * Sets the exchange by reading stock data from the given file and creating a new Exchange object.
   *
   * @param file the file to read stock data from,
   *      if null, the exchange is set to null and the open file button is reset
   */
  private void setExchange(File file) {
    if (file != null) {
      nav.changeOpenFileButton(file.getName());
      try {
        this.exchange = new Exchange(
            file.getName(),
            this.reader.readStocks(file.toPath()));
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

  /**
   * Sets the exchange by prompting the user
   * to select a file through the navigation controller's file dialogue.
   */
  public void setExchangeFromFile() {
    File file = nav.getFileDialogue();
    setExchange(file);
  }

  /**
   * Creates a player using the name and starting money from the navigation controller.
   */
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

  /** 
   * Returns the name of the player.
   *
   * @return the player name
   */
  public String getPlayerName() {
    return this.player.getName();
  }

  /**
   * Starts the game by creating a player,
   * loading the exchange data from a file if not already loaded,
   * updating the game page, gainers and losers, portfolio, stock market, and transaction.
   */
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

  /**
   * Starts a new game by resetting the player and exchange, clearing the new game fields,
   * and showing the new game panel.
   */
  public void onNewGame() {
    this.player = null;
    this.setExchange(null);
    nav.clearNewGameFields();
    nav.showNewGamePanel();
  }

  /** 
   * Clears the currently loaded file.
   */
  public void onClearFile() {
    this.setExchange(null);
  }

  /**
   * Returns the money of the player.
   *
   * @return the player money
   */
  public String getMoney() {
    return this.player.getMoney().setScale(roundingNum, roundingMode).toString();
  }


  /** 
   * Returns the status of the player.
   *
   * @return the player status
   */
  public String getPlayerStatus() {
    return this.player.getStatus();
  }

  /** 
   * Advances the week in the exchange.
   */
  public void advanceWeek() {
    this.exchange.advance();
  }

  /**
   * Returns the net worth of the player.
   *
   * @return the net worth
   */
  public String getNetWorth() {
    return this.player.getNetWorth().setScale(roundingNum, roundingMode).toString();
  }

  /** 
   * Returns the worth of the portfolio.
   *
   * @return the portfolio worth
   */
  public String getPortfolioWorth() {
    return this.player.getPortfolio().getNetWorth().setScale(roundingNum, roundingMode).toString();
  }

  /**
   * Returns the current week number.
   *
   * @return the week number
   */
  public String getWeek() {
    return Integer.toString(this.exchange.getWeek());
  }

  /**
   * Returns the names of the shares in the portfolio.
   *
   * @return the list of share names
   */
  public List<String> getPortfolioShareNames() {
    List<String> outputShares = new ArrayList<>();
    for (Share share : this.player.getPortfolio().getShares()) {
      outputShares.add(share.stock().getSymbol() + " " + share.stock().getCompany());
    }
    return outputShares;
  }

  /**
   * Fills the portfolio with the given shares.
   *
   * @param shares the list of shares to display
   */
  private void fillShares(List<Share> shares) {
    for (Share share : shares) {
      nav.addShareToPortfolio(
          share.stock().getSymbol(),
          share.stock().getCompany(),
          share.quantity().setScale(roundingNum, roundingMode).toString(),
          "$" + share.purchasePrice().setScale(roundingNum, roundingMode).toString(),
          String.format(
              "$%s ($%s)",
              new SaleCalculator(share).calculateTotal()
              .setScale(roundingNum, roundingMode),
              share.stock().getSalesPrice().setScale(roundingNum, roundingMode)));
    }
  }

  /**
   * Fills the stock market with the given stocks.
   *
   * @param stocks the list of stocks to display
   */
  private void fillStocks(List<Stock> stocks) {
    for (Stock stock : stocks) {
      nav.addStockToStockMarket(
          stock.getSymbol(),
          stock.getCompany(),
          "$" + stock.getSalesPrice().setScale(roundingNum, roundingMode).toString());
    }
  }

  /**
   * Fills the stock market with the given stocks, limited by the specified number.
   *
   * @param stocks the list of stocks to display
   * @param limit the maximum number of stocks to display
   */
  private void fillStocks(List<Stock> stocks, int limit) {
    for (int i = 0; i < limit && i < stocks.size(); i++) {
      Stock stock = stocks.get(i);
      nav.addStockToStockMarket(
          stock.getSymbol(),
          stock.getCompany(),
          "$" + stock.getSalesPrice().setScale(roundingNum, roundingMode).toString());
    }
  }

  /**
   * Fills the transaction history with the given transactions.
   *
   * @param transactions the list of transactions to display
   */
  private void fillTransaction(List<Transaction> transactions) {
    logger.debug("Filling rows with transactions");
    for (Transaction transaction : transactions) {
      logger.debug("Filling transaction of share {}", transaction.getShare().stock().getSymbol());
      nav.addTransactionToTransactionHistory(
          Integer.toString(transaction.getWeek()),
          transaction.getClass().getSimpleName(),
          transaction.getShare().stock().getSymbol(),
          transaction.getShare().quantity().setScale(roundingNum, roundingMode).toString(),
          "$" + transaction.getShare().purchasePrice()
          .setScale(roundingNum, roundingMode).toString(),
          "$" + transaction.getCalculator().calculateTotal()
          .setScale(roundingNum, roundingMode).toString());
    }
  }

  /**
   * fetches the portfolio and fills the portfolio with the shares.
   */
  public void fetchPortfolio() {
    this.lastShareSort = "None";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getShares();
    this.fillShares(shares);
    logger.debug("Portfolio refreshed");
  }

  /**
   * fetches the stock market and fills the stock market with the stocks.
   */
  public void fetchStockMarket() {
    this.lastStockSort = "None";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStock();
    this.fillStocks(stocks, stockLimit);
  }

  private List<Transaction> getTransactions() {
    List<Transaction> transactions = new ArrayList<Transaction>();
    for (int counter = this.player.getTransactionArchive()
          .countDistinctWeeks(), i = 0; counter > 0; i++) {
      List<Transaction> batch = (this.player.getTransactionArchive().getTransactions(i));
      if (batch.size() > 0) {
        logger.debug("Found transactions {}", batch);
        transactions.addAll(batch);
        counter--;
      }
    }
    return transactions;
  }

  /**
   * fetches transactions sorted by week
   * and fills the transaction history with the sorted transactions.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
   *      If false, keeps the current sorting order.
   */
  public void fetchWeekTransactionHistory(boolean toggle) {
    logger.debug("Fetching transactions by week");
    this.lastTransactionSort = "Week";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions();
    if (toggle) {
      this.weekToggleTransaction = !this.weekToggleTransaction;
    }
    if (this.weekToggleTransaction) {
      transactions = transactions.reversed();
    }
    this.fillTransaction(transactions);
  }

  /**
   * fetches transactions sorted by type, with purchases first and sales last,
   * and fills the transaction history with the sorted transactions.
   *
   * @param toggle if true, toggles the sorting order between purchases first and sales first. 
   *         If false, keeps the current sorting order.
   */
  public void fetchTypeTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Type";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = getTransactions();
    List<Transaction> transactionsPurchases = transactions.stream()
        .filter(t -> t.getClass().getSimpleName().equals("Purchase")).toList();
    List<Transaction> transactionsSales = transactions.stream()
        .filter(t -> t.getClass().getSimpleName().equals("Sale"))
        .toList();
    transactions.clear();
    transactions.addAll(transactionsPurchases);
    transactions.addAll(transactionsSales);
    if (toggle) {
      this.typeToggleTransaction = !this.typeToggleTransaction;
    }
    if (this.weekToggleTransaction) {
      transactions = transactions.reversed();
    }
    this.fillTransaction(transactions);
  }

  /**
   * fetches shares sorted by symbol and fills the portfolio with the sorted shares.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
   *      If false, keeps the current sorting order.
   */
  public void fetchSymbolSortedPortfolio(boolean toggle) {
    this.lastShareSort = "Symbol";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesBySymbol();
    if (toggle) {
      this.symbolToggleShare = !this.symbolToggleShare;
    }
    if (this.symbolToggleShare) {
      shares = shares.reversed();
    }
    
    this.fillShares(shares);
  }

  /**
   * fetches stocks sorted by symbol and fills the stock market with the sorted stocks.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
   *      If false, keeps the current sorting order.
   */
  public void fetchSymbolSortedStockMarket(boolean toggle) {
    this.lastStockSort = "Symbol";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStockBySymbol();
    if (toggle) {
      this.symbolToggleStock = !this.symbolToggleStock;
    }
    if (this.symbolToggleStock) {
      stocks = stocks.reversed();
    }
    this.fillStocks(stocks, stockLimit);
  }

  /**
   * fetches transactions sorted by stock
   * and fills the transaction history with the sorted transactions.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
   *      If false, keeps the current sorting order.
   */
  public void fetchStockSortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Stock";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
        (t1, t2) -> t1.getShare().stock().getSymbol()
        .compareTo(t2.getShare().stock().getSymbol())).toList();
    if (toggle) {
      this.stockToggleTransaction = !this.stockToggleTransaction;
    }
    if (this.stockToggleTransaction) {
      transactions = transactions.reversed();
    }
    this.fillTransaction(transactions);
  }

  /**
   * fetches shares sorted by company name and fills the portfolio with the sorted shares.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
   *      If false, keeps the current sorting order.
   */
  public void fetchCompanySortedPortfolio(boolean toggle) {
    this.lastShareSort = "Company";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByCompany();
    if (toggle) {
      this.companyNameToggleShare = !this.companyNameToggleShare;
    }
    if (this.companyNameToggleShare) {
      shares = shares.reversed();
    }
    this.fillShares(shares);
  }

  /**
   * fetches stocks sorted by company name and fills the stock market with the sorted stocks.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
    *      If false, keeps the current sorting order.
   */
  public void fetchCompanySortedStockMarket(boolean toggle) {
    this.lastStockSort = "Company";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStockByCompany();
    if (toggle) {
      this.companyNameToggleStock = !this.companyNameToggleStock;
    }
    if (this.companyNameToggleStock) {
      stocks = stocks.reversed();
    }
    this.fillStocks(stocks, stockLimit);
  }

  /**
   * fetches shares sorted by quantity and fills the portfolio with the sorted shares.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
   *     If false, keeps the current sorting order.
   */
  public void fetchQuantitySortedPortfolio(boolean toggle) {
    this.lastShareSort = "Quantity";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByQuantity();
    if (toggle) {
      this.quantityToggleShare = !this.quantityToggleShare;
    }
    if (this.quantityToggleShare) {
      shares = shares.reversed();
    }
    this.fillShares(shares);
  }

  /**
   * fetches transactions sorted by quantity
   * and fills the transaction history with the sorted transactions.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending. 
   *      If false, keeps the current sorting order.
   */
  public void fetchQuantitySortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Quantity";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
        (t1, t2) -> t1.getShare().quantity().compareTo(t2.getShare().quantity())).toList();
    if (toggle) {
      this.quantityToggleTransaction = !this.quantityToggleTransaction;
    }
    if (this.quantityToggleTransaction) {
      transactions = transactions.reversed();
    }
    this.fillTransaction(transactions);
  }

  /**
   * fetches shares sorted by purchase price and fills the portfolio with the sorted shares.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending. 
   *      If false, keeps the current sorting order.
   */
  public void fetchPurchasePriceSortedPortfolio(boolean toggle) {
    this.lastShareSort = "Purchase Price";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByPurchasePrice();
    if (toggle) {
      this.purchasePriceToggleShare = !this.purchasePriceToggleShare;
    }
    if (this.purchasePriceToggleShare) {
      shares = shares.reversed();
    }
    this.fillShares(shares);
  }

  /**
   * fetches stocks sorted by purchase price and fills the stock market with the sorted stocks.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending. 
   *      If false, keeps the current sorting order.
   */
  public void fetchPurchasePriceSortedStockMarket(boolean toggle) {
    this.lastStockSort = "Purchase Price";
    nav.clearStockMarketStocks();
    List<Stock> stocks = this.exchange.getAllStockByPrice();
    if (toggle) {
      this.purchasePriceToggleStock = !this.purchasePriceToggleStock;
    }
    if (this.purchasePriceToggleStock) {
      stocks = stocks.reversed();
    }
    this.fillStocks(stocks, stockLimit);
  }

  /**
   * fetches transactions sorted by purchase price
   * and fills the transaction history with the sorted transactions.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending. 
   *          If false, keeps the current sorting order.
   */
  public void fetchPriceSortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "Price";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
        (t1, t2) -> t1.getShare().purchasePrice()
            .compareTo(t2.getShare().purchasePrice())).toList();
    if (toggle) {
      this.priceToggleTransaction = !this.priceToggleTransaction;
    }
    if (this.priceToggleTransaction) {
      transactions = transactions.reversed();
    }
    this.fillTransaction(transactions);
  }

  /**
   * fetches shares sorted by current value, calculated by the SaleCalculator,
   * and fills the portfolio with the sorted shares.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending.
   */
  public void fetchCurrentValueSortedPortfolio(boolean toggle) {
    this.lastShareSort = "Current Value";
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().getSharesByCurrentValue();
    if (toggle) {
      this.currentValueToggleShare = !this.currentValueToggleShare;
    }
    if (this.currentValueToggleShare) {
      shares = shares.reversed();
    }
    this.fillShares(shares);
  }

  /**
   * fetches transactions sorted by total cost/reward, calculated by the SaleCalculator, 
   * and fills the transaction history with the sorted transactions.
   *
   * @param toggle if true, toggles the sorting order between ascending and descending. 
   *      If false, keeps the current sorting order.
   */
  public void fetchCostRewardSortedTransactionHistory(boolean toggle) {
    this.lastTransactionSort = "CostReward";
    nav.clearTransactionHistoryTransactions();
    List<Transaction> transactions = this.getTransactions().stream().sorted(
        (t1, t2) -> t1.getCalculator().calculateTotal()
            .compareTo(t2.getCalculator().calculateTotal())).toList();
    if (toggle) {
      this.costRewardToggleTransaction = !this.costRewardToggleTransaction;
    }
    if (this.costRewardToggleTransaction) {
      transactions = transactions.reversed();
    }
    this.fillTransaction(transactions);
  }

  /**
   * fetches the portfolio and fills 
   * the portfolio with the shares, keeping the current sorting order.
   */
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

  /**
   * Refreshes the stock market based on the last sorting order.
   */
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

  /**
   * Refreshes the transaction history based on the last sorting order.
   */
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

  /**
   * Sells a share with the given stock symbol.
   *
   * @param symbol the stock symbol of the share to sell
   */
  public void sellShare(String symbol) {
    Share share = this.player.getPortfolio().getShare(symbol);
    String quantity = share.quantity().setScale(roundingNum, roundingMode).toString();


    String confirmationText
        = "Do you confirm the sale of " + symbol + "x"
        + quantity + "?";
    
    try {
      if (nav.createConfirmation("Confirm Sale:", confirmationText)) {
        Transaction sale = this.exchange.sell(share, this.player);
        String purchasePrice = share.purchasePrice()
            .setScale(roundingNum, roundingMode).toString();
        String valueAtSale = share.stock().getSalesPrice()
            .setScale(roundingNum, roundingMode).toString();
        String gross = sale.getCalculator().calculateGross()
            .setScale(roundingNum, roundingMode).toString();
        String comissions = sale.getCalculator().calculateCommission()
            .setScale(roundingNum, roundingMode).toString();
        String tax = sale.getCalculator().calculateTax()
            .setScale(roundingNum, roundingMode).toString();
        String totalSaleAmount = sale.getCalculator().calculateTotal()
            .setScale(roundingNum, roundingMode).toString();
        String rapportText = String.format(
            "Stock: %s\n"
            + "Quantity: %s\n"
            + "Value at purchase: %s\n"
            + "Value at sale: %s\n"
            + "Gross revenue: %s\n"
            + "Comissions: %s\n"
            + "Tax: %s\n"
            + "---\n"
            + "Total revenue: %s",
            symbol, quantity, purchasePrice, valueAtSale, gross, comissions,
            tax, totalSaleAmount
        );
        nav.createInfo("Transaction rapport:", rapportText);
      }
    } catch (Exception e) {
      // Do nothing.
    }
  }

  /**
   * Attempts to buy a share with the given stock symbol and quantity.
   * If the quantity is not a valid number, an error popup is shown.
   *
   * @param symbol the stock symbol of the share to buy
   * @param quantity the quantity of shares to buy, expected to be a valid number in string format
   */
  public void buyShare(String symbol, String quantity) {
    String confirmationText
        = "Do you confirm the purchase of " + symbol + "x" + quantity + "?";
          
    try {
      if (nav.createConfirmation("Confirm purchase:", confirmationText)) {
        Transaction purchase
            = this.exchange.buy(symbol, new BigDecimal(quantity), this.player);
        String value = this.exchange.getStock(symbol).getSalesPrice()
            .setScale(roundingNum, roundingMode).toString();
        String gross = purchase.getCalculator().calculateGross()
            .setScale(roundingNum, roundingMode).toString();
        String comissions = purchase.getCalculator().calculateCommission()
            .setScale(roundingNum, roundingMode).toString();
        String tax = purchase.getCalculator().calculateTax()
            .setScale(roundingNum, roundingMode).toString();
        String total = purchase.getCalculator().calculateTotal()
            .setScale(roundingNum, roundingMode).toString();
        String rapportText = String.format(
            "Stock: %s\n"
            + "Quantity: %s\n"
            + "Value: %s\n"
            + "Gross cost: %s\n"
            + "Comissions: %s\n"
            + "Tax: %s\n"
            + "---\n"
            + "Total cost: %s",
            symbol, quantity, value, gross, comissions, tax, total
        );
        nav.createInfo("Transaction rapport:", rapportText);
      }
    } catch (ArithmeticException e) {
      nav.createErrorPopup("Insufficient funds.");
    } catch (IllegalArgumentException e) {
      nav.createErrorPopup("Quantity must be a valid number.");
    }
  }

  /**
   * Sells all shares in the player's portfolio.
   */
  public void sellAllShares() {
    String confirmationText
        = "Are you sure you want to sell ALL your shares?\n"
        + "This cannot be undone";
    if (nav.createConfirmation("Confirm Selling all shares:", confirmationText)) {
      logger.debug("Selling all shares");
      for (Share share : new ArrayList<>(this.player.getPortfolio().getShares())) {
        this.exchange.sell(share, this.player);
      }
    }
  }

  /**
   * Searches shares in the player's portfolio by stock symbol 
   * or company name depending on the query.
   *
   * @param query the search query input by the user, 
   *        can be a part of the stock symbol or company name
   */
  public void searchShare(String query) {
    nav.clearPortfolioShares();
    List<Share> shares = this.player.getPortfolio().search(query);
    this.fillShares(shares);
  }

  /**
   * Searches stocks by symbol or company name depending on the query.
   *
   * @param query the search query input by the user, 
   *        can be a part of the stock symbol or company name
   */
  public void searchStock(String query) {
    nav.clearStockMarketStocks();
    if (query.isBlank()) {
      this.fetchStockMarket();
    } else {
      List<Stock> stocks = this.exchange.findStocks(query);
      this.fillStocks(stocks);
    }
  }

  /**
   * Searches transactions by week, type, stock symbol, quantity, 
   * purchase price or total cost/reward depending on the query.
   * The search query, can be a part of the stock symbol,
   * company name, or a number representing week, quantity, price or cost/reward
   *
   * @param query the search query input by the user, can be a part of the stock symbol,
   *              company name, or a number representing week, quantity, price or cost/reward
   */
  public void searchTransaction(String query) {
    nav.clearTransactionHistoryTransactions();
    List<Transaction> results = this.getTransactions().stream()
        .filter(t -> Integer.toString(t.getWeek()).contains(query)
            || t.getClass().getSimpleName().toLowerCase().contains(query.toLowerCase())
            || t.getShare().stock().getSymbol().toLowerCase().contains(query.toLowerCase())
            || t.getShare().quantity().toString().contains(query)
            || t.getShare().purchasePrice().toString().contains(query)
            || t.getCalculator().calculateTotal().toString().contains(query))
        .toList();
    this.fillTransaction(results);
  }

  /**
   * Returns the symbol of the gainer at the specified index.
   *
   * @param i the index of the gainer
   * @return the symbol of the gainer
   */
  public String getGainerSymbol(int i) {
    return this.exchange.getGainers(i + 1).get(i).getSymbol();
  }

  /**
   * Returns the gain of the gainer at the specified index.
   *
   * @param i the index of the gainer
   * @return the gain of the gainer
   */
  public String getGainerGain(int i) {
    return "$ " + this.exchange.getGainers(i + 1).get(i)
    .getLatestPriceChange().setScale(roundingNum, roundingMode).toString();
  }

  /**
   * Returns the symbol of the loser at the specified index.
   *
   * @param i the index of the loser
   * @return the symbol of the loser
   */
  public String getLoserSymbol(int i) {
    return this.exchange.getLosers(i + 1).get(i).getSymbol();
  }

  /**
   * Returns the gain of the loser at the specified index.
   *
   * @param i the index of the loser
   * @return the gain of the loser
   */
  public String getLoserGain(int i) {
    return "$ " + this.exchange.getLosers(i + 1).get(i)
    .getLatestPriceChange().setScale(roundingNum, roundingMode).toString();
  }

  /**
   * Sells all shares and prints final stats to console before exiting the program.
    * Used when player has lost or won the game.
   */
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

  public String getCompanyFromSymbol(String symbol) {
    return this.exchange.getStock(symbol).getCompany();
  }

  public List<BigDecimal> getPriceHistory(String symbol) {
    return this.exchange.getStock(symbol).getHistoricalPrices();
  }

  public BigDecimal getHighestPrice(String symbol) {
    return this.exchange.getStock(symbol).getHighestPrice();
  }

  public String getHighestPriceString(String symbol) {
    return this.getHighestPrice(symbol).setScale(roundingNum, roundingMode)
        .toString();
  }

  public BigDecimal getLowestPrice(String symbol) {
    return this.exchange.getStock(symbol).getLowestPrice();
  }

  public String getLowestPriceString(String symbol) {
    return this.getLowestPrice(symbol).setScale(roundingNum, roundingMode)
        .toString();
  }

  @Override
  public void onWeekAdvanced(int newWeek) {
    nav.updateGamePage();
    this.fetchRefreshPortfolio();
    this.fetchRefreshStockMarket();
    nav.updateGainersAndLosers();
    nav.updateStockContent();
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
