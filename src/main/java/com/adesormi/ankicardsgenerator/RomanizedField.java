package com.adesormi.ankicardsgenerator;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Map;

class RomanizedField extends ColorableField {

  private Map<Integer, Color> colorsMap;

  RomanizedField(String value) {
    super(value);
    generateColorsMap();
  }

  private void generateColorsMap() {
    ImmutableList<String> words = getWords();
    colorsMap = new HashMap<>(words.size());
    for (int colorIndex = 0; colorIndex < words.size(); ++colorIndex) {
      colorsMap.put(colorIndex, getWordColor(words.get(colorIndex)));
    }
  }

  Map<Integer, Color> getColorsMap() {
    return colorsMap;
  }

  private ImmutableList<String> getWords() {
    return ImmutableList.copyOf(Splitter.on(" ").omitEmptyStrings().split(value));
  }

  private Color getWordColor(String word) {
    return Color.values()[getKey(word)];
  }

  private int getKey(String word) {
    char lastChar = getLastCharacter(word);
    return Character.isDigit(lastChar) ? lastChar - '0' : 0;
  }

  private char getLastCharacter(String word) {
    return word.charAt(word.length() - 1);
  }
}
