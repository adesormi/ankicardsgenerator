package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;

public class NoKeysWordsKeysParser implements KeysParser {

  @Override
  public ImmutableList<ImmutableList<Integer>> parseKeys(ImmutableList<Word> words) {
    ImmutableList.Builder<ImmutableList<Integer>> builder = ImmutableList.builder();
    words.forEach(w -> builder.add(ImmutableList.of()));
    return builder.build();
  }
}
