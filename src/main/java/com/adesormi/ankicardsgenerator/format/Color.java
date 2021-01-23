package com.adesormi.ankicardsgenerator.format;

public enum Color implements FormattingKey {

  NONE("", ""),
  BLUE("<span color=\"blue\">", "</span>"),
  CYAN("<span color=\"cyan\">", "</span>"),
  GREEN("<span color=\"green\">", "</span>"),
  ORANGE("<span color=\"orange\">", "</span>"),
  RED("<span color=\"red\">", "</span>");

  private String openingTag;
  private String closingTag;

  Color(String openingTag, String closingTag) {
    this.openingTag = openingTag;
    this.closingTag = closingTag;
  }

  public static Color getColorFromString(String colorName) {
    if (colorName == null) colorName = "";
    String name = colorName.trim().toUpperCase();
    try {
      return name.isEmpty() ? NONE : valueOf(name);
    } catch(IllegalArgumentException iaE) {
      throw new InvalidColorException();
    }
  }

  @Override
  public String openingTag() { return openingTag; }

  @Override
  public String closingTag() { return closingTag; }

}
