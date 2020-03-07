package com.adesormi.ankicardsgenerator;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

class LatinField extends Field {

  LatinField(int columnIndex, boolean isColorable, String value) {
    super(columnIndex, isColorable, value);
  }

  LatinField(boolean isColorable, String value) {
    this(0, isColorable, value);
  }

  @Override
  protected ImmutableList<String> splitFieldValueIntoWords(String value) {
    return ImmutableList.copyOf(Splitter.on(" ").omitEmptyStrings().trimResults().split(value));
  }
}
