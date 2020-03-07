package com.adesormi.ankicardsgenerator;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class CardTest {

  private static Field MASTER_FIELD_WITH_KEYS;
  private static Field MASTER_FIELD_WITHOUT_KEYS;
  private static Field LATIN_FIELD;
  private static Field LOGOGRAPHIC_FIELD;

  @Before
  public void setUp() throws Exception {
    MASTER_FIELD_WITH_KEYS = new LatinField(true, "master1 master2");
    MASTER_FIELD_WITHOUT_KEYS = new LatinField(true, "masterOne masterTwo");
    LATIN_FIELD = new LatinField(true, "latinOne latinTwo");
    LOGOGRAPHIC_FIELD = new LogographicField(true, "你好");
  }

  @Test
  public void equals_sameFields_true() {
    Card card1 = new Card(MASTER_FIELD_WITH_KEYS, ImmutableList.of(LATIN_FIELD, LOGOGRAPHIC_FIELD));
    Card card2 = new Card(MASTER_FIELD_WITH_KEYS, ImmutableList.of(LATIN_FIELD, LOGOGRAPHIC_FIELD));

    assertThat(card1).isEqualTo(card2);
  }

  @Test
  public void equals_differentFields_false() {
    Card card1 = new Card(MASTER_FIELD_WITH_KEYS, ImmutableList.of(LATIN_FIELD, LOGOGRAPHIC_FIELD));
    Card card2 =
        new Card(MASTER_FIELD_WITHOUT_KEYS, ImmutableList.of(LATIN_FIELD, LOGOGRAPHIC_FIELD));

    assertThat(card1).isNotEqualTo(card2);
  }

  @Test
  public void color_onlyOInColorKeys_allWordsInFieldsHaveColorKey0() {
    Card card =
        new Card(MASTER_FIELD_WITHOUT_KEYS, ImmutableList.of(LATIN_FIELD, LOGOGRAPHIC_FIELD));
    ImmutableList<Integer> expectedColorKeys = ImmutableList.of(0, 0);

    card.mapWordsWithColorKeys();

    verifyWordsInFieldHaveColorKeys(MASTER_FIELD_WITH_KEYS, expectedColorKeys);
    verifyWordsInFieldHaveColorKeys(LATIN_FIELD, expectedColorKeys);
    verifyWordsInFieldHaveColorKeys(LOGOGRAPHIC_FIELD, expectedColorKeys);
  }

  @Test
  public void color_colorKeys1And2_wordsInFieldsHaveColorKeys1And2() {
    Card card = new Card(MASTER_FIELD_WITH_KEYS, ImmutableList.of(LATIN_FIELD, LOGOGRAPHIC_FIELD));
    ImmutableList<Integer> expectedColorKeys = ImmutableList.of(1, 2);

    card.mapWordsWithColorKeys();

    verifyWordsInFieldHaveColorKeys(MASTER_FIELD_WITH_KEYS, expectedColorKeys);
    verifyWordsInFieldHaveColorKeys(LATIN_FIELD, expectedColorKeys);
    verifyWordsInFieldHaveColorKeys(LOGOGRAPHIC_FIELD, expectedColorKeys);
  }

  @Test
  public void color_colorKeys1And2_wordsInFieldsHaveColorKeys1And2ExceptNonColorableField() {
    Field nonColorableField = new LatinField(false, "wordOne wordTwo");
    Card card = new Card(
        MASTER_FIELD_WITH_KEYS,
        ImmutableList.of(LATIN_FIELD, LOGOGRAPHIC_FIELD, nonColorableField));
    ImmutableList<Integer> expectedColorKeys = ImmutableList.of(1, 2);

    card.mapWordsWithColorKeys();

    verifyWordsInFieldHaveColorKeys(MASTER_FIELD_WITH_KEYS, expectedColorKeys);
    verifyWordsInFieldHaveColorKeys(LATIN_FIELD, expectedColorKeys);
    verifyWordsInFieldHaveColorKeys(LOGOGRAPHIC_FIELD, expectedColorKeys);
    verifyWordsInFieldHaveColorKeys(nonColorableField, ImmutableList.of(0, 0));
  }

  private void verifyWordsInFieldHaveColorKeys(Field field, ImmutableList<Integer> colorKeys) {
    ImmutableList<Word> words = field.getWords();
    assertThat(words.size()).isEqualTo(colorKeys.size());
    for (int i = 0; i < colorKeys.size(); i++) {
      assertThat(words.get(i).getColorKey()).isEqualTo(colorKeys.get(i));
    }
  }
}
