package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.fields.Field;
import com.google.common.collect.ImmutableList;

public abstract class Card {

  protected final ImmutableList<Field> fields;

  protected Card(ImmutableList<Field> fields) {
    this.fields = fields;
  }

  protected abstract void format();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Card otherCard = (Card) o;
    return fields.equals(otherCard.fields);
  }

  @Override
  public int hashCode() {
    return fields.hashCode();
  }
}
