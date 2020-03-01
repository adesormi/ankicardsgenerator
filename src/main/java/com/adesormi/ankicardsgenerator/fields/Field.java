package com.adesormi.ankicardsgenerator.fields;

public abstract class Field {

  protected String value;

  public Field(String value) {
    this.value = value == null ? "" : value;
  }

  public String getValue() {
    return value;
  }

  protected abstract boolean isColorable();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Field otherField = (Field) o;
    return value.equals(otherField.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
