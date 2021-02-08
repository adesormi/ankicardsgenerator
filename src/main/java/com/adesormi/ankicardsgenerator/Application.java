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

  private FileReader fileReader;
  private CardFormatter cardFormatter;
  private FileWriter fileWriter;

  public static void main(String[] args) {
    Input input = null;
    try {
      input = new CommandLineHandler().getInput(args);
    } catch (InvalidArgsException e) {
      help();
      System.exit(0);
    }
    new Application().configure(input.getConfigFilePath()).generateCards(input.getWorkingDirPath());
  }

  private Application configure(Path configFile) {
    LOGGER.info("Setting up configuration...");
    Configuration configuration = new ConfigurationHandler().loadConfiguration(configFile);
    fileReader = new FileReader(new CardReader(configuration.getCardFactory()));
    cardFormatter = configuration.getCardFormatter();
    fileWriter = new FileWriter(new CardWriter());
    return this;
  }

  private void generateCards(Path workingDirectory) {
    LOGGER.info("Generating cards...");
    getFilesFromWorkingDirectory(workingDirectory).forEach(f -> {
      ImmutableList<Card> cards = fileReader.readCardsFromFile(f);
      cards.forEach(cardFormatter::formatCard);
      fileWriter.writeCardsToFile(getOutputFile(f), cards);
    });
  }

  private List<Path> getFilesFromWorkingDirectory(Path workingDirectory) {
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

  private static void help() {
    System.out.println(
        "\nUsage: ./ankicardsgenerator [-c, --configuration] <config> [-w, --working_dir] <dir>\n");
  }
}
