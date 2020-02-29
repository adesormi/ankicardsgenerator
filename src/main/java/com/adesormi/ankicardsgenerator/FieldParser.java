package com.adesormi.ankicardsgenerator;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Map;

class FieldParser {

  private static FieldParser instance;

  static FieldParser getInstance() {
    if(instance == null) {
      instance = new FieldParser();
    }
    return instance;
  }

  private FieldParser() {}

  Map<Integer, Integer> generateColorIndexMap(String fieldValue) {
    ImmutableList<String> words = getWords(fieldValue);
    Map<Integer, Integer> colorsMap = new HashMap<>(words.size());
    for (int wordIndex = 0; wordIndex < words.size(); ++wordIndex) {
      colorsMap.put(wordIndex, getColorKey(words.get(wordIndex)));
    }
    return colorsMap;
  }

  private ImmutableList<String> getWords(String fieldValue) {
    return ImmutableList.copyOf(Splitter.on(" ").omitEmptyStrings().split(fieldValue));
  }

  private int getColorKey(String word) {
    char lastChar = getLastCharacter(word);
    return Character.isDigit(lastChar) ? lastChar - '0' : 0;
  }

  private char getLastCharacter(String word) {
    return word.charAt(word.length() - 1);
  }
}
