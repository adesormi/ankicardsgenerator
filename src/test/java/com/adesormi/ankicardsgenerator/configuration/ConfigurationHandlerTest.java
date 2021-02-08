package com.adesormi.ankicardsgenerator.configuration;

import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.fields.InvalidFieldTypeException;
import com.adesormi.ankicardsgenerator.format.*;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.TestUtil.testFile;
import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
  void loadConfiguration_propertiesWithNoFields_throwsMissingFieldException() {
    assertThrows(
      MissingFieldsException.class,
      () -> configurationHandler.loadConfiguration(testFile("noFields.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithInvalidFieldType_throwsInvalidFieldTypeException() {
    assertThrows(
        InvalidFieldTypeException.class,
        () -> configurationHandler.loadConfiguration(
            testFile("invalidFieldType.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithNoMasterField_throwsMissingMasterFieldException() {
    assertThrows(
        MissingMasterFieldException.class,
        () -> configurationHandler.loadConfiguration(testFile("noMasterField.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithInvalidMasterFieldIndex_throwsInvalidMasterFieldIndexException() {
    assertThrows(
        InvalidMasterFieldIndexException.class,
        () -> configurationHandler.loadConfiguration(
            testFile("invalidMasterFieldIndex.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithInvalidImmutableFieldIndex_throwsInvalidImmutableFieldIndexException() {
    assertThrows(
        InvalidImmutableFieldIndexException.class,
        () -> configurationHandler.loadConfiguration(testFile("invalidImmutableFieldIndex.properties"))
    );
  }

  @Test
  void loadConfiguration_chineseProperties_success() {
    Configuration configuration =
        configurationHandler.loadConfiguration(testFile("chinese.properties"));

    assertThat(configuration.getCardFactory()).isEqualTo(CHINESE_CARD_FACTORY);
  }

  @Test
  void loadConfiguration_vietnameseProperties_success() {
    Configuration configuration =
        configurationHandler.loadConfiguration(testFile("vietnamese.properties"));

    assertThat(configuration.getCardFactory()).isEqualTo(VIETNAMESE_CARD_FACTORY);
  }

  @Test
  void loadConfiguration_propertiesWithNoNumberOfKeys_throwsMissingNumberOfKeysException() {
    assertThrows(
        MissingNumberOfKeysException.class,
        () -> configurationHandler.loadConfiguration(testFile("noNumberOfKeys.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithNaNAsNumberOfKeys_throwsInvalidNumberOfKeysException() {
    assertThrows(
        InvalidNumberOfKeysException.class,
        () -> configurationHandler.loadConfiguration(
            testFile("notANumberAsNumberOfKeys.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithNegativeNumberOfKeys_throwsInvalidNumberOfKeysException() {
    assertThrows(
        InvalidNumberOfKeysException.class,
        () -> configurationHandler.loadConfiguration(
            testFile("negativeNumberOfKeys.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithZeroAsNumberOfKeys_throwsInvalidNumberOfKeysException() {
    assertThrows(
        InvalidNumberOfKeysException.class,
        () -> configurationHandler.loadConfiguration(
            testFile("zeroAsNumberOfKeys.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWith1KeyNoColorAndNoFont_success() {
    Configuration configuration =
        configurationHandler.loadConfiguration(testFile("1KeyNoColorNoFont.properties"));

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FONT_CARD_FORMATTER);
  }

  @Test
  void loadConfiguration_propertiesWith1KeyEmptyColorsEmptyFonts_success() {
    Configuration configuration =
        configurationHandler.loadConfiguration(
            testFile("1KeyEmptyColorsEmptyFonts.properties"));

    assertThat(configuration.getCardFormatter()).isEqualTo(ONE_KEY_NO_COLOR_NO_FONT_CARD_FORMATTER);
  }

  @Test
  void loadConfiguration_propertiesWithInvalidColor_throwsInvalidColorException() {
    assertThrows(
        InvalidColorException.class,
        () -> configurationHandler.loadConfiguration(testFile("invalidColor.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWithInvalidFont_throwsInvalidFontException() {
    assertThrows(
        InvalidFontException.class,
        () -> configurationHandler.loadConfiguration(testFile("invalidFont.properties"))
    );
  }

  @Test
  void loadConfiguration_propertiesWith2KeysNoneRedColorsBoldNoneFonts_success() {
    Configuration configuration =
        configurationHandler.loadConfiguration(
            testFile("2KeysNoneRedColorsBoldNoneFonts.properties"));

    assertThat(configuration.getCardFormatter())
        .isEqualTo(TWO_KEYS_NONE_RED_COLORS_BOLD_NONE_FONTS_CARD_FORMATTER);
  }
}
