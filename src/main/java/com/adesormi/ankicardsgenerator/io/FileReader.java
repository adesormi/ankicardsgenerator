package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.InvalidCardException;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


public class FileReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

  private final CardReader cardReader;

  public FileReader(CardReader cardReader) {
    this.cardReader = cardReader;
  }

  public ImmutableList<Card> readCardsFromFile(Path filePath) {
    LOGGER.info("Reading cards from: " + filePath);
    ImmutableList.Builder<Card> builder = ImmutableList.builder();
    try(Stream<String> lines = Files.lines(filePath)) {
      lines.forEach(l -> builder.add(cardReader.readLine(l)));
    } catch(IOException | InvalidCardException e) {
      throw new InvalidInputException();
    }
    return builder.build();
  }
}
