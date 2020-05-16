package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.io.FileReader.InvalidInputFileException;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static org.mockito.Mockito.when;

public class FileReaderTest {

  private static final CardFactory CHINESE_CARD_FACTORY =
      new CardFactory(3, ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(3, ImmutableList.of(ENGLISH, VIETNAMESE, VIETNAMESE_VNI));

  private static final String CHINESE_LINE1 = "hello, 你好, ni3 hao3";
  private static final Card CHINESE_CARD1 =
      CHINESE_CARD_FACTORY.createCard(ImmutableList.of("hello", "你好", "ni3 hao3"));
  private static final String CHINESE_LINE2 = "bye, 再见, zai4 jian4";
  private static final Card CHINESE_CARD2 =
      CHINESE_CARD_FACTORY.createCard(ImmutableList.of("bye", "再见", "zai4 jian4"));
  private static final String VIETNAMESE_LINE1 = "I do, tôi làm, toi6 lam2";
  private static final Card VIETNAMESE_CARD1 =
      VIETNAMESE_CARD_FACTORY.createCard(ImmutableList.of("I do", "tôi làm", "toi6 làm"));

  @Mock private CardReader cardReader;

  private FileReader fileReader;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = InvalidInputFileException.class)
  public void readCardsFromFile_nullFileName_throwInvalidInputFileException() {
    fileReader = new FileReader(cardReader);

    fileReader.readCardsFromFile(null);
  }

  @Test(expected = InvalidInputFileException.class)
  public void readCardsFromFile_emptyFileName_throwInvalidInputFileException() {
    fileReader = new FileReader(cardReader);

    fileReader.readCardsFromFile("");
  }

  @Test(expected = InvalidInputFileException.class)
  public void readCardsFromFile_notExistingFile_throwInvalidInputFileException() {
    fileReader = new FileReader(cardReader);

    fileReader.readCardsFromFile("notExistingFile");
  }

  @Test
  public void readCardsFromFile_chineseFileWith2Lines_twoChineseCards() {
    fileReader = new FileReader(cardReader);

    when(cardReader.readLine(CHINESE_LINE1)).thenReturn(CHINESE_CARD1);
    when(cardReader.readLine(CHINESE_LINE2)).thenReturn(CHINESE_CARD2);

    fileReader.readCardsFromFile("test/resources/chinese.csv");
  }

  @Test
  public void readCardsFromFile_vietnameseFile1Line_oneVietnameseCard() {
    fileReader = new FileReader(cardReader);

    when(cardReader.readLine(VIETNAMESE_LINE1)).thenReturn(VIETNAMESE_CARD1);

    fileReader.readCardsFromFile("test/resources/vietnamese.csv");
  }
}
