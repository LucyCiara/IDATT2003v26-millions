package edu.ntnu.idi.idatt2003.group18v26.view.components.searchbars;

import edu.ntnu.idi.idatt2003.group18v26.control.GameController;

public class PortfolioSearchBar extends SearchBar {
  public PortfolioSearchBar() {
    super(GameController.getInstance().getPortfolioShareNames());
  }
}
