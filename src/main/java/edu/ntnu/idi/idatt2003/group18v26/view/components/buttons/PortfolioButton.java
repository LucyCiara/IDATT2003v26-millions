package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class PortfolioButton extends ToggleButton {
  public PortfolioButton() {
    super();
    setText("Portfolio");
    setOnAction(e -> NavigationController.getInstance().selectPortfolio());
  }
}
