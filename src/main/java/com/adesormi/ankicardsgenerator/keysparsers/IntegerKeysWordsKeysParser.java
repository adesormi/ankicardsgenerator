package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegerKeysWordsKeysParser implements KeysParser {

  @Override
  public ImmutableList<ImmutableList<Integer>> parseKeys(ImmutableList<Word> words) {
    ImmutableList.Builder<ImmutableList<Integer>> builder = ImmutableList.builder();
    words.forEach(w -> builder.add(getFormatKeys(w)));
    return builder.build();
  }

  private ImmutableList<Integer> getFormatKeys(Word word) {
    String digitsStr = getDigits(word.getValue());
    ImmutableList.Builder<Integer> keys = ImmutableList.builder();
    for (char c : digitsStr.toCharArray()) {
      keys.add(c - '0');
    }
    return keys.build();
  }

  private String getDigits(String s) {
    Matcher matcher = Pattern.compile("\\d+$").matcher(s);
    if (matcher.find()) return matcher.group(0);
    return "";
  }
}
