package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.io.CardReader;
import com.adesormi.ankicardsgenerator.io.CardWriter;
import com.adesormi.ankicardsgenerator.io.FileReader;
import com.google.common.collect.ImmutableList;

public class Main {

  public static void main(String[] args) {
    Configuration configuration = new Configuration("propertiesFile");
    CardReader cardReader = new CardReader(configuration.getCardFactory());
    FileReader fileReader = new FileReader(cardReader);
    ImmutableList<Card> cards = fileReader.readCardsFromFile("file");
    CardFormatter cardFormatter = configuration.getCardFormatter();
    cards.forEach(cardFormatter::formatCard);
    CardWriter cardWriter = new CardWriter();
    cards.forEach(cardWriter::writeCard);
  }
}
