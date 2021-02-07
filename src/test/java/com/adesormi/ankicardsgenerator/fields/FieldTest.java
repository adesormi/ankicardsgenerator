package com.adesormi.ankicardsgenerator.fields;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FieldTest {

  private static final String VALUE = "word1 word2";
  private static final ImmutableList<Word> WORDS = ImmutableList.of(
      new Word("word1"),
      new Word("word2")
  );
  private static final ImmutableList<Integer> KEYS = ImmutableList.of(1, 2);

  @Test
  void constructor_nullValue_throwInvalidValueException() {
    assertThrows(
        InvalidFieldValueException.class,
        () -> new Field(CHINESE, null)
    );
  }

  @Test
  void constructor_emptyValue_throwInvalidValueException() {
    assertThrows(
        InvalidFieldValueException.class,
        () -> new Field(FRENCH, "")
    );
  }

  @Test
  void constructor_valueWith2WordsAndKeys1And2_twoWordsTwoKeys() {
    Field field = new Field(CHINESE_PINYIN, VALUE);

    assertThat(field.getWords()).isEqualTo(WORDS);
    assertThat(field.getKeys()).isEqualTo(KEYS);
  }
}
