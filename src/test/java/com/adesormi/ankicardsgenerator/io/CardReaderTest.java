package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.TestUtil;
import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.cards.InvalidCardException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.adesormi.ankicardsgenerator.TestUtil.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class CardReaderTest {

  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock CardFactory cardFactory;
  private TestUtil testUtil;
  private CardReader cardReader;

  @Before
  public void setUp() {
    testUtil = new TestUtil();
  }

  @Test(expected = InvalidCardException.class)
  public void readLine_nullLine_throwInvalidCardException() {
    cardReader = new CardReader(cardFactory);

    cardReader.readLine(null);
  }

  @Test(expected = InvalidCardException.class)
  public void readLine_emptyLine_throwInvalidCardException() {
    cardReader = new CardReader(cardFactory);

    cardReader.readLine("");
  }

  @Test(expected = InvalidCardException.class)
  public void readLine_invalidFields_throwInvalidCardException() {
    cardReader = new CardReader(cardFactory);

    when(cardFactory.createCard(any())).thenThrow(InvalidCardException.class);

    cardReader.readLine(chineseLine());
  }

  @Test
  public void readLine_chineseLine_chineseCard() {
    cardReader = new CardReader(cardFactory);
    Card chineseCard = testUtil.chineseCard();

    when(cardFactory.createCard(chineseFields())).thenReturn(chineseCard);

    assertThat(cardReader.readLine(chineseLine())).isEqualTo(chineseCard);
  }

  @Test
  public void readLine_vietnameseLine_vietnameseCard() {
    cardReader = new CardReader(cardFactory);
    Card vietnameseCard = testUtil.vietnameseCard();

    when(cardFactory.createCard(vietnameseFields())).thenReturn(vietnameseCard);

    assertThat(cardReader.readLine(vietnameseLine())).isEqualTo(vietnameseCard);
  }
}
