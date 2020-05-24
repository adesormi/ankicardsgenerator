package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;

import static com.adesormi.ankicardsgenerator.Constants.COMMA_SEPARATOR;

public class CardReader {

  private final CardFactory cardFactory;

  public CardReader(CardFactory cardFactory) {
    this.cardFactory = cardFactory;
  }

  Card readLine(String line) {
    ImmutableList<String> fields = getFieldsAsStringsFromLine(line);
    return cardFactory.createCard(fields);
  }

  private ImmutableList<String> getFieldsAsStringsFromLine(String line) {
    if (line == null || line.isEmpty()) throw new InvalidCardInputException();
    String[] fields = line.trim().split(COMMA_SEPARATOR);
    ImmutableList.Builder<String> builder = ImmutableList.builder();
    Arrays.stream(fields).forEach(f -> builder.add(f.trim()));
    return builder.build();
  }

  public static class InvalidCardInputException extends RuntimeException {}
}
