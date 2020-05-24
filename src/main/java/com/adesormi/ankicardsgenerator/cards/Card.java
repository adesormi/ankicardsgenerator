package com.adesormi.ankicardsgenerator.cards;

import com.adesormi.ankicardsgenerator.fields.Field;
import com.google.common.collect.ImmutableList;

import java.util.Objects;

public class Card {

  private final int masterFieldIndex;
  private final ImmutableList<Field> fields;

  Card(int masterFieldIndex, ImmutableList<Field> fields) {
    this.masterFieldIndex = masterFieldIndex;
    this.fields = fields;
  }

  public Field getMasterField() { return fields.get(masterFieldIndex); }

  public ImmutableList<Field> getFields() {
    return fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Card)) return false;
    Card card = (Card) o;
    return masterFieldIndex == card.masterFieldIndex &&
        Objects.equals(fields, card.fields);
  }
}
