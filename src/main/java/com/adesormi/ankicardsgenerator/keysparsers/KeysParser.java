package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.Word;
import com.google.common.collect.ImmutableList;

public interface KeysParser {

  ImmutableList<Integer> parseKeys(ImmutableList<Word> words);
}
