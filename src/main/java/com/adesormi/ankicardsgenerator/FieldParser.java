package com.adesormi.ankicardsgenerator;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public class FieldParser {

  public static int[] getColorsKey(String fieldValue) {
    ImmutableList<String> words = getWords(fieldValue);
    int[] colors = new int[words.size()];
    for (int wordIndex = 0; wordIndex < words.size(); ++wordIndex) {
      colors[wordIndex] = getColorKey(words.get(wordIndex));
    }
    return colors;
  }

  private static ImmutableList<String> getWords(String fieldValue) {
    return ImmutableList.copyOf(
        Splitter.on(" ").omitEmptyStrings().trimResults().split(fieldValue));
  }

  private static int getColorKey(String word) {
    char lastChar = getLastCharacter(word);
    return Character.isDigit(lastChar) ? lastChar - '0' : 0;
  }

  private static char getLastCharacter(String word) {
    return word.charAt(word.length() - 1);
  }
}
