package com.adesormi.ankicardsgenerator;

import com.google.common.collect.ImmutableList;

class LogographicField extends Field {

  LogographicField(int columnIndex, boolean isColorable, String value) {
    super(columnIndex, isColorable, value);
  }

  LogographicField(boolean isColorable, String value) {
    this(0, isColorable, value);
  }

  @Override
  protected ImmutableList<String> splitFieldValueIntoWords(String value) {
    ImmutableList.Builder<String> builder = ImmutableList.builder();
    for (char c : value.toCharArray()) {
      builder.add(c + "");
    }
    return builder.build();
  }
}
