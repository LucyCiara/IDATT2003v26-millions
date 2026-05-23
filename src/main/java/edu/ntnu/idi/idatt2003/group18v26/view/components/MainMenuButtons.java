package edu.ntnu.idi.idatt2003.group18v26.view.components;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ContinueButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.NewGameButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.QuitButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SettingsButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenuButtons extends VBox {
  private static final double BUTTON_WIDTH = 200;
  private static final double BUTTON_HEIGHT = 50;

  public MainMenuButtons() {
    
    setId("main-menu-buttons");
    setAlignment(Pos.CENTER);
    setSpacing(10);

    NewGameButton newGameBtn = new NewGameButton();
    newGameBtn.setWidthRestriction(BUTTON_WIDTH);
    newGameBtn.setHeightRestriction(BUTTON_HEIGHT);

    ContinueButton continueBtn = new ContinueButton();
    continueBtn.setWidthRestriction(BUTTON_WIDTH);
    continueBtn.setHeightRestriction(BUTTON_HEIGHT);

    SettingsButton settingsBtn = new SettingsButton();
    settingsBtn.setWidthRestriction(BUTTON_WIDTH);
    settingsBtn.setHeightRestriction(BUTTON_HEIGHT);

    QuitButton quitBtn = new QuitButton();
    quitBtn.setWidthRestriction(BUTTON_WIDTH);
    quitBtn.setHeightRestriction(BUTTON_HEIGHT);


    getChildren().addAll(
        newGameBtn,
        continueBtn,
        settingsBtn,
        quitBtn
    );
  }
}