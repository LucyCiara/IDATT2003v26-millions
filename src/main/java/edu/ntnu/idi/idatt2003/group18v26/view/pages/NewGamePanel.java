package edu.ntnu.idi.idatt2003.group18v26.view.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.newgame.NewGameButtons;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.newgame.NewGameFields;
import edu.ntnu.idi.idatt2003.group18v26.view.components.multicomponents.newgame.NewGameHeader;
import javafx.scene.layout.BorderPane;

/** 
 * Class for the new game panel component in the application.
 */
public class NewGamePanel extends BorderPane {
  private NewGameFields newGameFields;

  private static final Logger logger = LoggerFactory.getLogger(NewGamePanel.class);

  /**
   * Constructs a new NewGamePanel.
   */
  public NewGamePanel() {
    this.newGameFields = new NewGameFields();
    logger.debug("Created NewGameFields");

    getStyleClass().add("page");
    setMaxSize(350, 350);
    setTop(new NewGameHeader());
    setCenter(this.newGameFields);
    setBottom(new NewGameButtons());
    logger.debug("Set top, center and bottom");
  }

  public void changeOpenFileButton(String fileName) {
    this.newGameFields.changeOpenFileButton(fileName);
  }

  public String getPlayerName() {
    return this.newGameFields.getPlayerName();
  }

  public String getPlayerStartMoney() {
    return this.newGameFields.getPlayerStartMoney();
  }

  public void clearFields() {
    this.newGameFields.clearFields();
  }
}
