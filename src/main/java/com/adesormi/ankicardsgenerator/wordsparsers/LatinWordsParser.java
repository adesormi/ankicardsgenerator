package com.adesormi.ankicardsgenerator.wordsparsers;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import static com.adesormi.ankicardsgenerator.Constants.SPACE;

public class LatinWordsParser implements WordsParser {

  @Override
  public ImmutableList<String> splitValueIntoWordsStr(String value) {
    return ImmutableList.copyOf(Splitter.on(SPACE).omitEmptyStrings().trimResults().split(value));
  }

  @Override
  public String wordsSeparator() { return SPACE; }
}
