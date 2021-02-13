package com.adesormi.ankicardsgenerator.fields;

import com.google.common.collect.ImmutableList;

import java.util.Objects;

public class Field {

  private final ImmutableList<Word> words;
  private final FieldType fieldType;
  private final ImmutableList<ImmutableList<Integer>> keys;
  private boolean isImmutable;

  Field(FieldType fieldType, String value) {
    validateValue(value);
    this.fieldType = fieldType;
    this.words = parseWords(value);
    this.keys = parseKeys();
  }

  public ImmutableList<Word> getWords() { return words; }

  public ImmutableList<ImmutableList<Integer>> getKeys() { return keys; }

  public String wordsSeparator() {
    return fieldType.getWordsParser().wordsSeparator();
  }

  public boolean isImmutable() {
    return isImmutable;
  }

  public void setImmutable(boolean immutable) {
    isImmutable = immutable;
  }

  private void validateValue(String value) {
    if (value == null || value.isEmpty()) throw new InvalidFieldValueException();
  }

  private ImmutableList<Word> parseWords(String value) {
    return fieldType.getWordsParser().parseWords(value);
  }

  private ImmutableList<ImmutableList<Integer>> parseKeys() {
    return fieldType.getKeysParser().parseKeys(words);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Field)) return false;
    Field field = (Field) o;
    return isImmutable == field.isImmutable &&
        Objects.equals(words, field.words) &&
        fieldType == field.fieldType;
  }

}
