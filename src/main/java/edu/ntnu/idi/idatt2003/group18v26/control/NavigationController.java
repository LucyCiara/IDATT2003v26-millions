package edu.ntnu.idi.idatt2003.group18v26.control;

import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import edu.ntnu.idi.idatt2003.group18v26.view.components.fields.StockQuantityField;
import edu.ntnu.idi.idatt2003.group18v26.view.pages.GamePage;
import edu.ntnu.idi.idatt2003.group18v26.view.pages.MenuPanel;
import edu.ntnu.idi.idatt2003.group18v26.view.pages.NewGamePanel;
import edu.ntnu.idi.idatt2003.group18v26.view.pages.TitlePage;
import java.io.File;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The NavigationController class is responsible for managing the navigation and UI transitions
 * between different pages of the application. 
 */
public class NavigationController {
  private static NavigationController instance;

  private Stage stage;
  private StackPane root;
  private Scene scene;
  private TitlePage titlePage;
  private NewGamePanel newGamePanel;
  private FileChooser fileChooser;
  private GamePage gamePage;
  private MenuPanel menu;

  private static final Logger logger = LoggerFactory.getLogger(NavigationController.class);

  /**
   * Private constructor to enforce singleton pattern.
   */
  private NavigationController() {
    this.root = new StackPane();
    this.root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    this.titlePage = new TitlePage();
    this.newGamePanel = new NewGamePanel();
    this.fileChooser = new FileChooser();
    this.gamePage = new GamePage();
    this.menu = new MenuPanel();

    this.scene = new Scene(this.root, 1280, 720);
  }

  /**
   * Returns the singleton instance of the NavigationController.
   *
   * @return the NavigationController instance
   */
  public static NavigationController getInstance() {
    if (instance == null) {
      instance = new NavigationController();
    }
    return instance;
  }

  /**
   * Displays the titlePage.
   */
  public void showTitlePage() {
    this.root.getChildren().clear();
    this.root.getChildren().add(this.titlePage);
  }

  /**
   * Displays the newGamePanel on top of the current page.
   */
  public void showNewGamePanel() {
    this.root.getChildren().add(this.newGamePanel);
  }

  /**
   * Hides the last added page.
   */
  public void hideLastPage() {
    this.root.getChildren().remove(this.root.getChildren().size() - 1);
  }

  /**
   * Returns the root StackPane of the application.
   *
   * @return the root StackPane
   */
  public StackPane getRoot() {
    return this.root;
  }

  /**
   * Opens a file dialog for the user to select a file and returns the selected file.
   *
   * @return the selected File, or null if no file was selected
   */
  public File getFileDialogue() {
    File file = this.fileChooser.showOpenDialog(this.stage);
    return file;
  }

  /**
   * Sets the stage for the application and initializes the scene.
   *
   * @param stage the primary stage of the application
   * @throws IllegalArgumentException if the stage is null
   */
  public void setStage(Stage stage) {
    ParameterValidator.objectChecker(stage, "stage");
    this.stage = stage;
    this.stage.setTitle("Millions");
    this.stage.setScene(this.scene);
    this.stage.show();
  }

  /**
   * Changes the text of the open file button.
   *
   * @param fileName the name of the selected file
   */
  public void changeOpenFileButton(String fileName) {
    this.newGamePanel.changeOpenFileButton(fileName);
  }

  /**
   * Returns the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return this.newGamePanel.getPlayerName();
  }

  /**
   * Returns the starting money of the player.
   *
   * @return the player's starting money
   */
  public String getPlayerStartMoney() {
    return this.newGamePanel.getPlayerStartMoney();
  }

  /**
   * Creates a warning popup with the specified text.
   *
   * @param text the text to display in the popup
   */
  public void createWarningPopup(String text) {
    Alert warning = new Alert(AlertType.WARNING);
    warning.setContentText(text);
    warning.show();
  }

  /**
   * Creates an error popup with the specified text.
   *
   * @param text the text to display in the popup
   */
  public void createErrorPopup(String text) {
    Alert error = new Alert(AlertType.ERROR);
    error.setContentText(text);
    error.show();
  }

