package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.google.common.collect.ImmutableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.adesormi.ankicardsgenerator.Constants.POINT_SEPARATOR;
import static com.adesormi.ankicardsgenerator.Constants.ROOT_PATH;

public class FileWriter {

  private final CardWriter cardWriter;

  public FileWriter(CardWriter cardWriter) {
    this.cardWriter = cardWriter;
  }

  public void writeCardsToFile(String fileName, ImmutableList<Card> cards) {
    Path filePath = getFilePath(fileName);
    try(BufferedWriter bw = Files.newBufferedWriter(filePath)) {
      for (Card c : cards) {
        bw.write(cardWriter.writeCard(c));
        bw.newLine();
      }
    } catch(Exception e) {
      throw new InvalidOutputException();
    }
  }

  private Path getFilePath(String fileName) {
    if (fileName == null || fileName.isEmpty()) throw new InvalidOutputFileException();
    String[] fileNameParts = fileName.split("\\" + POINT_SEPARATOR);
    if (fileNameParts.length != 2) throw new InvalidOutputFileException();
    String outputFileName = fileNameParts[0] + "_output." + fileNameParts[1];
    return Paths.get(ROOT_PATH, outputFileName);
  }

  public static class InvalidOutputFileException extends RuntimeException {}

  public static class InvalidOutputException extends RuntimeException {}
}
