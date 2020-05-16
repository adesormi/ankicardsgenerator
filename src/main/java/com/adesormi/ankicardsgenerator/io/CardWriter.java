package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.Card;
import com.adesormi.ankicardsgenerator.fields.Field;
import com.adesormi.ankicardsgenerator.format.CardFormatter;

import static com.adesormi.ankicardsgenerator.Constants.SEPARATOR;
import static com.adesormi.ankicardsgenerator.Constants.SPACE;

public class CardWriter {

  private final CardFormatter cardFormatter;

  public CardWriter(CardFormatter cardFormatter) {
    this.cardFormatter = cardFormatter;
  }

  public String writeCard(Card card) {
    StringBuilder sb = new StringBuilder();
    card.getFields().forEach(f -> sb.append(getFieldAsString(f)).append(SEPARATOR).append(SPACE));
    return sb.toString();
  }

  private String getFieldAsString(Field field) {
    return "";
  }
}
