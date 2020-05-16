package com.adesormi.ankicardsgenerator.fields;

public class FieldFactory {

  public Field createField(FieldType fieldType, String fieldValue) {
    switch (fieldType) {
      case CHINESE:
        return new ChineseHanziField(fieldValue);
      case CHINESE_PINYIN:
        return new ChinesePinyinField(fieldValue);
      case ENGLISH:
        return new EnglishField(fieldValue);
      case FRENCH:
        return new FrenchField(fieldValue);
      case VIETNAMESE:
        return new VietnameseField(fieldValue);
      case VIETNAMESE_VNI:
        return new VietnameseVniField(fieldValue);
      default:
        throw new FieldType.InvalidFieldTypeException();
    }
  }
}
