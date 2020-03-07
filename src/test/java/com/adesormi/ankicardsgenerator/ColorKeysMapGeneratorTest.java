package com.adesormi.ankicardsgenerator;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.ColorKeysMapGenerator.getColorKeysMap;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ColorKeysMapGeneratorTest {

  private static final Word WORD_WITHOUT_KEY = new Word("word");
  private static final Word WORD_WITH_KEY1 = new Word("word1");
  private static final Word WORD_WITH_KEY2 = new Word("word2");

  @Test
  public void getColors_noWord_emptyMap() {
    assertThat(getColorKeysMap(/* words= */ ImmutableList.of())).isEmpty();
  }

  @Test
  public void getColors_1WordWithNoKey_contains0() {
    assertThat(getColorKeysMap(/* words= */ ImmutableList.of(WORD_WITHOUT_KEY)))
        .containsExactly(0);
  }

  @Test
  public void getColorsKey_1WordWithKey1_contains1() {
    assertThat(getColorKeysMap(/* words= */ ImmutableList.of(WORD_WITH_KEY1)))
        .containsExactly(1);
  }

  @Test
  public void getColorsKey_wordsWithKeys1And2_contains1And2() {
    assertThat(getColorKeysMap(/* words= */ ImmutableList.of(WORD_WITH_KEY1, WORD_WITH_KEY2)))
        .containsExactly(1, 2);
  }
}
