package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class ToggleButton extends ButtonType {
  public ToggleButton() {
    super();
  }

  public void setSelected() {
    getStyleClass().add("selected");
  }

  public void setNotSelected() {
    if (getStyleClass().contains("selected")) {
      getStyleClass().remove("selected");
    }
  }
}
