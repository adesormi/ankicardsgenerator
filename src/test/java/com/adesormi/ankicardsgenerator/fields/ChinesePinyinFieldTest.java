package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ChinesePinyinFieldTest {

  private static final String VALUE = "ni3 hao3";

  private ChinesePinyinField field;

  @Before
  public void setUp() {
    field = new ChinesePinyinField(VALUE);
  }

  @Test
  public void parseValueIntoWords_valueWith2Characters_2Words() {
    assertThat(field.getWords()).containsExactly(
        new Word("ni3"),
        new Word("hao3")
    );
  }
}
