package com.adesormi.ankicardsgenerator.fields;

public enum FieldType {

  CHINESE,
  CHINESE_PINYIN,
  ENGLISH,
  FRENCH,
  VIETNAMESE,
  VIETNAMESE_VNI;

  public static FieldType getFieldTypeFromString(String fieldTypeName) {
    String name = fieldTypeName.toUpperCase().replace('-', '_');
    try {
      return valueOf(name);
    } catch(IllegalArgumentException iaE) {
      throw new InvalidFieldTypeException();
    }
  }

  public static class InvalidFieldTypeException extends RuntimeException {}
}
