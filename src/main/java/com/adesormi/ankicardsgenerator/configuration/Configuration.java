package com.adesormi.ankicardsgenerator.configuration;

import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.format.CardFormatter;

public class Configuration {

  private final CardFactory cardFactory;
  private final CardFormatter cardFormatter;

  public Configuration(
      CardFactory cardFactory, CardFormatter cardFormatter) {
    this.cardFactory = cardFactory;
    this.cardFormatter = cardFormatter;
  }

  public CardFactory getCardFactory() {
    return cardFactory;
  }

  public CardFormatter getCardFormatter() { return cardFormatter; }
}
