package com.adesormi.ankicardsgenerator.cli;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.TestUtil.testFile;
import static com.adesormi.ankicardsgenerator.TestUtil.testFileAsStr;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandLineHandlerTest {

  private static final String VALID_CONFIG_FILE = "config.properties";
  private static final String VALID_WORKING_DIR = "workingDir";

  private CommandLineHandler cliHandler;

  @BeforeEach
  void setUp() {
    cliHandler = new CommandLineHandler();
  }

  @Test
  void parseInput_invalidArgs_throwsInvalidArgsException() {
    assertThrows(
        InvalidArgsException.class,
        () -> cliHandler.parseInput(new String[]{"-i", "invalidArg"})
    );
  }

  @Test
  void parseInput_missingConfigurationOption_throwsInvalidArgsException() {
    assertThrows(
        InvalidArgsException.class,
        () -> cliHandler.parseInput(new String[]{"-w", "workingdir"})
    );
  }

  @Test
  void parseInput_missingWorkingDirOption_throwsInvalidArgsException() {
    assertThrows(
        InvalidArgsException.class,
        () -> cliHandler.parseInput(new String[]{"-c", "config"})
    );
  }

  @Test
  void parseInput_shortFormUsed_success() {
    Input input = cliHandler.parseInput(new String[]{"-c", testFileAsStr(VALID_CONFIG_FILE), "-w", testFileAsStr(VALID_WORKING_DIR)});

    assertThat(input.getConfigFilePath().toAbsolutePath().toString())
        .isEqualTo(testFile(VALID_CONFIG_FILE).toAbsolutePath().toString());
    assertThat(input.getWorkingDirPath().toAbsolutePath().toString())
        .isEqualTo(testFile(VALID_WORKING_DIR).toAbsolutePath().toString());
  }

  @Test
  void parseInput_longFormUsed_success() {
    Input input = cliHandler.parseInput(new String[]{"--working_dir", testFileAsStr(VALID_WORKING_DIR), "--configuration", testFileAsStr(VALID_CONFIG_FILE)});

    assertThat(input.getConfigFilePath().toAbsolutePath().toString())
        .isEqualTo(testFile(VALID_CONFIG_FILE).toAbsolutePath().toString());
    assertThat(input.getWorkingDirPath().toAbsolutePath().toString())
        .isEqualTo(testFile(VALID_WORKING_DIR).toAbsolutePath().toString());
  }
}
