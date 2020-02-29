package com.adesormi.ankicardsgenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class FieldParserTest {

  private static final int DEFAULT_COLOR_INDEX = 0;
  private static final int COLOR1_INDEX = 1;
  private static final int COLOR2_INDEX = 2;

  private FieldParser fieldParser;

  @Before
  public void setUp() {
    fieldParser = FieldParser.getInstance();
  }

  @Test
  public void generateColorIndexMap_emptyValue_emptyMap() {
    assertThat(fieldParser.generateColorIndexMap(/* fieldValue= */ ""))
        .isEmpty();
  }

  @Test
  public void generateColorIndexMap_1WordWithNoKey_word0MappedToDefaultColor() {
    assertThat(fieldParser.generateColorIndexMap(/* fieldValue= */ "wordWithNoKey"))
        .containsEntry(0, DEFAULT_COLOR_INDEX);
  }

  @Test
  public void generateColorIndexMap_1WordWithKey1_word0MappedToColor1() {
    assertThat(fieldParser.generateColorIndexMap(/* fieldValue= */ "wordWithKey1"))
        .containsEntry(0, COLOR1_INDEX);
  }

  @Test
  public void generateColorIndexMap_wordsWithKeys1And2_word0MappedToColor1AndWord1MappedToColor2() {
    assertThat(fieldParser.generateColorIndexMap(/* fieldValue= */ "wordWithKey1 wordWithKey2"))
        .containsExactly(0, COLOR1_INDEX, 1, COLOR2_INDEX);
  }
}
