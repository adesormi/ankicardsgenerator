package com.adesormi.ankicardsgenerator.fieldsparsers;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import static com.adesormi.ankicardsgenerator.Constants.SPACE;

public class LatinFieldParser implements FieldParser {

  public ImmutableList<String> parseFieldValue(String value) {
    return ImmutableList.copyOf(Splitter.on(SPACE).omitEmptyStrings().trimResults().split(value));
  }
}
