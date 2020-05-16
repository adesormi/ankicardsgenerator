package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.fields.Field;
import com.google.common.collect.ImmutableList;

public class Card {

  private final int masterFieldIndex;
  private final ImmutableList<Field> fields;

  public Card(int masterFieldIndex, ImmutableList<Field> fields) {
    this.masterFieldIndex = masterFieldIndex;
    this.fields = fields;
  }

  public void color() {
    ImmutableList<Integer> colorKeys = fields.get(masterFieldIndex).getColorKeysMap();
    fields.forEach(f -> f.colorWords(colorKeys));
  }

  public ImmutableList<Field> getFields() {
    return fields;
  }
}
