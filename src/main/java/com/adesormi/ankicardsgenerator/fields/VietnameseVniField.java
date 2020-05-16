package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.keysparsers.IntegerKeysWordsKeysParser;
import com.google.common.collect.ImmutableList;

public class VietnameseVniField extends Field {

  public VietnameseVniField(String value) {
    super(new IntegerKeysWordsKeysParser(), value);
  }

  @Override
  public ImmutableList<String> parseValueIntoWords(String value) {
    return ValueParser.parseSpaceSeparatedValue(value);
  }
}
