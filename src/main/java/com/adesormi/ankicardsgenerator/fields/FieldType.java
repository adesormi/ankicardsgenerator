package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.wordsparsers.WordsParser;
import com.adesormi.ankicardsgenerator.wordsparsers.LatinWordsParser;
import com.adesormi.ankicardsgenerator.wordsparsers.LogographicWordsParser;
import com.adesormi.ankicardsgenerator.keysparsers.IntegerKeysWordsKeysParser;
import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import com.adesormi.ankicardsgenerator.keysparsers.NoKeysWordsKeysParser;

public enum FieldType {

  CHINESE(new LogographicWordsParser(), new NoKeysWordsKeysParser()),
  CHINESE_PINYIN(new LatinWordsParser(), new IntegerKeysWordsKeysParser()),
  ENGLISH(new LatinWordsParser(), new NoKeysWordsKeysParser()),
  FRENCH(new LatinWordsParser(), new NoKeysWordsKeysParser()),
  VIETNAMESE(new LatinWordsParser(), new NoKeysWordsKeysParser()),
  VIETNAMESE_VNI(new LatinWordsParser(), new NoKeysWordsKeysParser());

  private final WordsParser wordsParser;
  private final KeysParser keysParser;

  FieldType(WordsParser wordsParser, KeysParser keysParser) {
    this.wordsParser = wordsParser;
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

  public WordsParser getWordsParser() {
    return wordsParser;
  }

  public KeysParser getKeysParser() {
    return keysParser;
  }

  public static class InvalidFieldTypeException extends RuntimeException {}
}
