package com.adesormi.ankicardsgenerator.fields;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public abstract class ColorableField extends Field {

  ColorableField(String value) {
    super(value);
  }

  public void color(int[] colors) {
    ImmutableList<String> words = ImmutableList.copyOf(
        Splitter.on(" ").omitEmptyStrings().trimResults().split(value));
    value = "";
    for (int i = 0; i < words.size(); ++i) {
      value += colors[i] == 0
          ? words.get(i)
          : "<span color=\"" + colors[i] + "\">" + words.get(i) + "</span>";
    }
  }

  @Override
  protected boolean isColorable() {
    return true;
  }
}
