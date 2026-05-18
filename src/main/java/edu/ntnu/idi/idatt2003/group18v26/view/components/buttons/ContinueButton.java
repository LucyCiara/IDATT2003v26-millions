package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class ContinueButton extends ButtonType {
  public ContinueButton() {
    super();
    super.setText("Continue");
    super.setOnAction(e -> System.out.println("Continue"));
  }
}