package com.adesormi.ankicardsgenerator.cli;

import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.TestUtil.testFile;
import static com.adesormi.ankicardsgenerator.TestUtil.testFileAsStr;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputTest {

  private static final String VALID_CONFIG_FILE = "config.properties";
  private static final String VALID_WORKING_DIR = "workingDir";

  @Test
  void constructor_success() {
    Input input = new Input(testFileAsStr(VALID_CONFIG_FILE), testFileAsStr(VALID_WORKING_DIR));

    assertThat(input.getConfigFilePath().toAbsolutePath().toString())
        .isEqualTo(testFile(VALID_CONFIG_FILE).toAbsolutePath().toString());
    assertThat(input.getWorkingDirPath().toAbsolutePath().toString())
        .isEqualTo(testFile(VALID_WORKING_DIR).toAbsolutePath().toString());
  }

  @Test
  void constructor_missingConfigFile_throwsInvalidArgsException() {
    assertThrows(
        InvalidArgsException.class,
        () -> new Input(
            testFileAsStr("nonExistingFile.properties"), testFileAsStr(VALID_WORKING_DIR))
    );
  }

  @Test
  void constructor_configFileIsNotAFile_throwsInvalidArgsException() {
    assertThrows(
        InvalidArgsException.class,
        () -> new Input(testFileAsStr("workingDir"), testFileAsStr(VALID_WORKING_DIR))
    );
  }

  @Test
  void constructor_missingWorkingDir_throwsInvalidArgsException() {
    assertThrows(
        InvalidArgsException.class,
        () -> new Input(testFileAsStr(VALID_CONFIG_FILE), testFileAsStr("nonExistingDir"))
    );
  }

  @Test
  void constructor_workingDirIsNotADir_throwsInvalidArgsException() {
    assertThrows(
        InvalidArgsException.class,
        () -> new Input(testFileAsStr(VALID_CONFIG_FILE), testFileAsStr("config.properties"))
    );
  }
}
