package com.adesormi.ankicardsgenerator;

import com.google.common.collect.ImmutableList;

class ColorKeysMapGenerator {

  static ImmutableList<Integer> getColorKeysMap(ImmutableList<Word> words) {
    ImmutableList.Builder<Integer> builder = ImmutableList.builder();
    words.forEach(w -> builder.add(getColorKey(w)));
    return builder.build();
  }

  private static int getColorKey(Word word) {
    char lastChar = getLastCharacter(word.getValue());
    return Character.isDigit(lastChar) ? lastChar - '0' : 0;
  }

  private static char getLastCharacter(String s) {
    return s.charAt(s.length() - 1);
  }
}
