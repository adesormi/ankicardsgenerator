package com.adesormi.ankicardsgenerator.format;

public enum Font implements FormattingKey {

  NONE("", ""),
  BOLD("<b>", "</b>"),
  ITALIC("<i>", "</i>"),
  UNDERLINE("<u>", "</u>");

  private final String openingTag;
  private final String closingTag;

  Font(String openingTag, String closingTag) {
    this.openingTag = openingTag;
    this.closingTag = closingTag;
  }

  public static Font getFontFromString(String fontName) {
    if (fontName == null) fontName = "";
    String name = fontName.trim().toUpperCase().replace('-', '_');
    try {
      return name.isEmpty() ? NONE : valueOf(name);
    } catch(IllegalArgumentException iaE) {
      throw new InvalidFontException();
    }
  }

  @Override
  public String openingTag() { return openingTag; }

  @Override
  public String closingTag() { return closingTag; }

}
