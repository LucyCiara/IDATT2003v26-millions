package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class ContinueButton extends ButtonType {
  public ContinueButton() {
    super();
    setText("Continue");
    setOnAction(e -> System.out.println("Continue"));
  }
}