package edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.title;

import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ButtonType;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.ContinueButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.NewGameButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.QuitButton;
import edu.ntnu.idi.idatt2003.group18v26.view.components.buttons.SettingsButton;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Class for the main menu buttons component, 
 * which includes the new game, continue, settings, and quit buttons.
 */
public class MainMenuButtons extends VBox {
  private static final double BUTTON_WIDTH = 200;

  /**
   * Constructs a new MainMenuButtons.
   */
  public MainMenuButtons() {
    
    setAlignment(Pos.CENTER);
    setSpacing(10);

    setMaxWidth(BUTTON_WIDTH);

    NewGameButton newGameBtn = new NewGameButton();
    ContinueButton continueBtn = new ContinueButton();
    SettingsButton settingsBtn = new SettingsButton();
    QuitButton quitBtn = new QuitButton();

    getChildren().addAll(
        newGameBtn,
        continueBtn,
        settingsBtn,
        quitBtn
    );

    getChildren().forEach(btn -> ((ButtonType) btn).setMaxWidth(Double.MAX_VALUE));
  }
}