package edu.ntnu.idi.idatt2003.group18v26.control;

import edu.ntnu.idi.idatt2003.group18v26.view.pages.NewGamePanel;
import edu.ntnu.idi.idatt2003.group18v26.view.pages.TitlePage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class NavigationController {
  private StackPane root;
  private TitlePage titlePage;
  private static NavigationController instance;

  private NavigationController(StackPane root) {
    this.root = root;
    this.root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    this.titlePage = new TitlePage();
  }

  public static NavigationController getInstance() {
    if (instance == null) {
      instance = new NavigationController(new StackPane());
    }
    return instance;
  }

  public void showTitlePage() {
    this.root.getChildren().add(titlePage);
  }

  public void showNewGamePanel() {
    this.root.getChildren().add(new NewGamePanel());
  }

  public void hideLastPage() {
    this.root.getChildren().remove(this.root.getChildren().size() - 1);
  }

  public StackPane getRoot() {
    return this.root;
  }
}
