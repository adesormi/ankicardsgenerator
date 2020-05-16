package com.adesormi.ankicardsgenerator.format;

import com.google.common.collect.ImmutableList;

public class CardFormatter {

  private int numberOfKeys;
  private ImmutableList<Color> colors;
  private ImmutableList<Form> forms;

  public CardFormatter(int numberOfKeys, ImmutableList<Color> colors, ImmutableList<Form> forms) {
    this.numberOfKeys = numberOfKeys;
    this.colors = colors;
    this.forms = forms;
  }

  public int getNumberOfKeys() {
    return numberOfKeys;
  }

  public ImmutableList<Color> getColors() {
    return colors;
  }

  public ImmutableList<Form> getForms() {
    return forms;
  }
}
