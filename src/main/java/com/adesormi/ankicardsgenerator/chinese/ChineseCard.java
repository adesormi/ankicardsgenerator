package com.adesormi.ankicardsgenerator.chinese;

import com.adesormi.ankicardsgenerator.Card;
import com.adesormi.ankicardsgenerator.FieldParser;
import com.adesormi.ankicardsgenerator.fields.BaseField;
import com.adesormi.ankicardsgenerator.fields.ColorableField;
import com.adesormi.ankicardsgenerator.fields.LogographicField;
import com.adesormi.ankicardsgenerator.fields.RomanizedField;
import com.google.common.collect.ImmutableList;

class ChineseCard extends Card {

  ChineseCard(String baseValue, String logographicValue, String romanizedValue) {
    super(
        new BaseField(baseValue),
        ImmutableList.of(
            new LogographicField(logographicValue),
            new RomanizedField(romanizedValue))
    );
  }

  String getHanzi() {
    return logographicField().getValue();
  }

  String getPinyin() {
    return romanizedField().getValue();
  }

  @Override
  protected void format() {
    int[] colors = FieldParser.getColorsKey(romanizedField().getValue());
    romanizedField().color(colors);
    logographicField().color(colors);
  }

  private ColorableField logographicField() {
    return colorableFields.get(0);
  }

  private ColorableField romanizedField() {
    return colorableFields.get(1);
  }
}
