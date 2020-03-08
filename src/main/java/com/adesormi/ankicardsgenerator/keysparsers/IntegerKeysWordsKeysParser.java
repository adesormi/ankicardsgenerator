package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.Word;
import com.google.common.collect.ImmutableList;

public class IntegerKeysWordsKeysParser implements KeysParser {

  @Override
  public ImmutableList<Integer> parseKeys(ImmutableList<Word> words) {
    ImmutableList.Builder<Integer> builder = ImmutableList.builder();
    words.forEach(w -> builder.add(getColorKey(w)));
    return builder.build();
  }

  private int getColorKey(Word word) {
    char lastChar = getLastCharacter(word.getValue());
    return Character.isDigit(lastChar) ? lastChar - '0' : 0;
  }

  private char getLastCharacter(String s) {
    return s.charAt(s.length() - 1);
  }
}
