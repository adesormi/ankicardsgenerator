package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.keysparsers.IntegerKeysWordsKeysParser;
import com.google.common.collect.ImmutableList;

public class ChinesePinyinField extends Field {

  public ChinesePinyinField(String value) {
    super(new IntegerKeysWordsKeysParser(), value);
  }

  @Override
  public ImmutableList<String> parseValueIntoWords(String value) {
    return ValueParser.parseSpaceSeparatedValue(value);
  }
}
