package com.adesormi.ankicardsgenerator;

import java.util.Objects;

public class Word {

  private final String value;
  private int key;

  public Word(String value) {
    this.value = value;
  }

  public String getValue() { return value; }

  public int getKey() { return key; }

  public void setKey(int key) { this.key = key; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Word)) return false;
    Word word = (Word) o;
    return Objects.equals(value, word.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
