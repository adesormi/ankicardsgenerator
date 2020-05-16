package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.fields.Field;

import static com.adesormi.ankicardsgenerator.Constants.SEPARATOR;
import static com.adesormi.ankicardsgenerator.Constants.SPACE;

public class CardWriter {

  public String writeCard(Card card) {
    StringBuilder sb = new StringBuilder();
    card.getFields().forEach(f -> sb.append(getFieldAsString(f)).append(SEPARATOR).append(SPACE));
    return sb.toString();
  }

  private String getFieldAsString(Field field) {
    return "";
  }
}
