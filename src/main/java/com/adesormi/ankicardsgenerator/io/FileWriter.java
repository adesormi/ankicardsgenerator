package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.google.common.collect.ImmutableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {

  private final CardWriter cardWriter;

  public FileWriter(CardWriter cardWriter) {
    this.cardWriter = cardWriter;
  }

  public void writeCardsToFile(Path filePath, ImmutableList<Card> cards) {
    try(BufferedWriter bw = Files.newBufferedWriter(filePath)) {
      for (Card c : cards) {
        bw.write(cardWriter.writeCard(c));
        bw.newLine();
      }
    } catch(IOException e) {
      throw new OutputException();
    }
  }
}
