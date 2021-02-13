package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;


public class IntegerKeysWordsKeysParserTest {

  private static final Word WORD_WITH_NO_KEY = new Word("word");
  private static final Word WORD_WITH_KEY1 = new Word("word1");
  private static final Word WORD_WITH_KEY2 = new Word("word2");
  private static final Word WORD_WITH_KEYS1AND2 = new Word("word12");

  private IntegerKeysWordsKeysParser keysParser;

  @BeforeEach
  void setUp() {
    keysParser = new IntegerKeysWordsKeysParser();
  }

  @Test
  void parseKeys_noWord_listIsEmpty() {
    assertThat(keysParser.parseKeys(ImmutableList.of())).isEmpty();
  }

  @Test
  void parseKeys_wordWithNoKey_listWith1EmptyList() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_NO_KEY)))
        .containsExactly(ImmutableList.of());
  }

  @Test
  void parseKeys_wordWithKey1AndWordWithKey2_listWithListOf1AndListOf2() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_KEY1, WORD_WITH_KEY2)))
        .containsExactly(ImmutableList.of(1), ImmutableList.of(2));
  }

  @Test
  void parseKeys_wordWithKeys1And2_listWithListOf1And2() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_KEYS1AND2)))
        .containsExactly(ImmutableList.of(1, 2));
  }
}
