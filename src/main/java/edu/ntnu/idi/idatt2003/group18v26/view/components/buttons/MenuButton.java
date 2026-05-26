package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class MenuButton extends ButtonType {
  public MenuButton() {
    super();
    setText("Menu");
    setOnAction(e -> System.out.println("Menu"));
  }
}
