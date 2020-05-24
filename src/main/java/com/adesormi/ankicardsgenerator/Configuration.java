package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.fields.FieldType;
import com.adesormi.ankicardsgenerator.format.CardFormatter;
import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Form;
import com.google.common.collect.ImmutableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

import static com.adesormi.ankicardsgenerator.Constants.COMMA_SEPARATOR;
import static com.adesormi.ankicardsgenerator.Constants.ROOT_PATH;

public class Configuration {

  private static final String FIELDS_PROPERTY = "fields";
  private static final String MASTER_FIELD_PROPERTY = "master";
  private static final String IMMUTABLE_FIELDS_PROPERTY = "immutable";
  private static final String NUMBER_OF_KEYS_PROPERTY = "number_of_keys";
  private static final String COLORS_PROPERTY = "colors";
  private static final String FORMS_PROPERTY = "forms";

  private Properties properties;
  private int numberOfKeys;

  private CardFactory cardFactory;
  private CardFormatter cardFormatter;

  public Configuration(String propertiesFileName) {
    loadProperties(propertiesFileName);
    setupConfiguration();
  }

  public CardFactory getCardFactory() {
    return cardFactory;
  }

  public CardFormatter getCardFormatter() { return cardFormatter; }

  private void loadProperties(String propertiesFileName) {
    Path propertiesFilePath = Paths.get(ROOT_PATH, propertiesFileName);
    properties = new Properties();
    try(FileInputStream in = new FileInputStream(propertiesFilePath.toFile())) {
      properties.load(in);
    } catch(IOException ioException) {
      throw new MissingConfigurationException();
    }
  }

  private void setupConfiguration() {
    setupCardFactory();
    setupCardFormatter();
  }

  private void setupCardFactory() {
    ImmutableList<FieldType> fieldTypes = getFieldTypesFromProperties();
    int masterFieldIndex = getMasterFieldIndexFromProperties();
    ImmutableList<Integer> immutableFieldsIndex = getImmutableFieldsIndexFromProperties();
    cardFactory = new CardFactory(masterFieldIndex, immutableFieldsIndex, fieldTypes);
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
    for(String s : immutableFieldsIndexStr) {
      try {
        builder.add(Integer.parseInt(s) - 1); // -1 for 0 indexed list
      } catch( NumberFormatException nfE) {
        throw new InvalidImmutableFieldIndexException();
      }
    }
    return builder.build();
  }

  private void setupCardFormatter() {
    numberOfKeys = getNumberOfKeysFromProperties();
    ImmutableList<Color> colors = getColorsFromProperties();
    ImmutableList<Form> forms = getFormsFromProperties();
    cardFormatter = new CardFormatter(colors, forms);
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

  private ImmutableList<Form> getFormsFromProperties() {
    String[] formsStr = loadArrayOfStringsFromProperties(FORMS_PROPERTY);
    formsStr = Arrays.copyOf(formsStr, numberOfKeys);
    return parseForms(formsStr);
  }

  private ImmutableList<Form> parseForms(String[] formsStr) {
    ImmutableList.Builder<Form> builder = ImmutableList.builder();
    builder.add(Form.NONE); // add NONE for 0 indexed list
    Arrays.stream(formsStr).forEach(f -> builder.add(Form.getFormFromString(f)));
    return builder.build();
  }

  private String[] loadArrayOfStringsFromProperties(String propertyName) {
    String propertiesStr = properties.getProperty(propertyName);
    if (propertiesStr == null) propertiesStr = "";
    return propertiesStr.trim().split(COMMA_SEPARATOR);
  }

  public static class MissingConfigurationException extends RuntimeException {}

  public static class MissingFieldsException extends RuntimeException {}

  public static class MissingMasterFieldException extends RuntimeException {}

  public static class InvalidMasterFieldIndexException extends RuntimeException {}

  public static class InvalidImmutableFieldIndexException extends RuntimeException {}

  public static class MissingNumberOfKeysException extends RuntimeException {}

  public static class InvalidNumberOfKeysException extends RuntimeException {}
}
