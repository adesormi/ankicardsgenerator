package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.TestUtil;
import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.InvalidCardException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.adesormi.ankicardsgenerator.TestUtil.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FileReaderTest {

  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private CardReader cardReader;
  private TestUtil testData;
  private FileReader fileReader;

  @Before
  public void setUp() {
    testData = new TestUtil();
  }

  @Test(expected = InvalidInputException.class)
  public void readCardsFromFile_cardError_throwInvalidInputException() {
    fileReader = new FileReader(cardReader);
    when(cardReader.readLine(any())).thenThrow(new InvalidCardException());

    fileReader.readCardsFromFile(testFile("chinese.csv"));
  }

  @Test
  public void readCardsFromFile_chineseFileWith2Lines_twoChineseCards() {
    fileReader = new FileReader(cardReader);
    Card card = testData.chineseCard();
    Card card2 = testData.chineseCard2();
    when(cardReader.readLine(chineseLine())).thenReturn(card);
    when(cardReader.readLine(chineseLine2())).thenReturn(card2);

    assertThat(fileReader.readCardsFromFile(testFile("chinese.csv"))).containsExactly(card, card2);
  }

  @Test
  public void readCardsFromFile_vietnameseFile1Line_oneVietnameseCard() {
    fileReader = new FileReader(cardReader);
    Card card = testData.vietnameseCard();
    when(cardReader.readLine(vietnameseLine())).thenReturn(card);

    assertThat(fileReader.readCardsFromFile(testFile("vietnamese.csv"))).containsExactly(card);
  }
}
