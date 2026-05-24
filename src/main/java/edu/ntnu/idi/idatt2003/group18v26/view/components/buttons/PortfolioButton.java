package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

public class PortfolioButton extends ToggleButton {
  public PortfolioButton() {
    super();
    setText("Portfolio");
    setOnAction(e -> System.out.println("Portfolio"));
  }
}
