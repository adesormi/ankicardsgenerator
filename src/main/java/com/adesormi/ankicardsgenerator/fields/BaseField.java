package com.adesormi.ankicardsgenerator.fields;

public class BaseField extends Field {

  public BaseField(String value) {
    super(value);
  }

  @Override
  protected boolean isColorable() {
    return false;
  }
}
