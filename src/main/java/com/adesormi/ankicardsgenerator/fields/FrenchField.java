package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.keysparsers.NoKeysWordsKeysParser;
import com.google.common.collect.ImmutableList;

public class FrenchField extends Field {

  public FrenchField(String value) {
    super(new NoKeysWordsKeysParser(), value);
  }

  @Override
  public ImmutableList<String> parseValueIntoWords(String value) {
    return ValueParser.parseSpaceSeparatedValue(value);
  }
}
