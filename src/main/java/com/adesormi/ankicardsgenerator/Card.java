package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.fields.Field;
import com.google.common.collect.ImmutableList;

public class Card {

  private final Field masterField;
  private final ImmutableList<Field> fields;

  public Card(Field masterField, ImmutableList<Field> fields) {
    this.masterField = masterField;
    this.fields = fields;
  }

  public void color() {
    ImmutableList<Integer> colorKeys = masterField.getColorKeysMap();
    masterField.colorWords(colorKeys);
    fields.forEach(f -> f.colorWords(colorKeys));
  }
}
