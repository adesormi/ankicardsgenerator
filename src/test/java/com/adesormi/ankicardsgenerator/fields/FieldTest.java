package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.fields.Field.InvalidValueException;
import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
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
  private static final int COLUMN_INDEX = 0;
  private static final String VALUE = "word1 word2";

  @Mock KeysParser keysParser;

  private Field field;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    field = new EnglishField(keysParser, COLUMN_INDEX, VALUE);
  }

  @Test(expected = InvalidValueException.class)
  public void constructor_nullValue_throwInvalidValueException() {
    new ChineseHanziField(keysParser, COLUMN_INDEX, null);
  }

  @Test(expected = InvalidValueException.class)
  public void parseValueIntoWords_emptyValue_throwInvalidValueException() {
    new ChineseHanziField(keysParser, COLUMN_INDEX, "");
  }

  @Test
  public void colorWords_isImmutable_wordsAreNotColored() {
    field.setImmutable(true);

    field.colorWords(COLOR_KEYS);

    assertThat(field.getWords().get(0).getColorKey()).isEqualTo(0);
    assertThat(field.getWords().get(1).getColorKey()).isEqualTo(0);
  }

  @Test
  public void colorWords_colorKeysMapContains1And2_wordsAreColoredWith1And2() {
    field.colorWords(COLOR_KEYS);

    assertThat(field.getWords().get(0).getColorKey()).isEqualTo(1);
    assertThat(field.getWords().get(1).getColorKey()).isEqualTo(2);
  }

  @Test
  public void getColorKeysMap_keysParserReturnsAList_returnsSameList() {
    when(keysParser.parseKeys(any())).thenReturn(COLOR_KEYS);

    assertThat(field.getColorKeysMap()).isEqualTo(COLOR_KEYS);
  }
}
