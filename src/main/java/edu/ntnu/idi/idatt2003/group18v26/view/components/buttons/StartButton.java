package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class StartButton extends ButtonType {
  public StartButton() {
    super();
    super.setText("Done");
    super.setOnAction(e -> System.out.println("New Game Started"));
  }
}
