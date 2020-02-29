package com.adesormi.ankicardsgenerator;

abstract class Field {

  protected String value;

  Field(String value) {
    this.value = value == null ? "" : value;
  }

  String getValue() {
    return value;
  }

  protected abstract boolean isColorable();
}
