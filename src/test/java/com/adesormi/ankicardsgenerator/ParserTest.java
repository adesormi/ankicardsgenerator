package com.adesormi.ankicardsgenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.Color.*;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ParserTest {

  @Test
  public void givenEmptyValue_whenGetColorsMap_thenEmptyMap() {
    RomanizedField field = new RomanizedField("");
    assertThat(field.getColorsMap()).isEmpty();
  }

  @Test
  public void given1WordWithNoKey_whenGetColorsMap_thenWordMappedToBlack() {
    RomanizedField field = new RomanizedField("wordWithNoKey");
    assertThat(field.getColorsMap()).containsEntry(0, BLACK);
  }

  @Test
  public void given1WordWithKey1_whenGetColorsMap_thenWordMappedToBlue() {
    RomanizedField field = new RomanizedField("blueWord1");
    assertThat(field.getColorsMap()).containsEntry(0, BLUE);
  }

  @Test
  public void given2WordsWithKeys1And2_whenGetColorsMap_thenWordsMappedToBlueAndGreen() {
    RomanizedField field = new RomanizedField("blueWord1 greenWord2");
    assertThat(field.getColorsMap()).containsExactly(0, BLUE, 1, GREEN);
  }
}
