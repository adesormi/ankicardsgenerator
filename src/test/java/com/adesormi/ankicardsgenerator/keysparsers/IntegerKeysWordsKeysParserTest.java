package com.adesormi.ankicardsgenerator.keysparsers;

import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class IntegerKeysWordsKeysParserTest {

  private static final Word WORD_WITH_KEY1 = new Word("word1");
  private static final Word WORD_WITH_KEY2 = new Word("word2");

  private IntegerKeysWordsKeysParser keysParser;

  @Before
  public void setUp() {
    keysParser = new IntegerKeysWordsKeysParser();
  }

  @Test
  public void parseKeys_noWord_mapIsEmpty() {
    assertThat(keysParser.parseKeys(ImmutableList.of())).isEmpty();
  }

  @Test
  public void parseKeys_2WordsWithKeys1And2_mapWithKeys1And2() {
    assertThat(keysParser.parseKeys(ImmutableList.of(WORD_WITH_KEY1, WORD_WITH_KEY2)))
        .containsExactly(1, 2);
  }
}