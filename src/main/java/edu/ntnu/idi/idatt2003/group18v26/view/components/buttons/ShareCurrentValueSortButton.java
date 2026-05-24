package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class ShareCurrentValueSortButton extends ButtonType {
  public ShareCurrentValueSortButton() {
    super();
    setText("Current Value");
    setOnAction(e -> System.out.println("Current Value"));
  }
}
