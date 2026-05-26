package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.menu;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.LoadButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.MainMenuButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.QuitButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ResumeButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SaveButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SellAllAndFinishButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SettingsButton;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class MenuButtons extends VBox {
  public MenuButtons() {
    setAlignment(Pos.CENTER);
    setSpacing(0);
    getChildren().addAll(
      new ResumeButton(),
      new SettingsButton(),
      new SaveButton(),
      new LoadButton(),
      new MainMenuButton(),
      new SellAllAndFinishButton(),
      new QuitButton()
    );
    getChildren().forEach(btn -> ((ButtonType) btn).setMaxWidth(Double.MAX_VALUE));
    setMaxWidth(200);
  }
}
