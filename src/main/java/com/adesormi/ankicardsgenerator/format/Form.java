package com.adesormi.ankicardsgenerator.format;

public enum Form implements FormattingKey {

  // TODO(adesormi): replace bold, italic and underline with proper formatting

  NONE("", ""),
  CIRCLE("<b>", "</b>"),
  RECTANGLE("<i>", "</i>"),
  TOP_LINE("<u>", "</u>");

  private String openingTag;
  private String closingTag;

  Form(String openingTag, String closingTag) {
    this.openingTag = openingTag;
    this.closingTag = closingTag;
  }

  public static Form getFormFromString(String formName) {
    if (formName == null) formName = "";
    String name = formName.trim().toUpperCase().replace('-', '_');
    try {
      return name.isEmpty() ? NONE : valueOf(name);
    } catch(IllegalArgumentException iaE) {
      throw new InvalidFormException();
    }
  }

  @Override
  public String openingTag() { return openingTag; }

  @Override
  public String closingTag() { return closingTag; }

}
