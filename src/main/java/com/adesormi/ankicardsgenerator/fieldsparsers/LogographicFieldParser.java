package com.adesormi.ankicardsgenerator.fieldsparsers;

import com.google.common.collect.ImmutableList;

public class LogographicFieldParser implements FieldParser {

  public ImmutableList<String> parseFieldValue(String value) {
    ImmutableList.Builder<String> builder = ImmutableList.builder();
    for (char c : value.toCharArray()) {
      builder.add(c + "");
    }
    return builder.build();
  }
}
