package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.Word;
import com.adesormi.ankicardsgenerator.keysparsers.KeysParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class FrenchFieldTest {

  private static final int COLUMN_INDEX = 0;
  private static final String VALUE = "bonjour";

  @Mock KeysParser keysParser;

  private FrenchField field;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    field = new FrenchField(keysParser, COLUMN_INDEX, VALUE);
  }

  @Test
  public void parseValueIntoWords_valueWith2Characters_2Words() {
    assertThat(field.getWords()).containsExactly(new Word("bonjour"));
  }
}
