package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.configuration.Configuration;
import com.adesormi.ankicardsgenerator.configuration.ConfigurationHandler;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.io.*;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  private Command command;
  private Path configFile;
  private Path workingDirectory;

  private FileReader fileReader;
  private CardFormatter cardFormatter;
  private FileWriter fileWriter;

  public static void main(String[] args) {
    new Application().run(args);
  }

  private void run(String[] args) {
    LOGGER.info("Starting Anki Cards Generator...");
    LOGGER.info("");
    try {
      validateArgs(args);
      executeCommand();
    } catch(Exception e) {
      help();
    }
  }

  private void executeCommand() {
    switch (command) {
      case CONFIGURE:
        configure();
        break;
      case GENERATE:
        setup();
        generateCards();
        break;
      case HELP:
        help();
        break;
      default:
        throw new InvalidArgsException();
    }
  }

  private void configure() {
    LOGGER.info("Configuring...");
    ConfigurationHandler configHandler = new ConfigurationHandler();
    boolean updated = configHandler.updateConfiguration(configFile);
    LOGGER.info(updated ? "Configuration successfully updated" : "An error occurred during the configuration update.");
  }

  private void setup() {
    Configuration configuration = new ConfigurationHandler().loadConfiguration();
    fileReader = new FileReader(new CardReader(configuration.getCardFactory()));
    cardFormatter = configuration.getCardFormatter();
    fileWriter = new FileWriter(new CardWriter());
  }

  private void generateCards() {
    LOGGER.info("Generating cards...");
    getFilesFromWorkingDirectory().forEach(f -> {
      ImmutableList<Card> cards = fileReader.readCardsFromFile(f);
      cards.forEach(cardFormatter::formatCard);
      fileWriter.writeCardsToFile(getOutputFile(f), cards);
    });
  }

  private List<Path> getFilesFromWorkingDirectory() {
    try {
      return Files.list(workingDirectory)
                  .filter(Files::isRegularFile)
                  .map(Path::toAbsolutePath)
                  .collect(Collectors.toList());
    } catch(IOException ioE) {
      throw new InvalidArgsException();
    }
  }

  private Path getOutputFile(Path filePath) {
    String[] fileNameParts = filePath.getFileName().toString().split("\\.");
    if (fileNameParts.length > 2) throw new OutputException();
    String outputFileName = fileNameParts.length == 1
        ? fileNameParts[0] + "_output"
        : fileNameParts[0] + "_output." + fileNameParts[1];
    return Paths.get(filePath.getParent().toString(), outputFileName);
  }

  private void help() {
    LOGGER.info("Usage: ./ankicardsgenerator <command> <args>");
    LOGGER.info("");
    LOGGER.info("  - ./ankicardsgenerator configure <path-to-file>");
    LOGGER.info("  - ./ankicardsgenerator generate <input-directory>");
    LOGGER.info("  - ./ankicardsgenerator help");
  }

  private void validateArgs(String[] args) {
    if (args == null || args.length <= 0 || args.length > 2) throw new InvalidArgsException();
    switch (args[0]) {
      case "configure":
        command = Command.CONFIGURE;
        configFile = getConfigFilePath(args);
        break;
      case "generate":
        command = Command.GENERATE;
        workingDirectory = getCardsDirPath(args);
        break;
      case "help":
        command = Command.HELP;
        break;
      default:
        throw new InvalidArgsException();
    }
  }

  private Path getConfigFilePath(String[] args) {
    Path file = Paths.get(args[1]);
    if (!file.toFile().isFile()) throw new InvalidArgsException();
    return file;
  }

  private Path getCardsDirPath(String[] args) {
    Path dir = Paths.get(args[1]);
    if (!dir.toFile().isDirectory()) throw new InvalidArgsException();
    return dir;
  }
}
