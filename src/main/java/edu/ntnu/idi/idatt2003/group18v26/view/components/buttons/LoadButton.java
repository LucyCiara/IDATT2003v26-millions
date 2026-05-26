package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class LoadButton extends ButtonType {
  public LoadButton() {
    super();
    setText("Load");
    setOnAction(e -> System.out.println("Load"));
  }
}
