package com.adesormi.ankicardsgenerator;

public class InvalidArgsException extends RuntimeException {

  public InvalidArgsException() {}

  public InvalidArgsException(String message) {
    super(message);
  }
}
