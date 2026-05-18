package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class SettingsButton extends ButtonType {
  public SettingsButton() {
    super();
    super.setText("Settings");
    super.setOnAction(e -> System.out.println("Settings"));
  }
}