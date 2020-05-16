package com.adesormi.ankicardsgenerator.fields;

public class FieldFactory {

  public Field createField(FieldType fieldType, String fieldValue) {
    return new Field(fieldType, fieldValue);
  }
}
