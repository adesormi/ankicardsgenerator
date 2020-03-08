package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import com.google.common.collect.ImmutableList;

public class EnglishField extends Field {

  public EnglishField(KeysParser keysParser, int columnIndex, String value) {
    super(keysParser, columnIndex, value);
  }

  @Override
  public ImmutableList<String> parseValueIntoWords(String value) {
    return ValueParser.parseSpaceSeparatedValue(value);
  }
}
