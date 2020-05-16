package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.Card;
import com.adesormi.ankicardsgenerator.CardFactory;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;

import static com.adesormi.ankicardsgenerator.Constants.SEPARATOR;

public class CardReader {

  private final CardFactory cardFactory;

  public CardReader(CardFactory cardFactory) {
    this.cardFactory = cardFactory;
  }

  public Card readLine(String line) {
    ImmutableList<String> fields = getFieldsAsStringsFromLine(line);
    return cardFactory.createCard(fields);
  }

  private ImmutableList<String> getFieldsAsStringsFromLine(String line) {
    if (line == null || line.isEmpty()) throw new InvalidCardInputException();
    String[] fields = line.trim().split(SEPARATOR);
    ImmutableList.Builder<String> builder = ImmutableList.builder();
    Arrays.stream(fields).forEach(f -> builder.add(f.trim()));
    return builder.build();
  }

  public static class InvalidCardInputException extends RuntimeException {}
}
