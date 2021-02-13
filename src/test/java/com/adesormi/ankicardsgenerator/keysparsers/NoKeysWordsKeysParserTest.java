package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;


public class NoKeysWordsKeysParserTest {

  private static final Word WORD_WITH_NO_KEY = new Word("word");
  private static final Word WORD_WITH_KEY1 = new Word("word1");

  private NoKeysWordsKeysParser keysParser;

  @BeforeEach
  void setUp() {
    keysParser = new NoKeysWordsKeysParser();
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
  void parseKeys_wordWithKey1_listWith1EmptyList() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_KEY1)))
        .containsExactly(ImmutableList.of());
  }

  @Test
  void parseKeys_wordWithNoKeyAndWordWithKey1_listWith2EmptyLists() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_NO_KEY, WORD_WITH_KEY1)))
        .containsExactly(ImmutableList.of(), ImmutableList.of());
  }
}
