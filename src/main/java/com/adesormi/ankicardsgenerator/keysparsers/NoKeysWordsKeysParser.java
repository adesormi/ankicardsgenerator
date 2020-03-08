package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.Word;
import com.google.common.collect.ImmutableList;

public class NoKeysWordsKeysParser implements KeysParser {

  @Override
  public ImmutableList<Integer> parseKeys(ImmutableList<Word> words) {
    ImmutableList.Builder<Integer> builder = ImmutableList.builder();
    words.forEach(w -> builder.add(0));
    return builder.build();
  }
}
