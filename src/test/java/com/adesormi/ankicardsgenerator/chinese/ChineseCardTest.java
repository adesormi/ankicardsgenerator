package com.adesormi.ankicardsgenerator.chinese;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ChineseCardTest {

  private static final String BASE_VALUE = "base";
  private static final String LOGOGRAPHIC_VALUE = "hanzi";
  private static final String ROMANIZED_VALUE = "pinyin";

  private ChineseCard card;

  @Before
  public void setUp() {
    card = new ChineseCard(BASE_VALUE, LOGOGRAPHIC_VALUE, ROMANIZED_VALUE);
  }

  @Test
  public void equals_sameValues_true() {
    assertThat(card).isEqualTo(
        new ChineseCard(BASE_VALUE, LOGOGRAPHIC_VALUE, ROMANIZED_VALUE)
    );
  }

  @Test
  public void equals_differentValues_false() {
    assertThat(card).isNotEqualTo(
        new ChineseCard("otherBaseValue", "otherLogographicValue", "otherRomanizedValue")
    );
  }

  @Test
  public void format_wordWithNoKey_noFormatting() {
    ChineseCard sameCard = new ChineseCard(BASE_VALUE, LOGOGRAPHIC_VALUE, ROMANIZED_VALUE);
    assertThat(sameCard).isEqualTo(card);

    sameCard.format();

    assertThat(sameCard).isEqualTo(card);
  }

  @Test
  public void format_wordWithKey1_wordFormattedWithColor1() {
    ChineseCard card = new ChineseCard(
        BASE_VALUE,
        /* logographicValue= */ "hanzi1",
        /* romanizedValue= */ "pinyin1");

    card.format();

    assertThat(card.getHanzi()).isEqualTo("<span color=\"1\">hanzi1</span>");
    assertThat(card.getPinyin()).isEqualTo("<span color=\"1\">pinyin1</span>");
  }
}
