package com.adesormi.ankicardsgenerator.fields;

import com.adesormi.ankicardsgenerator.format.Color;
import com.adesormi.ankicardsgenerator.format.Form;

import java.util.Objects;

public class Word {

  private final String value;
  private Color color = Color.NONE;
  private Form form = Form.NONE;

  public Word(String value) {
    this.value = value;
  }

  public String getValue() { return value; }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Form getForm() {
    return form;
  }

  public void setForm(Form form) {
    this.form = form;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Word)) return false;
    Word word = (Word) o;
    return Objects.equals(value, word.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
