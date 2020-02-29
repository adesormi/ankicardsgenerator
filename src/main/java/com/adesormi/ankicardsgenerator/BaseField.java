package com.adesormi.ankicardsgenerator;

class BaseField extends Field {

  BaseField(String value) {
    super(value);
  }

  @Override
  protected boolean isColorable() {
    return false;
  }
}
