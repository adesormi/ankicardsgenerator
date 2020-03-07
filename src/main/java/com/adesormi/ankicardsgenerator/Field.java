package com.adesormi.ankicardsgenerator;

import com.google.common.collect.ImmutableList;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class Field {

  protected ImmutableList<Word> words;
  private int columnIndex;
  private boolean isColorable;

  Field(int columnIndex, boolean isColorable, String value) {
    checkNotNull(value);
    this.columnIndex = columnIndex;
    this.isColorable = isColorable;
    setWords(value);
  }

  protected abstract ImmutableList<String> splitFieldValueIntoWords(String value);

  ImmutableList<Word> getWords() {
    return words;
  }

  private void setWords(String value) {
    ImmutableList<String> values = splitFieldValueIntoWords(value);
    ImmutableList.Builder<Word> builder = ImmutableList.builder();
    values.forEach(v -> builder.add(new Word(v)));
    words = builder.build();
  }

  int getColumnIndex() { return columnIndex; }

  void mapWordsWithColorKeys(ImmutableList<Integer> colorKeys) {
    if (isColorable) {
      for (int i = 0; i < colorKeys.size(); ++i) {
        words.get(i).setColorKey(colorKeys.get(i));
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Field)) return false;
    Field field = (Field) o;
    return columnIndex == field.columnIndex &&
        isColorable == field.isColorable &&
        Objects.equals(words, field.words);
  }

  @Override
  public int hashCode() {
    return Objects.hash(words, columnIndex, isColorable);
  }
}
