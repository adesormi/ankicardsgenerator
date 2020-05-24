package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Form;

import java.util.Objects;

public class Word {

  private String value;

  public Word(String value) {
    this.value = value;
  }

  public String getValue() { return value; }

  public void setColor(Color color) {
    value = color.openingTag() + value + color.closingTag();
  }

  public void setForm(Form form) {
    value = form.openingTag() + value + form.closingTag();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Word)) return false;
    Word word = (Word) o;
    return Objects.equals(value, word.value);
  }
}
