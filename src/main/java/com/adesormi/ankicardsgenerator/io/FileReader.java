package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.adesormi.ankicardsgenerator.Constants.ROOT_PATH;

public class FileReader {

  private final CardReader cardReader;

  public FileReader(CardReader cardReader) {
    this.cardReader = cardReader;
  }

  public ImmutableList<Card> readCardsFromFile(String fileName) {
    Path filePath = getFilePath(fileName);
    return readCard(filePath);
  }

  private Path getFilePath(String fileName) {
    if (fileName == null || fileName.isEmpty()) throw new InvalidInputFileException();
    return Paths.get(ROOT_PATH, fileName);
  }

  private ImmutableList<Card> readCard(Path filePath) {
    try(Stream<String> lines = Files.lines(filePath)) {
      ImmutableList.Builder<Card> builder = ImmutableList.builder();
      lines.forEach(l -> builder.add(cardReader.readLine(l)));
      return builder.build();
    } catch(IOException ioE) {
      throw new InvalidInputFileException();
    }
  }

  public static class InvalidInputFileException extends RuntimeException {}
}
