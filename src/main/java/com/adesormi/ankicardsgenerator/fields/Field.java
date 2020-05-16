package com.adesormi.ankicardsgenerator.fields;

import com.google.common.collect.ImmutableList;

public class Field {

  private final ImmutableList<Word> words;
  private final FieldType fieldType;
  private final ImmutableList<Integer> keys;
  private boolean isImmutable;

  Field(FieldType fieldType, String value) {
    validateValue(value);
    this.fieldType = fieldType;
    this.words = parseWords(value);
    this.keys = parseKeys();
  }

  public ImmutableList<Word> getWords() { return words; }

  public ImmutableList<Integer> getKeys() { return keys; }

  public boolean isImmutable() {
    return isImmutable;
  }

  public void setImmutable(boolean immutable) {
    isImmutable = immutable;
  }

  private void validateValue(String value) {
    if (value == null || value.isEmpty()) throw new InvalidValueException();
  }

  private ImmutableList<Word> parseWords(String value) {
    return fieldType.getWordsParser().parseWords(value);
  }

  private ImmutableList<Integer> parseKeys() {
    return fieldType.getKeysParser().parseKeys(words);
  }

  public static class InvalidValueException extends RuntimeException {}
}
