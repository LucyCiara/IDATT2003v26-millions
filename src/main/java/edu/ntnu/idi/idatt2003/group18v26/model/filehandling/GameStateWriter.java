package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import java.io.IOException;
import java.nio.file.Path;

import edu.ntnu.idi.idatt2003.group18v26.model.persistence.GameSnapshot;

/**
 * Interface for writing game state to files.
 * Enables multiple implementations (JSON, XML, etc.) without changing code.
 */
public interface GameStateWriter {
  
  /**
     * Write game snapshot to file.
     *
     * @param snapshot GameSnapshot to write
     * @param filePath Path where file should be saved
     * @throws IOException if file cannot be written
     */
  void writeGameState(GameSnapshot snapshot, Path filePath) throws IOException;
}
