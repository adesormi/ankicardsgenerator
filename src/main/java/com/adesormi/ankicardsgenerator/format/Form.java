package com.adesormi.ankicardsgenerator.format;

public enum Form {
  NONE,
  CIRCLE,
  RECTANGLE,
  TOP_LINE;

  public static Form getFormFromString(String formName) {
    if(formName == null) formName = "";
    String name = formName.trim().toUpperCase().replace('-', '_');
    try {
      return name.isEmpty() ? NONE : valueOf(name);
    } catch(IllegalArgumentException iaE) {
      throw new InvalidFormException();
    }
  }

  public static class InvalidFormException extends RuntimeException {}
}
