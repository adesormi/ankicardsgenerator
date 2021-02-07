package com.adesormi.ankicardsgenerator.configuration;

import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Font;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.TestUtil.testFile;
import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.google.common.truth.Truth.assertThat;


public class ConfigurationHandlerTest {

  private static final CardFactory CHINESE_CARD_FACTORY =
           new CardFactory(               2,
                                          ImmutableList.of(0),
                                          ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  private static final CardFactory VIETNAMESE_CARD_FACTORY =
           new CardFactory(               1,
                                          ImmutableList.of(0),
                                          ImmutableList.of(ENGLISH, VIETNAMESE_VNI, VIETNAMESE));

  private static final CardFormatter ONE_KEY_NO_COLOR_NO_FONT_CARD_FORMATTER =
      new CardFormatter(
          ImmutableList.of(Color.NONE, Color.NONE), ImmutableList.of(Font.NONE, Font.NONE));
  private static final CardFormatter TWO_KEYS_NONE_RED_COLORS_BOLD_NONE_FONTS_CARD_FORMATTER =
      new CardFormatter(
          ImmutableList.of(Color.NONE, Color.NONE, Color.RED),
          ImmutableList.of(Font.NONE, Font.BOLD, Font.NONE)
      );

  private ConfigurationHandler configurationHandler;

  @BeforeEach
  void setUp() {
    configurationHandler = new ConfigurationHandler();
  }

  @Test
  void updateConfiguration_propertiesWithNoFields_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("noFields.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWithInvalidFieldType_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("invalidFieldType.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWithNoMasterField_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("noMasterField.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWithInvalidMasterFieldIndex_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("invalidMasterFieldIndex.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWithInvalidImmutableFieldIndex_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("invalidImmutableFieldIndex.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_chineseProperties_success() {
    assertThat(configurationHandler.updateConfiguration(testFile("chinese.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFactory()).isEqualTo(CHINESE_CARD_FACTORY);
  }

  @Test
  void updateConfiguration_vietnameseProperties_success() {
    assertThat(configurationHandler.updateConfiguration(testFile("vietnamese.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFactory()).isEqualTo(VIETNAMESE_CARD_FACTORY);
  }

  @Test
  void updateConfiguration_propertiesWithNoNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("noNumberOfKeys.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWithNaNAsNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("notANumberAsNumberOfKeys.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWithNegativeNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("negativeNumberOfKeys.properties"))).isFalse();
  }

  @Test
  public void updateConfiguration_propertiesWithZeroAsNumberOfKeys_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("zeroAsNumberOfKeys.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWith1KeyNoColorAndNoFont_success() {
    assertThat(configurationHandler.updateConfiguration(testFile("1KeyNoColorNoFont.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FONT_CARD_FORMATTER);
  }

  @Test
  void updateConfiguration_propertiesWith1KeyEmptyColorsEmptyFonts_success() {
    assertThat(configurationHandler.updateConfiguration(testFile(
        "1KeyEmptyColorsEmptyFonts.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FONT_CARD_FORMATTER);
  }

  @Test
  public void updateConfiguration_propertiesWithInvalidColor_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("invalidColor.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWithInvalidFont_returnsFalse() {
    assertThat(configurationHandler.updateConfiguration(testFile("invalidFont.properties"))).isFalse();
  }

  @Test
  void updateConfiguration_propertiesWith2KeysNoneRedColorsBoldNoneFonts_success() {
    assertThat(configurationHandler.updateConfiguration(testFile("2KeysNoneRedColorsBoldNoneFonts.properties"))).isTrue();
    Configuration configuration = configurationHandler.loadConfiguration();

    assertThat(configuration.getCardFormatter())
        .isEqualTo(TWO_KEYS_NONE_RED_COLORS_BOLD_NONE_FONTS_CARD_FORMATTER);
  }
}
