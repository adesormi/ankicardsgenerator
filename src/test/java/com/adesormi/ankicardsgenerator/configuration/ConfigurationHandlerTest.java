package com.adesormi.ankicardsgenerator.configuration;

import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Form;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ConfigurationHandlerTest {

  private static final CardFactory CHINESE_CARD_FACTORY =
      new CardFactory(2, ImmutableList.of(0), ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(
          1, ImmutableList.of(0), ImmutableList.of(ENGLISH, VIETNAMESE_VNI, VIETNAMESE));

  private static final CardFormatter ONE_KEY_NO_COLOR_NO_FORM_CARD_FORMATTER =
      new CardFormatter(
          ImmutableList.of(Color.NONE, Color.NONE), ImmutableList.of(Form.NONE, Form.NONE));
  private static final CardFormatter TWO_KEYS_NONE_RED_COLORS_CIRCLE_NONE_FORMS_CARD_FORMATTER =
      new CardFormatter(
          ImmutableList.of(Color.NONE, Color.NONE, Color.RED),
          ImmutableList.of(Form.NONE, Form.CIRCLE, Form.NONE)
      );

  private ConfigurationHandler configurationHandler;

  @Before
  public void setUp() {
    configurationHandler = new ConfigurationHandler();
  }

  @Test
  public void updateConfiguration_propertiesWithNoFields_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("noFields.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithInvalidFieldType_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("invalidFieldType.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithNoMasterField_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("noMasterField.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithInvalidMasterFieldIndex_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("invalidMasterFieldIndex.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithInvalidImmutableFieldIndex_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("invalidImmutableFieldIndex.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_chineseProperties_success() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("chinese.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFactory()).isEqualTo(CHINESE_CARD_FACTORY);
  }

  @Test
  public void updateConfiguration_vietnameseProperties_success() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("vietnamese.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFactory()).isEqualTo(VIETNAMESE_CARD_FACTORY);
  }

  @Test
  public void updateConfiguration_propertiesWithNoNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("noNumberOfKeys.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithNaNAsNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("notANumberAsNumberOfKeys.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithNegativeNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("negativeNumberOfKeys.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithZeroAsNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("zeroAsNumberOfKeys.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWith1KeyNoColorsAndNoForms_success() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("1KeyNoColorsNoForms.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FORM_CARD_FORMATTER);
  }

  @Test
  public void updateConfiguration_propertiesWith1KeyEmptyColorsEmptyForms_success() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("1KeyEmptyColorsEmptyForms.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FORM_CARD_FORMATTER);
  }

  @Test
  public void updateConfiguration_propertiesWithInvalidColor_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("invalidColor.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithInvalidForm_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("invalidForm.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWith2KeysNoneRedColorsCircleNoneForms_success() {
    assertThat(configurationHandler.updateConfiguration(pathToTestFile("2KeysNoneRedColorsCircleNoneForms.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFormatter())
        .isEqualTo(TWO_KEYS_NONE_RED_COLORS_CIRCLE_NONE_FORMS_CARD_FORMATTER);
  }

  private Path pathToTestFile(String testFile) {
    return Paths.get("src/test/resources/", testFile);
  }
}
