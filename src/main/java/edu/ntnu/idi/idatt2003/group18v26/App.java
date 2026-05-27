package edu.ntnu.idi.idatt2003.group18v26;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class for the stock trading game. 
 * Initializes the JavaFX application and sets up the primary stage.
 */
public class App extends Application {


  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() {
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {
      NavigationController nav = NavigationController.getInstance();
      nav.setStage(stage);
      nav.showTitlePage();
    } catch (Exception e) {
      logger.error("An exception was thrown during run-time: {}", e);
    }
  }
}
