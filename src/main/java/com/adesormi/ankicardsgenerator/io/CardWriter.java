package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.fields.Field;

import static com.adesormi.ankicardsgenerator.Constants.COMMA_SEPARATOR;
import static com.adesormi.ankicardsgenerator.Constants.SPACE;

public class CardWriter {

  public String writeCard(Card card) {
    StringBuilder sb = new StringBuilder();
    sb.append(getFieldAsString(card.getFields().get(0)));
    for (int i = 1; i < card.getFields().size(); ++i) {
      sb.append(COMMA_SEPARATOR)
        .append(SPACE)
        .append((getFieldAsString(card.getFields().get(i))));
    }
    return sb.toString();
  }

  private String getFieldAsString(Field field) {
    StringBuilder sb = new StringBuilder();
    field.getWords().forEach(w -> sb.append(w.getValue()).append(field.wordsSeparator()));
    return sb.toString().trim();
  }
}
