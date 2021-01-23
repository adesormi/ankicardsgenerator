package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.InvalidCardException;
import com.google.common.collect.ImmutableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


public class FileReader {

  private final CardReader cardReader;

  public FileReader(CardReader cardReader) {
    this.cardReader = cardReader;
  }

  public ImmutableList<Card> readCardsFromFile(Path filePath) {
    ImmutableList.Builder<Card> builder = ImmutableList.builder();
    try(Stream<String> lines = Files.lines(filePath)) {
      lines.forEach(l -> builder.add(cardReader.readLine(l)));
    } catch(IOException | InvalidCardException e) {
      throw new InvalidInputException();
    }
    return builder.build();
  }
}
