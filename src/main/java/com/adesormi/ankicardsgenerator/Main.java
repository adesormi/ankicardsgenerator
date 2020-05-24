package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.io.CardReader;
import com.adesormi.ankicardsgenerator.io.CardWriter;
import com.adesormi.ankicardsgenerator.io.FileReader;
import com.adesormi.ankicardsgenerator.io.FileWriter;
import com.google.common.collect.ImmutableList;

public class Main {

  private static final String PATH = "main/resources/";

  public static void main(String[] args) {
    String propertiesFileName;
    String inputFileName;

    try {
      propertiesFileName = PATH + args[0];
      inputFileName = PATH + args[1];
    } catch(Exception e) {
      throw new IllegalArgumentException();
    }

    Configuration configuration = new Configuration(propertiesFileName);
    CardReader cardReader = new CardReader(configuration.getCardFactory());
    FileReader fileReader = new FileReader(cardReader);
    ImmutableList<Card> cards = fileReader.readCardsFromFile(inputFileName);
    CardFormatter cardFormatter = configuration.getCardFormatter();
    cards.forEach(cardFormatter::formatCard);
    CardWriter cardWriter = new CardWriter();
    FileWriter fileWriter = new FileWriter(cardWriter);
    fileWriter.writeCardsToFile(inputFileName, cards);
  }
}
