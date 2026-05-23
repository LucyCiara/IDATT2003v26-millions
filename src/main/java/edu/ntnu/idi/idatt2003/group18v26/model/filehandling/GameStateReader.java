package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import edu.ntnu.idi.idatt2003.group18v26.model.percistence.GameSnapshot;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Interface for reading game state from files.
 * Enables multiple implementations (JSON, XML, etc.) without changing code.
 */
public interface GameStateReader {

  /**
   * Read game state from file and return snapshot.
   *
   * @param filePath Path to the save file
   * @return GameSnapshot with loaded data
   * @throws IOException if file cannot be read
   */
  GameSnapshot readGameState(Path filePath) throws IOException;
}
