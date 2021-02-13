package com.adesormi.ankicardsgenerator.format;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.fields.Field;
import com.adesormi.ankicardsgenerator.fields.Word;
import com.google.common.collect.ImmutableList;

public class CardFormatter {

  private final ImmutableList<Color> colors;
  private final ImmutableList<Font> fonts;

  public CardFormatter(ImmutableList<Color> colors, ImmutableList<Font> fonts) {
    this.colors = colors;
    this.fonts = fonts;
  }

  public void formatCard(Card card) {
    card.getFields().forEach(field -> formatField(field, card.getMasterField().getKeys()));
  }

  private void formatField(Field field, ImmutableList<ImmutableList<Integer>> keys) {
    if (field.isImmutable()) return;
    ImmutableList<Word> words = field.getWords();
    for (int i = 0; i < keys.size(); ++i) {
      formatWord(words.get(i), keys.get(i));
    }
  }

  private void formatWord(Word word, ImmutableList<Integer> keys) {
    keys.forEach(key -> {
      word.setForm(fonts.get(key));
      word.setColor(colors.get(key));
    });
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CardFormatter)) return false;
    CardFormatter that = (CardFormatter) o;
    if (colors.size() != that.colors.size() || fonts.size() != that.fonts.size()) return false;
    for (int i = 0; i < colors.size(); ++i) {
      if (colors.get(i) != that.colors.get(i)) return false;
      if (fonts.get(i) != that.fonts.get(i)) return false;
    }
    return true;
  }
}
