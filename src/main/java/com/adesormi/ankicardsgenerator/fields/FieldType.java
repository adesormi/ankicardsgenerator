package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.fieldsparsers.FieldParser;
import com.adesormi.ankicardsgenerator.fieldsparsers.LatinFieldParser;
import com.adesormi.ankicardsgenerator.fieldsparsers.LogographicFieldParser;
import com.adesormi.ankicardsgenerator.keysparsers.IntegerKeysWordsKeysParser;
import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import com.adesormi.ankicardsgenerator.keysparsers.NoKeysWordsKeysParser;

public enum FieldType {

  CHINESE(new LogographicFieldParser(), new NoKeysWordsKeysParser()),
  CHINESE_PINYIN(new LatinFieldParser(), new IntegerKeysWordsKeysParser()),
  ENGLISH(new LatinFieldParser(), new NoKeysWordsKeysParser()),
  FRENCH(new LatinFieldParser(), new NoKeysWordsKeysParser()),
  VIETNAMESE(new LatinFieldParser(), new NoKeysWordsKeysParser()),
  VIETNAMESE_VNI(new LatinFieldParser(), new NoKeysWordsKeysParser());

  private final FieldParser fieldParser;
  private final KeysParser keysParser;

  FieldType(FieldParser fieldParser, KeysParser keysParser) {
    this.fieldParser = fieldParser;
    this.keysParser = keysParser;
  }

  public static FieldType getFieldTypeFromString(String fieldTypeName) {
    String name = fieldTypeName.toUpperCase().replace('-', '_');
    try {
      return valueOf(name);
    } catch(IllegalArgumentException iaE) {
      throw new InvalidFieldTypeException();
    }
  }

  public FieldParser getFieldParser() {
    return fieldParser;
  }

  public KeysParser getKeysParser() {
    return keysParser;
  }

  public static class InvalidFieldTypeException extends RuntimeException {}
}
