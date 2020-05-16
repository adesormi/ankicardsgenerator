package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.Word;
import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import com.google.common.collect.ImmutableList;

public class Field {

  protected final ImmutableList<Word> words;
  private final FieldType fieldType;
  private boolean isImmutable;

  public Field(FieldType fieldType, String value) {
    validateValue(value);
    this.fieldType = fieldType;
    this.words = getWordsFromValue(value);
  }

  public void colorWords(ImmutableList<Integer> colorKeys) {
    if (isImmutable) return;
    for (int i = 0; i < colorKeys.size(); ++i) {
      words.get(i).setKey(colorKeys.get(i));
    }
  }

  public ImmutableList<String> parseValueIntoWords(String value) {
    return fieldType.getFieldParser().parseFieldValue(value);
  }

  public ImmutableList<Integer> getColorKeysMap() {
    return fieldType.getKeysParser().parseKeys(words);
  }

  public ImmutableList<Word> getWords() { return words; }

  public boolean isImmutable() {
    return isImmutable;
  }

  public void setImmutable(boolean immutable) {
    isImmutable = immutable;
  }

  private void validateValue(String value) {
    if (value == null || value.isEmpty()) throw new InvalidValueException();
  }

  private ImmutableList<Word> getWordsFromValue(String value) {
    ImmutableList<String> values = parseValueIntoWords(value);
    ImmutableList.Builder<Word> builder = ImmutableList.builder();
    values.forEach(v -> builder.add(new Word(v)));
    return builder.build();
  }

  public static class InvalidValueException extends RuntimeException {}
}
