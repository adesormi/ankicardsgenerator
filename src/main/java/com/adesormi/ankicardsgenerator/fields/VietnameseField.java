package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.keysparsers.NoKeysWordsKeysParser;
import com.google.common.collect.ImmutableList;

public class VietnameseField extends Field {

  public VietnameseField(String value) {
    super(new NoKeysWordsKeysParser(), value);
  }

  @Override
  public ImmutableList<String> parseValueIntoWords(String value) {
    return ValueParser.parseSpaceSeparatedValue(value);
  }
}
