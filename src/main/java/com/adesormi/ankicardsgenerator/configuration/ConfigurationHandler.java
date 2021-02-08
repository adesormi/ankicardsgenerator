package com.adesormi.ankicardsgenerator.configuration;

import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.fields.FieldType;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Font;
import com.google.common.collect.ImmutableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;

import static com.adesormi.ankicardsgenerator.Constants.COMMA_SEPARATOR;

public class ConfigurationHandler {

  private static final String FIELDS_PROPERTY = "fields";
  private static final String MASTER_FIELD_PROPERTY = "master";
  private static final String IMMUTABLE_FIELDS_PROPERTY = "immutable";
  private static final String NUMBER_OF_KEYS_PROPERTY = "number_of_keys";
  private static final String COLORS_PROPERTY = "colors";
  private static final String FONTS_PROPERTY = "fonts";

  private int numberOfKeys;

  private Configuration configuration;
  private Properties properties;

  public Configuration loadConfiguration(Path configFile) {
    loadProperties(configFile);
    propertiesToConfiguration();
    return configuration;
  }

  private void loadProperties(Path configFile) {
    properties = new Properties();
    try(FileInputStream in = new FileInputStream(configFile.toAbsolutePath().toFile())) {
      properties.load(in);
    } catch(IOException ioException) {
      throw new MissingConfigurationException();
    }
  }

  private void propertiesToConfiguration() {
    CardFactory cardFactory = setupCardFactory();
    CardFormatter cardFormatter = setupCardFormatter();
    configuration = new Configuration(cardFactory, cardFormatter);
  }

  private CardFactory setupCardFactory() {
    ImmutableList<FieldType> fieldTypes = getFieldTypesFromProperties();
    int masterFieldIndex = getMasterFieldIndexFromProperties();
    ImmutableList<Integer> immutableFieldsIndex = getImmutableFieldsIndexFromProperties();
    return new CardFactory(masterFieldIndex, immutableFieldsIndex, fieldTypes);
  }

  private ImmutableList<FieldType> getFieldTypesFromProperties() {
    String[] fieldsStr = loadFieldsAsStringsFromProperties();
    return parseFieldTypes(fieldsStr);
  }

  private String[] loadFieldsAsStringsFromProperties() {
    String fieldsStr = properties.getProperty(FIELDS_PROPERTY);
    if (fieldsStr == null || fieldsStr.isEmpty()) throw new MissingFieldsException();
    return fieldsStr.trim().split(COMMA_SEPARATOR);
  }

  private ImmutableList<FieldType> parseFieldTypes(String[] fieldsStr) {
    ImmutableList.Builder<FieldType> builder = ImmutableList.builder();
    Arrays.stream(fieldsStr).forEach(f -> builder.add(FieldType.getFieldTypeFromString(f.trim())));
    return builder.build();
  }

  private int getMasterFieldIndexFromProperties() {
    String masterFieldIndexStr = loadMasterFieldIndexAsStringFromProperties();
    return parseMasterFieldIndex(masterFieldIndexStr) - 1; // -1 for 0 indexed list
  }

  private String loadMasterFieldIndexAsStringFromProperties() {
    String masterFieldStr = properties.getProperty(MASTER_FIELD_PROPERTY);
    if (masterFieldStr == null || masterFieldStr.isEmpty()) throw new MissingMasterFieldException();
    return masterFieldStr;
  }

  private int parseMasterFieldIndex(String masterFieldIndexStr) {
    try {
      return Integer.parseInt(masterFieldIndexStr.trim());
    } catch(NumberFormatException nfE) {
      throw new InvalidMasterFieldIndexException();
    }
  }

  private ImmutableList<Integer> getImmutableFieldsIndexFromProperties() {
    String[] immutableFieldsIndexStr = loadImmutableFieldsIndexAsStringsFromProperties();
    return parseImmutableFieldsIndex(immutableFieldsIndexStr);
  }

  private String[] loadImmutableFieldsIndexAsStringsFromProperties() {
    String immutableFieldsIndexStr = properties.getProperty(IMMUTABLE_FIELDS_PROPERTY);
    if (immutableFieldsIndexStr == null || immutableFieldsIndexStr.isEmpty()) return new String[]{};
    return immutableFieldsIndexStr.trim().split(COMMA_SEPARATOR);
  }

  private ImmutableList<Integer> parseImmutableFieldsIndex(String[] immutableFieldsIndexStr) {
    ImmutableList.Builder<Integer> builder = ImmutableList.builder();
    for (String s : immutableFieldsIndexStr) {
      try {
        builder.add(Integer.parseInt(s) - 1); // -1 for 0 indexed list
      } catch(NumberFormatException nfE) {
        throw new InvalidImmutableFieldIndexException();
      }
    }
    return builder.build();
  }

  private CardFormatter setupCardFormatter() {
    numberOfKeys = getNumberOfKeysFromProperties();
    ImmutableList<Color> colors = getColorsFromProperties();
    ImmutableList<Font> fonts = getFontsFromProperties();
    return new CardFormatter(colors, fonts);
  }

  private int getNumberOfKeysFromProperties() {
    String numberOfKeysStr = loadNumberOfKeysAsStringFromProperties();
    return parseNumberOfKeys(numberOfKeysStr);
  }

  private String loadNumberOfKeysAsStringFromProperties() {
    String numberOfKeysStr = properties.getProperty(NUMBER_OF_KEYS_PROPERTY);
    if (numberOfKeysStr == null || numberOfKeysStr.isEmpty())
      throw new MissingNumberOfKeysException();
    return numberOfKeysStr;
  }

  private int parseNumberOfKeys(String numberOfKeysStr) {
    try {
      int numberOfKeys = Integer.parseInt(numberOfKeysStr);
      if (numberOfKeys <= 0) throw new InvalidNumberOfKeysException();
      return numberOfKeys;
    } catch(NumberFormatException nfE) {
      throw new InvalidNumberOfKeysException();
    }
  }

  private ImmutableList<Color> getColorsFromProperties() {
    String[] colorsStr = loadArrayOfStringsFromProperties(COLORS_PROPERTY);
    colorsStr = Arrays.copyOf(colorsStr, numberOfKeys);
    return parseColors(colorsStr);
  }

  private ImmutableList<Color> parseColors(String[] colorsStr) {
    ImmutableList.Builder<Color> builder = ImmutableList.builder();
    builder.add(Color.NONE); // add NONE for 0 indexed list
    Arrays.stream(colorsStr).forEach(c -> builder.add(Color.getColorFromString(c)));
    return builder.build();
  }

  private ImmutableList<Font> getFontsFromProperties() {
    String[] fontsStr = loadArrayOfStringsFromProperties(FONTS_PROPERTY);
    fontsStr = Arrays.copyOf(fontsStr, numberOfKeys);
    return parseFonts(fontsStr);
  }

  private ImmutableList<Font> parseFonts(String[] fontsStr) {
    ImmutableList.Builder<Font> builder = ImmutableList.builder();
    builder.add(Font.NONE); // add NONE for 0 indexed list
    Arrays.stream(fontsStr).forEach(f -> builder.add(Font.getFontFromString(f)));
    return builder.build();
  }

  private String[] loadArrayOfStringsFromProperties(String propertyName) {
    String propertiesStr = properties.getProperty(propertyName);
    if (propertiesStr == null) propertiesStr = "";
    return propertiesStr.trim().split(COMMA_SEPARATOR);
  }
}
