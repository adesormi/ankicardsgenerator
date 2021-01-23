package com.adesormi.ankicardsgenerator.fields;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class FieldTest {

  private static final String VALUE = "word1 word2";
  private static final ImmutableList<Word> WORDS = ImmutableList.of(
      new Word("word1"),
      new Word("word2")
  );
  private static final ImmutableList<Integer> KEYS = ImmutableList.of(1, 2);

  @Test(expected = InvalidFieldValueException.class)
  public void constructor_nullValue_throwInvalidValueException() {
    new Field(CHINESE, null);
  }

  @Test(expected = InvalidFieldValueException.class)
  public void constructor_emptyValue_throwInvalidValueException() {
    new Field(FRENCH, "");
  }

  @Test
  public void constructor_valueWith2WordsAndKeys1And2_twoWordsTwoKeys() {
    Field field = new Field(CHINESE_PINYIN, VALUE);

    assertThat(field.getWords()).isEqualTo(WORDS);
    assertThat(field.getKeys()).isEqualTo(KEYS);
  }
}
