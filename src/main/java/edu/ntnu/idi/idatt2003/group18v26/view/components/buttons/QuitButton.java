package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class QuitButton extends ButtonType {
  public QuitButton() {
    super();
    super.setText("Quit");
    super.setOnAction(e -> System.out.println("Quit"));
  }
}