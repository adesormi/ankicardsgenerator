package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;

public interface KeysParser {

  ImmutableList<Integer> parseKeys(ImmutableList<Word> words);
}
