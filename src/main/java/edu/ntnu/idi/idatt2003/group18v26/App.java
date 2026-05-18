package edu.ntnu.idi.idatt2003.group18v26;

import edu.ntnu.idi.idatt2003.group18v26.view.pages.MainPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Hello world!
 */
public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Scene scene = new Scene(new MainPage(), 1280, 720);
    stage.setTitle("Millions");
    stage.setScene(scene);
    stage.show();
  }
}
