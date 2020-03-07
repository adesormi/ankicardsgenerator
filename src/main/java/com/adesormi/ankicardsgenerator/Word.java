package com.adesormi.ankicardsgenerator;

import java.util.Objects;

class Word {

  private final String value;
  private int colorKey;

  Word(String value) {
    this.value = value;
  }

  String getValue() { return value; }

  Integer getColorKey() { return colorKey; }

  void setColorKey(int colorKey) { this.colorKey = colorKey; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Word)) return false;
    Word word = (Word) o;
    return colorKey == word.colorKey &&
        Objects.equals(value, word.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, colorKey);
  }
}
