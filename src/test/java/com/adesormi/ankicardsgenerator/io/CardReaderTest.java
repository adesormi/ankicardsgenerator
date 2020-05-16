package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.fields.Field;
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
public class CardReaderTest {

  private static final CardFactory CHINESE_CARD_FACTORY =
      new CardFactory(3, ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(3, ImmutableList.of(ENGLISH, VIETNAMESE, VIETNAMESE_VNI));

  private static final String CHINESE_LINE = "hello, 你好, ni3 hao3";
  private static final ImmutableList<String> CHINESE_FIELDS =
      ImmutableList.of("hello", "你好", "ni3 hao3");
  private static final Card CHINESE_CARD =
      CHINESE_CARD_FACTORY.createCard(ImmutableList.of("hello", "你好", "ni3 hao3"));
  private static final String VIETNAMESE_LINE = "I do, tôi làm, toi6 lam2";
  private static final ImmutableList<String> VIETNAMESE_FIELDS =
      ImmutableList.of("I do", "tôi làm", "toi6 lam2");
  private static final Card VIETNAMESE_CARD =
      VIETNAMESE_CARD_FACTORY.createCard(ImmutableList.of("I do", "tôi làm", "toi6 làm"));

  @Mock CardFactory cardFactory;

  private CardReader cardReader;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = InvalidCardInputException.class)
  public void readLine_nullLine_throwInvalidCardInputException() {
    cardReader = new CardReader(cardFactory);

    cardReader.readLine(null);
  }

  @Test(expected = InvalidCardInputException.class)
  public void readLine_emptyLine_throwInvalidCardInputException() {
    cardReader = new CardReader(cardFactory);

    cardReader.readLine("");
  }

  @Test
  public void readLine_chineseLine_chineseCard() {
    cardReader = new CardReader(cardFactory);

    when(cardFactory.createCard(CHINESE_FIELDS)).thenReturn(CHINESE_CARD);

    assertThat(cardReader.readLine(CHINESE_LINE)).isEqualTo(CHINESE_CARD);
  }

  @Test
  public void readLine_vietnameseLine_vietnameseCard() {
    cardReader = new CardReader(cardFactory);

    when(cardFactory.createCard(VIETNAMESE_FIELDS)).thenReturn(VIETNAMESE_CARD);

    assertThat(cardReader.readLine(VIETNAMESE_LINE)).isEqualTo(VIETNAMESE_CARD);
  }
}
