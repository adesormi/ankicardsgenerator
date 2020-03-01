package com.adesormi.ankicardsgenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.FieldParser.getColorsKey;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class FieldParserTest {

  private static final int DEFAULT_COLOR_INDEX = 0;
  private static final int COLOR1_INDEX = 1;
  private static final int COLOR2_INDEX = 2;

  @Test
  public void getColorsKey_emptyValue_emptyMap() {
    assertThat(getColorsKey(/* fieldValue= */ "")).isEmpty();
  }

  @Test
  public void getColorsKey_1WordWithNoKey_colorsKey0IsDefaultColor() {
    int[] colorsKeys = getColorsKey(/* fieldValue= */ "wordWithNoKey");

    assertThat(colorsKeys).hasLength(1);
    assertThat(colorsKeys[0]).isEqualTo(DEFAULT_COLOR_INDEX);
  }

  @Test
  public void getColorsKey_1WordWithKey1_colorsKey0IsColor1() {
    int[] colorsKeys = getColorsKey(/* fieldValue= */ "wordWithKey1");

    assertThat(colorsKeys).hasLength(1);
    assertThat(colorsKeys[0]).isEqualTo(COLOR1_INDEX);
  }

  @Test
  public void getColorsKey_wordsWithKeys1And2_colorsKey0IsColor1colorsKey1IsColor2() {
    int[] colorsKeys = getColorsKey(/* fieldValue= */ "wordWithKey1 wordWithKey2");

    assertThat(colorsKeys).hasLength(2);
    assertThat(colorsKeys[0]).isEqualTo(COLOR1_INDEX);
    assertThat(colorsKeys[1]).isEqualTo(COLOR2_INDEX);
  }
}
