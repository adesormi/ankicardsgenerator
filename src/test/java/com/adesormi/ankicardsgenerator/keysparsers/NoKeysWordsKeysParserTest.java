package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.Word;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class NoKeysWordsKeysParserTest {

  private static final Word WORD_WITH_NOKEY = new Word("word");
  private static final Word OTHER_WORD_WITH_NOKEY = new Word("otherWord");

  private NoKeysWordsKeysParser keysParser;

  @Before
  public void setUp() {
    keysParser = new NoKeysWordsKeysParser();
  }

  @Test
  public void parseKeys_noWord_mapIsEmpty() {
    assertThat(keysParser.parseKeys(ImmutableList.of())).isEmpty();
  }

  @Test
  public void parseKeys_2WordsWithNoKeys_mapWithKeys0And0() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_NOKEY, OTHER_WORD_WITH_NOKEY)))
        .containsExactly(0, 0);
  }
}