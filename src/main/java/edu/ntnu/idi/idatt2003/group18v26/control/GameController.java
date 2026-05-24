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
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import javafx.scene.control.Button;

public class GameController implements GameObserver {
  private static GameController instance;

  private static NavigationController nav;
 
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

  @Override
  public void onWeekAdvanced(int newWeek) {
    System.out.println("test");
    nav.updateGamePage();
  }

  @Override
  public void onStockPriceChanged(String symbol) {
    nav.updateGamePage();
  }

  @Override
  public void onPurchaseCompleted(String symbol, String quantity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onPurchaseCompleted'");
  }

  @Override
  public void onSaleCompleted(String symbol, String quantity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onSaleCompleted'");
  }

  @Override
  public void onMoneyChanged(String newBalance) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onMoneyChanged'");
  }

  @Override
  public void onPortfolioChanged() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onPortfolioChanged'");
  }
}
