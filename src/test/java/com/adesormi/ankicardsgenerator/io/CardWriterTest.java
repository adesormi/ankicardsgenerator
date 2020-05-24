package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.adesormi.ankicardsgenerator.io.CardReader.InvalidCardInputException;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class CardWriterTest {

  private static final CardFactory CHINESE_CARD_FACTORY =
      new CardFactory(3, ImmutableList.of(0), ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(3, ImmutableList.of(0), ImmutableList.of(ENGLISH, VIETNAMESE, VIETNAMESE_VNI));

  private static final String CHINESE_LINE = "hello, 你好, ni3 hao3";
  private static final Card CHINESE_CARD =
      CHINESE_CARD_FACTORY.createCard(ImmutableList.of("hello", "你好", "ni3 hao3"));
  private static final String VIETNAMESE_LINE = "I do, tôi làm, toi6 lam2";
  private static final Card VIETNAMESE_CARD =
      VIETNAMESE_CARD_FACTORY.createCard(ImmutableList.of("I do", "tôi làm", "toi6 lam2"));

  private CardWriter cardWriter;

  @Test
  public void writeCard_chineseCard_chineseLine() {
    cardWriter = new CardWriter();

    assertThat(cardWriter.writeCard(CHINESE_CARD)).isEqualTo(CHINESE_LINE);
  }

  @Test
  public void writeCard_vietnameseCard_vietnameseLine() {
    cardWriter = new CardWriter();

    assertThat(cardWriter.writeCard(VIETNAMESE_CARD)).isEqualTo(VIETNAMESE_LINE);
  }
}
