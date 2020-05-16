package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.Configuration.*;
import com.adesormi.ankicardsgenerator.fields.FieldType.*;
import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Color.InvalidColorException;
import com.adesormi.ankicardsgenerator.format.Form;
import com.adesormi.ankicardsgenerator.format.Form.InvalidFormException;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.adesormi.ankicardsgenerator.format.Color.GREEN;
import static com.adesormi.ankicardsgenerator.format.Color.RED;
import static com.adesormi.ankicardsgenerator.format.Form.CIRCLE;
import static com.adesormi.ankicardsgenerator.format.Form.RECTANGLE;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ConfigurationTest {

  private static final CardFactory CHINESE_CARD_FACTORY =
      new CardFactory(3, ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(2, ImmutableList.of(ENGLISH, VIETNAMESE_VNI, VIETNAMESE));

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

    assertThat(configuration.getCardFormatter().getColors()).isEqualTo(ImmutableList.of(Color.NONE));
    assertThat(configuration.getCardFormatter().getForms()).isEqualTo(ImmutableList.of(Form.NONE));
  }

  @Test
  public void constructor_propertiesWith1KeyNoColors_success() {
    configuration = new Configuration("test/resources/1KeyNoColors.properties");

    assertThat(configuration.getCardFormatter().getColors()).isEqualTo(ImmutableList.of(Color.NONE));
  }

  @Test
  public void constructor_propertiesWithEmptyColors_success() {
    configuration = new Configuration("test/resources/1KeyEmptyColors.properties");

    assertThat(configuration.getCardFormatter().getColors()).isEqualTo(ImmutableList.of(Color.NONE));
  }

  @Test(expected = InvalidColorException.class)
  public void constructor_propertiesWithInvalidColor_throwInvalidColorException() {
    configuration = new Configuration("test/resources/invalidColor.properties");
  }

  @Test
  public void constructor_propertiesWith1KeyNoForms_success() {
    configuration = new Configuration("test/resources/1KeyNoForms.properties");

    assertThat(configuration.getCardFormatter().getForms()).isEqualTo(ImmutableList.of(Form.NONE));
  }

  @Test
  public void constructor_propertiesWith1KeyEmptyForms_success() {
    configuration = new Configuration("test/resources/1KeyEmptyForms.properties");

    assertThat(configuration.getCardFormatter().getForms()).isEqualTo(ImmutableList.of(Form.NONE));
  }

  @Test(expected = InvalidFormException.class)
  public void constructor_propertiesWithInvalidForm_throwInvalidFormException() {
    configuration = new Configuration("test/resources/invalidForm.properties");
  }

  @Test
  public void constructor_propertiesWith5KeysRedNoneGreenColors_success() {
    configuration = new Configuration("test/resources/5KeysRedNoneGreenColors.properties");

    assertThat(configuration.getCardFormatter().getColors()).isEqualTo(
        ImmutableList.of(RED, Color.NONE, GREEN, Color.NONE, Color.NONE));
  }

  @Test
  public void constructor_propertiesWith4KeysNoneCircleRectangleForms_success() {
    configuration = new Configuration("test/resources/4KeysNoneCircleRectangleForms.properties");

    assertThat(configuration.getCardFormatter().getForms()).isEqualTo(
        ImmutableList.of(Form.NONE, CIRCLE, RECTANGLE, Form.NONE));
  }

  @Test
  public void constructor_propertiesWith2KeysRedNoneGreenColors_success() {
    configuration = new Configuration("test/resources/2KeysRedNoneGreenColors.properties");

    assertThat(configuration.getCardFormatter().getColors()).isEqualTo(
        ImmutableList.of(RED, Color.NONE));
  }

  @Test
  public void constructor_propertiesWith2KeysNoneCircleRectangleForms_success() {
    configuration = new Configuration("test/resources/2KeysNoneCircleRectangleForms.properties");

    assertThat(configuration.getCardFormatter().getForms()).isEqualTo(
        ImmutableList.of(Form.NONE, CIRCLE));
  }
}
