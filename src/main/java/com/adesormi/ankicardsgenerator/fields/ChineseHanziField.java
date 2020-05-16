package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.keysparsers.NoKeysWordsKeysParser;
import com.google.common.collect.ImmutableList;

public class ChineseHanziField extends Field {

  public ChineseHanziField(String value) {
    super(new NoKeysWordsKeysParser(), value);
  }

  @Override
  public ImmutableList<String> parseValueIntoWords(String value) {
    return ValueParser.parseLogographicValue(value);
  }
}
