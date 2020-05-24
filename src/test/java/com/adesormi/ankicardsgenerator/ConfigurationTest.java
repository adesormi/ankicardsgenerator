package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.Configuration.*;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.fields.FieldType.*;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Color.InvalidColorException;
import com.adesormi.ankicardsgenerator.format.Form;
import com.adesormi.ankicardsgenerator.format.Form.InvalidFormException;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ConfigurationTest {

  private static final CardFactory CHINESE_CARD_FACTORY =
      new CardFactory(2, ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(1, ImmutableList.of(ENGLISH, VIETNAMESE_VNI, VIETNAMESE));

  private static final CardFormatter ONE_KEY_NO_COLOR_NO_FORM_CARD_FORMATTER =
      new CardFormatter(ImmutableList.of(Color.NONE, Color.NONE), ImmutableList.of(Form.NONE, Form.NONE));
  private static final CardFormatter TWO_KEYS_NONE_RED_COLORS_CIRCLE_NONE_FORMS_CARD_FORMATTER =
      new CardFormatter(
          ImmutableList.of(Color.NONE, Color.NONE, Color.RED),
          ImmutableList.of(Form.NONE, Form.CIRCLE, Form.NONE)
      );

  private Configuration configuration;

  @Test(expected = MissingConfigurationException.class)
  public void constructor_noProperties_throwMissingConfigurationException() {
    configuration = new Configuration("");
  }

  @Test(expected = MissingFieldsException.class)
  public void constructor_propertiesWithNoFields_throwMissingFieldsException() {
    configuration = new Configuration("test/resources/noFields.properties");
  }

  @Test(expected = InvalidFieldTypeException.class)
  public void constructor_propertiesWithInvalidFieldType_throwInvalidFieldTypeException() {
    configuration = new Configuration("test/resources/invalidFieldType.properties");
  }

  @Test(expected = MissingMasterFieldException.class)
  public void constructor_propertiesWithNoMasterField_throwMissingMasterFieldException() {
    configuration = new Configuration("test/resources/noMasterField.properties");
  }

  @Test(expected = InvalidMasterFieldIndexException.class)
  public void constructor_propertiesWithInvalidMasterFieldIndex_throwInvalidMasterFieldIndexException() {
    configuration = new Configuration("test/resources/invalidMasterFieldIndex.properties");
  }

  @Test
  public void constructor_chineseProperties_success() {
    configuration = new Configuration("test/resources/chinese.properties");

    assertThat(configuration.getCardFactory()).isEqualTo(CHINESE_CARD_FACTORY);
  }

  @Test
  public void constructor_vietnameseProperties_success() {
    configuration = new Configuration("test/resources/vietnamese.properties");

    assertThat(configuration.getCardFactory()).isEqualTo(VIETNAMESE_CARD_FACTORY);
  }

  @Test(expected = MissingNumberOfKeysException.class)
  public void constructor_propertiesWithNoNumberOfKeys_throwMissingNumberOfKeysException() {
    configuration = new Configuration("test/resources/noNumberOfKeys.properties");
  }

  @Test(expected = InvalidNumberOfKeysException.class)
  public void constructor_propertiesWithNaNAsNumberOfKeys_throwInvalidNumberOfKeysException() {
    configuration = new Configuration("test/resources/notANumberAsNumberOfKeys.properties");
  }

  @Test(expected = InvalidNumberOfKeysException.class)
  public void constructor_propertiesWithNegativeNumberOfKeys_throwInvalidNumberOfKeysException() {
    configuration = new Configuration("test/resources/negativeNumberOfKeys.properties");
  }

  @Test(expected = InvalidNumberOfKeysException.class)
  public void constructor_propertiesWithZeroAsNumberOfKeys_throwInvalidNumberOfKeysException() {
    configuration = new Configuration("test/resources/zeroAsNumberOfKeys.properties");
  }

  @Test
  public void constructor_propertiesWith1KeyNoColorsAndNoForms_success() {
    configuration = new Configuration("test/resources/1KeyNoColorsNoForms.properties");

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FORM_CARD_FORMATTER);
  }

  @Test
  public void constructor_propertiesWith1KeyEmptyColorsEmptyForms_success() {
    configuration = new Configuration("test/resources/1KeyEmptyColorsEmptyForms.properties");

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FORM_CARD_FORMATTER);
  }

  @Test(expected = InvalidColorException.class)
  public void constructor_propertiesWithInvalidColor_throwInvalidColorException() {
    configuration = new Configuration("test/resources/invalidColor.properties");
  }

  @Test(expected = InvalidFormException.class)
  public void constructor_propertiesWithInvalidForm_throwInvalidFormException() {
    configuration = new Configuration("test/resources/invalidForm.properties");
  }

  @Test
  public void constructor_propertiesWith2KeysNoneRedColorsCircleNoneForms_success() {
    configuration =
        new Configuration("test/resources/2KeysNoneRedColorsCircleNoneForms.properties");

    assertThat(configuration.getCardFormatter())
        .isEqualTo(TWO_KEYS_NONE_RED_COLORS_CIRCLE_NONE_FORMS_CARD_FORMATTER);
  }
}
