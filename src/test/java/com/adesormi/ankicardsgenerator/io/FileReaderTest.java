package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.TestUtil;
import com.adesormi.ankicardsgenerator.cards.Card;
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


public class FileReaderTest {

  private AutoCloseable closeable;
  @Mock private CardReader cardReader;
  private TestUtil testData;
  private FileReader fileReader;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
    testData = new TestUtil();
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void readCardsFromFile_cardError_throwInvalidInputException() {
    fileReader = new FileReader(cardReader);
    when(cardReader.readLine(any())).thenThrow(new InvalidCardException());

    assertThrows(
        InvalidInputException.class,
        () -> fileReader.readCardsFromFile(testFile("chinese.csv"))
    );
  }

  @Test
  void readCardsFromFile_chineseFileWith2Lines_twoChineseCards() {
    fileReader = new FileReader(cardReader);
    Card card = testData.chineseCard();
    Card card2 = testData.chineseCard2();
    when(cardReader.readLine(chineseLine())).thenReturn(card);
    when(cardReader.readLine(chineseLine2())).thenReturn(card2);

    assertThat(fileReader.readCardsFromFile(testFile("chinese.csv"))).containsExactly(card, card2);
  }

  @Test
  void readCardsFromFile_vietnameseFile1Line_oneVietnameseCard() {
    fileReader = new FileReader(cardReader);
    Card card = testData.vietnameseCard();
    when(cardReader.readLine(vietnameseLine())).thenReturn(card);

    assertThat(fileReader.readCardsFromFile(testFile("vietnamese.csv"))).containsExactly(card);
  }
}
