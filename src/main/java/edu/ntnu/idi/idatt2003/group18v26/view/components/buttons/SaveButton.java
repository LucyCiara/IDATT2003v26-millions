package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class SaveButton extends ButtonType {
  public SaveButton() {
    super();
    setText("Save");
    setOnAction(e -> System.out.println("Save"));
  }
}
