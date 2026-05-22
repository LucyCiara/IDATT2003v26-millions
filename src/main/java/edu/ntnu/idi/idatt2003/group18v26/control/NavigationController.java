package edu.ntnu.idi.idatt2003.group18v26.control;

import java.io.File;

import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import edu.ntnu.idi.idatt2003.group18v26.view.pages.NewGamePanel;
import edu.ntnu.idi.idatt2003.group18v26.view.pages.TitlePage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NavigationController {
  private static NavigationController instance;
  
  private Stage stage;
  private StackPane root;
  private TitlePage titlePage;
  private NewGamePanel newGamePanel;
  private FileChooser fileChooser;

  private NavigationController() {
    this.root = new StackPane();
    this.root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    this.titlePage = new TitlePage();
    this.newGamePanel = new NewGamePanel();
    this.fileChooser = new FileChooser();
  }

  public static NavigationController getInstance() {
    if (instance == null) {
      instance = new NavigationController();
    }
    return instance;
  }
  
  public void showTitlePage() {
    this.root.getChildren().add(titlePage);
  }

  public void showNewGamePanel() {
    this.root.getChildren().add(this.newGamePanel);
  }

  public void hideLastPage() {
    this.root.getChildren().remove(this.root.getChildren().size() - 1);
  }

  public StackPane getRoot() {
    return this.root;
  }

  public File getFileDialogue() {
    File file = this.fileChooser.showOpenDialog(this.stage);
    return file;
  }

  public void setStage(Stage stage) {
    ParameterValidator.objectChecker(stage, "stage");
    this.stage = stage;
  }

  public void changeOpenFileButton(String fileName) {
    this.newGamePanel.changeOpenFileButton(fileName);
  }

  public String getPlayerName() {
    return this.newGamePanel.getPlayerName();
  }

  public String getPlayerStartMoney() {
    return this.newGamePanel.getPlayerStartMoney();
  }

  public void createWarningPopup(String text) {
    Alert warning = new Alert(AlertType.WARNING);
    warning.setContentText(text);
    warning.show();
  }

  public void createErrorPopup(String text) {
    Alert error = new Alert(AlertType.ERROR);
    error.setContentText(text);
    error.show();
  }

  public void clearNewGameFields() {
    this.newGamePanel.clearFields();
  }
}
