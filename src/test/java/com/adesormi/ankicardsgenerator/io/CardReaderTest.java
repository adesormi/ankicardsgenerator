package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.Card;
import com.adesormi.ankicardsgenerator.CardFactory;
import com.adesormi.ankicardsgenerator.fields.*;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.adesormi.ankicardsgenerator.io.CardReader.InvalidCardInputException;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class CardReaderTest {

  private static final String CHINESE_LINE = "hello, 你好, ni3 hao3";
  private static final ImmutableList<String> CHINESE_FIELDS =
      ImmutableList.of("hello", "你好", "ni3 hao3");
  private static final Card CHINESE_CARD = new Card(
      3,
      ImmutableList.of(
          new EnglishField("hello"),
          new ChineseHanziField("你好"),
          new ChinesePinyinField("ni3 hao3")
      )
  );
  private static final String VIETNAMESE_LINE = "I do, tôi làm, toi6 lam2";
  private static final ImmutableList<String> VIETNAMESE_FIELDS =
      ImmutableList.of("I do", "tôi làm", "toi6 lam2");
  private static final Card VIETNAMSE_CARD = new Card(
      3,
      ImmutableList.of(
          new EnglishField("I do"),
          new VietnameseField("tôi làm"),
          new VietnameseVniField("toi6 lam2")
      )
  );

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

    when(cardFactory.createCard(VIETNAMESE_FIELDS)).thenReturn(VIETNAMSE_CARD);

    assertThat(cardReader.readLine(VIETNAMESE_LINE)).isEqualTo(VIETNAMSE_CARD);
  }
}
