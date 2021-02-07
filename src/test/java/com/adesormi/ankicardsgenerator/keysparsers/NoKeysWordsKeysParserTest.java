package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;


public class NoKeysWordsKeysParserTest {

  private static final Word WORD_WITH_NOKEY = new Word("word");
  private static final Word OTHER_WORD_WITH_NOKEY = new Word("otherWord");

  private NoKeysWordsKeysParser keysParser;

  @BeforeEach
  void setUp() {
    keysParser = new NoKeysWordsKeysParser();
  }

  @Test
  void parseKeys_noWord_mapIsEmpty() {
    assertThat(keysParser.parseKeys(ImmutableList.of())).isEmpty();
  }

  @Test
  void parseKeys_2WordsWithNoKeys_mapWithKeys0And0() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_NOKEY, OTHER_WORD_WITH_NOKEY)))
        .containsExactly(0, 0);
  }
}
