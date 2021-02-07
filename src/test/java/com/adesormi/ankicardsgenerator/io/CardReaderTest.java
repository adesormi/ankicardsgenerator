package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.TestUtil;
import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.cards.InvalidCardException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.adesormi.ankicardsgenerator.TestUtil.*;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class CardReaderTest {

  private AutoCloseable closeable;
  @Mock CardFactory cardFactory;
  private TestUtil testUtil;
  private CardReader cardReader;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
    testUtil = new TestUtil();
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void readLine_nullLine_throwInvalidCardException() {
    cardReader = new CardReader(cardFactory);

    assertThrows(
        InvalidCardException.class,
        () -> cardReader.readLine(null)
    );
  }

  @Test
  void readLine_emptyLine_throwInvalidCardException() {
    cardReader = new CardReader(cardFactory);

    assertThrows(
        InvalidCardException.class,
        () -> cardReader.readLine("")
    );
  }

  @Test
  void readLine_invalidFields_throwInvalidCardException() {
    cardReader = new CardReader(cardFactory);

    when(cardFactory.createCard(any())).thenThrow(InvalidCardException.class);

    assertThrows(
        InvalidCardException.class,
        () -> cardReader.readLine(chineseLine())
    );
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