  /**
   * This method creates a confirmation popup.
   * 
   * @param title The title and header for the popup.
   * @param text The content-text of the popup.
   * @return A boolean based on whether the OK button was clicked or not.
   */
  public boolean createConfirmation(String title, String text) {
    Alert confirmation = new Alert(AlertType.CONFIRMATION);
    confirmation.setTitle(title);
    confirmation.setHeaderText(title);
    confirmation.setContentText(text);
    Optional<ButtonType> buttonType = confirmation.showAndWait();
    if (buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
      return true;
    }
    return false;
  }

  public void createInfo(String title, String text) {
    Alert info = new Alert(AlertType.INFORMATION);
    info.setTitle(title);
    info.setHeaderText(title);
    info.setContentText(text);
    info.show();
  }

  /**
   * Clears the fields in the new game panel.
   */
  public void clearNewGameFields() {
    this.newGamePanel.clearFields();
  }

  /**
   * Displays the game page.
   */
  public void showGamePage() {
    this.root.getChildren().clear();
    this.root.getChildren().add(this.gamePage);
  }

  /**
   * Selects the portfolio tab on the game page.
   */
  public void selectPortfolio() {
    this.gamePage.selectPortfolio();
  }

  /**
   * Selects the stock market tab on the game page.
   */
  public void selectStockMarket() {
    this.gamePage.selectStockMarket();
  }

  /**
   * Selects the transaction history tab on the game page.
   */
  public void selectTransactionHistory() {
    this.gamePage.selectTransactionHistory();
  }

  /**
   * Adds a share to the portfolio on the game page.
   *
   * @param shareSymbol the symbol of the share to add
   * @param shareName the name of the share to add
   * @param shareQty the quantity of the share to add
   * @param purchasePrice the purchase price of the share to add
   * @param currentValue the current value of the share to add
   */
  public void addShareToPortfolio(String shareSymbol, String shareName,
      String shareQty, String purchasePrice, String currentValue) {
    this.gamePage.addShare(shareSymbol, shareName, shareQty, purchasePrice, currentValue);
  }

  /**
   * Clears the shares in the portfolio on the game page.
   */
  public void clearPortfolioShares() {
    logger.debug("Clearing shares");
    this.gamePage.clearShares();
  }

  /**
   * Adds a stock to the stock market on the game page.
   *
   * @param stockSymbol the symbol of the stock to add
   * @param stockName the name of the stock to add
   * @param purchasePrice the purchase price of the stock to add
   */
  public void addStockToStockMarket(String stockSymbol, String stockName, String purchasePrice) {
    this.gamePage.addStock(stockSymbol, stockName, purchasePrice);
  }

  /**
   * Clears the stocks in the stock market on the game page.
   */
  public void clearStockMarketStocks() {
    this.gamePage.clearStock();
  }

  /**
   * Handles the buy action for a stock.
   *
   * @param symbol the symbol of the stock to buy
   * @param qtyField the field containing the quantity to buy
   */
  public void onBuy(String symbol, StockQuantityField qtyField) {
    GameController.getInstance().buyShare(symbol, qtyField.getText());
    qtyField.clear();
  }

  /**
   * Adds a transaction to the transaction history on the game page.
   *
   * @param week the week of the transaction
   * @param transactionType the type of the transaction
   * @param stock the stock involved in the transaction
   * @param quantity the quantity of the stock involved in the transaction
   * @param price the price of the stock involved in the transaction
   * @param costReward the cost or reward of the transaction
   */
  public void addTransactionToTransactionHistory(String week, String transactionType,
        String stock, String quantity, String price, String costReward) {
    this.gamePage.addTransaction(week, transactionType, stock, quantity, price, costReward);
  }

  /**
   * Clears the transactions in the transaction history on the game page.
   */
  public void clearTransactionHistoryTransactions() {
    this.gamePage.clearTransactions();
  }

  /**
   * Displays the menu.
   */
  public void showMenu() {
    this.root.getChildren().add(this.menu);
  }

  public void selectStock(String symbol) {
    this.gamePage.selectStock(symbol);
  }

  public GamePage getGamepage() {
    return this.gamePage;
  }


}
