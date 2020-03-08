package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.Word;
import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import com.google.common.collect.ImmutableList;

public abstract class Field {

  protected ImmutableList<Word> words;
  private KeysParser keysParser;
  private int columnIndex;
  private boolean isImmutable;

  protected Field(KeysParser keysParser, int columnIndex, String value) {
    validateValue(value);
    this.keysParser = keysParser;
    this.columnIndex = columnIndex;
    this.words = getWordsFromValue(value);
  }

  public void colorWords(ImmutableList<Integer> colorKeys) {
    if (isImmutable) return;
    for (int i = 0; i < colorKeys.size(); ++i) {
      words.get(i).setColorKey(colorKeys.get(i));
    }
  }

  public abstract ImmutableList<String> parseValueIntoWords(String value);

  public ImmutableList<Integer> getColorKeysMap() {
    return keysParser.parseKeys(words);
  }

  public ImmutableList<Word> getWords() { return words; }

  public void setImmutable(boolean immutable) { isImmutable = immutable; }

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
