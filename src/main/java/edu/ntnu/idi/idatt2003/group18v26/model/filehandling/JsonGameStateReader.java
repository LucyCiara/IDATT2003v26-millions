package edu.ntnu.idi.idatt2003.group18v26.model.filehandling;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import edu.ntnu.idi.idatt2003.group18v26.model.persistence.GameSnapshot;
import edu.ntnu.idi.idatt2003.group18v26.util.ParameterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Concrete implementation: Read JSON save files.
 */
public class JsonGameStateReader implements GameStateReader {
  private static final Logger logger = LoggerFactory.getLogger(JsonGameStateReader.class);
  private static final Gson gson = new Gson();

  /**
   * {@inheritDoc}
   * Read JSON file and deserialize to GameSnapshot.
   */
  @Override
  public GameSnapshot readGameState(Path filepath) throws IOException {
    ParameterValidator.objectChecker(filepath, "filepath");
    logger.info("Reading JSON save file: {}", filepath);

    if (!Files.exists(filepath)) {
      logger.error("Save file not found: {}", filepath);
      throw new IOException("Save file not found: " + filepath);
    }

    try {
      String json = new String(Files.readAllBytes(filepath));
      GameSnapshot snapshot = gson.fromJson(json, GameSnapshot.class);

      logger.info("Successfully loaded game for player: {}", snapshot.playerName);
      return snapshot;

    } catch (JsonSyntaxException e) {
      logger.error("Invalid JSON format in file: {}", filepath, e);
      throw new IOException("Invalid JSON format", e);

    } catch (IOException e) {
      logger.error("Failed to read save file: {}", filepath, e);
      throw e;
    }
  }
}
