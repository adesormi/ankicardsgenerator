package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cli.InvalidArgsException;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.io.FileReader;
import com.adesormi.ankicardsgenerator.io.FileWriter;
import com.adesormi.ankicardsgenerator.io.OutputException;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {

  private static final Logger LOGGER = LoggerFactory.getLogger(Generator.class);

  private final FileReader fileReader;
  private final CardFormatter cardFormatter;
  private final FileWriter fileWriter;

  public Generator(FileReader fileReader, CardFormatter cardFormatter, FileWriter fileWriter) {
    this.fileReader = fileReader;
    this.cardFormatter = cardFormatter;
    this.fileWriter = fileWriter;
  }

  public void generateCards(Path workingDirectory) {
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
}
