package edu.ntnu.idi.idatt2003.group18v26;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class for the stock trading game. 
 * Initializes the JavaFX application and sets up the primary stage.
 */
public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() {
  }

  @Override
  public void start(Stage stage) throws Exception {
    NavigationController nav = NavigationController.getInstance();
    nav.setStage(stage);
    nav.showTitlePage();
  }
}
