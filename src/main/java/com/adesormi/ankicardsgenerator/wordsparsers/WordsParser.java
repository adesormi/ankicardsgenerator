package com.adesormi.ankicardsgenerator.wordsparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;

public interface WordsParser {

  default ImmutableList<Word> parseWords(String value) {
    ImmutableList<String> values = splitValueIntoWordsStr(value);
    ImmutableList.Builder<Word> builder = ImmutableList.builder();
    values.forEach(v -> builder.add(new Word(v)));
    return builder.build();
  }

  ImmutableList<String> splitValueIntoWordsStr(String value);

  String wordsSeparator();
}
