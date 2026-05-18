package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class NewGameButton extends ButtonType {
  public NewGameButton() {
    super();
    super.setText("New Game");
    super.setOnAction(e -> System.out.println("New Game"));
  }
}
