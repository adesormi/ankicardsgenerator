package com.adesormi.ankicardsgenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class FieldTest {

  private static final String DEFAULT_VALUE = "value";

  private Field baseField;
  private Field romanizedField;
  private Field logographicField;

  @Before
  public void setUp() {
    baseField = new BaseField(DEFAULT_VALUE);
    romanizedField = new RomanizedField(DEFAULT_VALUE);
    logographicField = new LogographicField(DEFAULT_VALUE);
  }

  @Test
  public void givenFieldHasValue_whenGetValue_thenValue() {
    assertThat(baseField.getValue()).isEqualTo(DEFAULT_VALUE);
  }

  @Test
  public void givenFieldIsGivenNullValue_whenGetValue_thenEmptyValue() {
    Field fieldWithNullValue = new BaseField(null);
    assertThat(fieldWithNullValue.getValue()).isEmpty();
  }

  @Test
  public void givenFieldIsBaseField_whenIsColorable_thenFalse() {
    assertThat(baseField.isColorable()).isFalse();
  }

  @Test
  public void givenFieldIsLogographicField_whenIsColorable_thenTrue() {
    assertThat(logographicField.isColorable()).isTrue();
  }

  @Test
  public void givenFieldIsRomanizedField_whenIsColorable_thenTrue() {
    assertThat(romanizedField.isColorable()).isTrue();
  }
}
