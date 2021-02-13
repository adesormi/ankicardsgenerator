package com.adesormi.ankicardsgenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ExceptionHandlerTest {

  private ExceptionHandler exceptionHandler;

  @BeforeEach
  void setUp() {
    exceptionHandler = new ExceptionHandler();
  }

  @Test
  void showHelp_success() {
    assertDoesNotThrow(
        () -> exceptionHandler.showHelp()
    );
  }
}
