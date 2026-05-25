package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.idi.idatt2003.group18v26.model.persistence.GameSnapshot;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete implementation: Write JSON save files.
 */
public class JsonGameStateWriter implements GameStateWriter {
  private static final Logger logger = LoggerFactory.getLogger(JsonGameStateWriter.class);
  private static final Gson gson = new GsonBuilder()
      .setPrettyPrinting()
      .create();

  /**
   * {@inheritDoc}
   * Serialize GameSnapshot to JSON and write to file.
   * Creates parent directories if they don't exist.
   */
  @Override
  public void writeGameState(GameSnapshot snapshot, Path filepath) throws IOException {
    ParameterValidator.objectChecker(snapshot, "snapshot");
    ParameterValidator.objectChecker(filepath, "filepath");
    logger.info("Writing JSON save file for player: {}", snapshot.playerName);

    try {
      // Create saves/ directory if it doesn't exist
      Files.createDirectories(filepath.getParent());

      // Serialize snapshot to JSON string with pretty printing
      String json = gson.toJson(snapshot);

      // Write JSON to file
      Files.write(filepath, json.getBytes());

      logger.info("Successfully saved game for player: {} to {}",
          snapshot.playerName, filepath);

    } catch (IOException e) {
      logger.error("Failed to write save file: {}", filepath, e);
      throw e;
    }
  }
}