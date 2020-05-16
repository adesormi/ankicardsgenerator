package com.adesormi.ankicardsgenerator.fields;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import static com.adesormi.ankicardsgenerator.Constants.SPACE;

class ValueParser {

  static ImmutableList<String> parseSpaceSeparatedValue(String value) {
    return ImmutableList.copyOf(Splitter.on(SPACE).omitEmptyStrings().trimResults().split(value));
  }

  static ImmutableList<String> parseLogographicValue(String value) {
    ImmutableList.Builder<String> builder = ImmutableList.builder();
    for (char c : value.toCharArray()) {
      builder.add(c + "");
    }
    return builder.build();
  }
}
