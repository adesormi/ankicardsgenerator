package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.fields.BaseField;
import com.adesormi.ankicardsgenerator.fields.Field;
import com.adesormi.ankicardsgenerator.fields.LogographicField;
import com.adesormi.ankicardsgenerator.fields.RomanizedField;
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
  public void getValue_fieldCreatedWithNullValue_emptyValue() {
    Field fieldWithNullValue = new BaseField(null);
    assertThat(fieldWithNullValue.getValue()).isEmpty();
  }

  @Test
  public void equals_sameValues_true() {
    assertThat(new BaseField("value")).isEqualTo(new BaseField("value"));
  }

  @Test
  public void equals_differentValues_false() {
    assertThat(new BaseField("value")).isNotEqualTo(new BaseField("otherValue"));
  }

  @Test
  public void isColorable_isBaseField_false() {
    assertThat(baseField.isColorable()).isFalse();
  }

  @Test
  public void isColorable_isLogographicField_true() {
    assertThat(logographicField.isColorable()).isTrue();
  }

  @Test
  public void isColorable_isRomanizedField_true() {
    assertThat(romanizedField.isColorable()).isTrue();
  }
}
