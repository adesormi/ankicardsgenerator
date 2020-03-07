package com.adesormi.ankicardsgenerator;

import com.google.common.collect.ImmutableList;

import java.util.Objects;

class Card {

  private final Field masterField;
  private final ImmutableList<Field> fields;

  protected Card(Field masterField, ImmutableList<Field> fields) {
    this.masterField = masterField;
    this.fields = fields;
  }

  void mapWordsWithColorKeys() {
    ImmutableList<Integer> colorKeys =
        ColorKeysMapGenerator.getColorKeysMap(masterField.getWords());
    masterField.mapWordsWithColorKeys(colorKeys);
    fields.forEach(f -> f.mapWordsWithColorKeys(colorKeys));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Card)) return false;
    Card card = (Card) o;
    return Objects.equals(masterField, card.masterField) &&
        Objects.equals(fields, card.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(masterField, fields);
  }
}
