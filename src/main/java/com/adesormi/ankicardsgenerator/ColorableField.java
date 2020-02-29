package com.adesormi.ankicardsgenerator;

abstract class ColorableField extends Field {

  ColorableField(String value) {
    super(value);
  }

  @Override
  protected boolean isColorable() {
    return true;
  }
}
