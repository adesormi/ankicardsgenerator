package com.adesormi.ankicardsgenerator.chinese;

import com.adesormi.ankicardsgenerator.Card;
import com.adesormi.ankicardsgenerator.CardInputParser;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

class ChineseCardInputParser extends CardInputParser {

  private static final int NUMBER_FIELDS = 3;

  private static ChineseCardInputParser instance;

  private ChineseCardInputParser() {}

  static ChineseCardInputParser getInstance() {
    if (instance == null) {
      instance = new ChineseCardInputParser();
    }
    return instance;
  }

  @Override
  public Card parseCardInput(String input) {
    ImmutableList<String> values = getFieldsValue(input);
    validateNumberFields(values);
    return new ChineseCard(
        /* baseValue= */ values.get(0),
        /* logographicValue= */ values.get(1),
        /* romanizedValue= */ values.get(2));
  }

  private void validateNumberFields(ImmutableList<String> values) {
    if (values.size() > NUMBER_FIELDS) throw new TooManyFieldsException();
    if (values.size() < NUMBER_FIELDS) throw new NotEnoughFieldsException();
  }

  private ImmutableList<String> getFieldsValue(String line) {
    return ImmutableList.copyOf(
        Splitter.on(",").omitEmptyStrings().trimResults().split(line));
  }
}
