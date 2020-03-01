package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.fields.BaseField;
import com.adesormi.ankicardsgenerator.fields.ColorableField;
import com.google.common.collect.ImmutableList;

public abstract class Card {

  protected final ImmutableList<ColorableField> colorableFields;
  private final BaseField baseField;

  protected Card(BaseField baseField, ImmutableList<ColorableField> colorableFields) {
    this.baseField = baseField;
    this.colorableFields = colorableFields;
  }

  protected abstract void format();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Card otherCard = (Card) o;
    return baseField.equals(baseField) && colorableFields.equals(otherCard.colorableFields);
  }

  @Override
  public int hashCode() {
    return baseField.hashCode() + colorableFields.hashCode();
  }
}
