package com.adesormi.ankicardsgenerator.fieldsparsers;

import com.google.common.collect.ImmutableList;

public interface FieldParser {
  ImmutableList<String> parseFieldValue(String value);
}
