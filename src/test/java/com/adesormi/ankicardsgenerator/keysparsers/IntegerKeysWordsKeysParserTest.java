package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;


public class IntegerKeysWordsKeysParserTest {

  private static final Word WORD_WITH_KEY1 = new Word("word1");
  private static final Word WORD_WITH_KEY2 = new Word("word2");

  private IntegerKeysWordsKeysParser keysParser;

  @BeforeEach
  void setUp() {
    keysParser = new IntegerKeysWordsKeysParser();
  }

  @Test
  void parseKeys_noWord_mapIsEmpty() {
    assertThat(keysParser.parseKeys(ImmutableList.of())).isEmpty();
  }

  @Test
  void parseKeys_2WordsWithKeys1And2_mapWithKeys1And2() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_KEY1, WORD_WITH_KEY2)))
        .containsExactly(1, 2);
  }
}
