package com.adesormi.ankicardsgenerator.wordsparsers;

import com.google.common.collect.ImmutableList;

import static com.adesormi.ankicardsgenerator.Constants.EMPTY;

public class LogographicWordsParser implements WordsParser {

  @Override
  public ImmutableList<String> splitValueIntoWordsStr(String value) {
    ImmutableList.Builder<String> builder = ImmutableList.builder();
    for (char c : value.toCharArray()) {
      builder.add(c + "");
    }
    return builder.build();
  }

  public String wordsSeparator() {
    return EMPTY;
  }
}
