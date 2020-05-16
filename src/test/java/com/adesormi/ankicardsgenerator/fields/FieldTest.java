package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.fields.Field.InvalidValueException;
import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class FieldTest {

  private static final ImmutableList<Integer> COLOR_KEYS = ImmutableList.of(1, 2);
  private static final String VALUE = "word1 word2";

  private Field field;

  @Before
  public void setUp() {
    field = new ChinesePinyinField(VALUE);
  }

  @Test(expected = InvalidValueException.class)
  public void constructor_nullValue_throwInvalidValueException() {
    new ChineseHanziField(null);
  }

  @Test(expected = InvalidValueException.class)
  public void parseValueIntoWords_emptyValue_throwInvalidValueException() {
    new ChineseHanziField("");
  }

  @Test
  public void colorWords_isImmutable_wordsAreNotColored() {
    field.setImmutable(true);

    field.colorWords(COLOR_KEYS);

    assertThat(field.getWords().get(0).getKey()).isEqualTo(0);
    assertThat(field.getWords().get(1).getKey()).isEqualTo(0);
  }

  @Test
  public void colorWords_colorKeysMapContains1And2_wordsAreColoredWith1And2() {
    field.colorWords(COLOR_KEYS);

    assertThat(field.getWords().get(0).getKey()).isEqualTo(1);
    assertThat(field.getWords().get(1).getKey()).isEqualTo(2);
  }
}
