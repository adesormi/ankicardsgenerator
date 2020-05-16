package com.adesormi.ankicardsgenerator.format;

public enum Color {
  NONE,
  BLUE,
  CYAN,
  GREEN,
  ORANGE,
  RED;

  public static Color getColorFromString(String colorName) {
    if(colorName == null) colorName = "";
    String name = colorName.trim().toUpperCase();
    try {
      return name.isEmpty() ? NONE : valueOf(name);
    } catch(IllegalArgumentException iaE) {
      throw new InvalidColorException();
    }
  }

  public static class InvalidColorException extends RuntimeException {}
}
