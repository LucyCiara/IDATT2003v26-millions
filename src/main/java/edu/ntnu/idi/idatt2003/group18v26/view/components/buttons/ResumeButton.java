package edu.ntnu.idi.idatt2003.group18v26.view.components.buttons;

import edu.ntnu.idi.idatt2003.group18v26.control.NavigationController;

public class ResumeButton extends ButtonType {
  public ResumeButton() {
    super();
    setText("Resume");
    setOnAction(e -> NavigationController.getInstance().hideLastPage());
  }
}
