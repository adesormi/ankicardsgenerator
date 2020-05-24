package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.io.FileWriter.InvalidOutputException;
import com.adesormi.ankicardsgenerator.io.FileWriter.InvalidOutputFileException;
import com.google.common.collect.ImmutableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.adesormi.ankicardsgenerator.Constants.ROOT_PATH;
import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static org.mockito.Mockito.when;

public class FileWriterTest {

  private static final String CSV_FILE_NAME = "filename.csv";

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
  private static final ImmutableList<Card> CHINESE_CARDS =
      ImmutableList.of(CHINESE_CARD1, CHINESE_CARD2);
  private static final String VIETNAMESE_LINE1 = "I do, tôi làm, toi6 lam2";
  private static final Card VIETNAMESE_CARD1 =
      VIETNAMESE_CARD_FACTORY.createCard(ImmutableList.of("I do", "tôi làm", "toi6 lam2"));
  private static final ImmutableList<Card> VIETNAMESE_CARDS = ImmutableList.of(VIETNAMESE_CARD1);

  @Mock private CardWriter cardWriter;

  private FileWriter fileWriter;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
    Files.deleteIfExists(Paths.get(ROOT_PATH, "test/resources/", CSV_FILE_NAME));
  }

  @Test(expected = InvalidOutputFileException.class)
  public void writeCardsToFile_nullFileName_throwInvalidOutputFileException() {
    fileWriter = new FileWriter(cardWriter);

    fileWriter.writeCardsToFile(null, CHINESE_CARDS);
  }

  @Test(expected = InvalidOutputFileException.class)
  public void writeCardsToFile_emptyFileName_throwInvalidOutputFileException() {
    fileWriter = new FileWriter(cardWriter);

    fileWriter.writeCardsToFile("", CHINESE_CARDS);
  }

  @Test(expected = InvalidOutputFileException.class)
  public void writeCardsToFile_notACsvFile_throwInvalidOutputFileException() {
    fileWriter = new FileWriter(cardWriter);

    fileWriter.writeCardsToFile("notACsvFile", CHINESE_CARDS);
  }

  @Test(expected = InvalidOutputException.class)
  public void writeCardsToFile_errorWhileWritting_throwInvalidOutputException() {
    fileWriter = new FileWriter(cardWriter);
    when(cardWriter.writeCard(CHINESE_CARD1)).thenThrow(new RuntimeException());

    fileWriter.writeCardsToFile("test/resources/" + CSV_FILE_NAME, CHINESE_CARDS);
  }

  @Test
  public void writeCardsToFile_twoChineseCards_fileWithTwoLines() {
    fileWriter = new FileWriter(cardWriter);

    when(cardWriter.writeCard(CHINESE_CARD1)).thenReturn(CHINESE_LINE1);
    when(cardWriter.writeCard(CHINESE_CARD2)).thenReturn(CHINESE_LINE2);

    fileWriter.writeCardsToFile("test/resources/" + CSV_FILE_NAME, CHINESE_CARDS);
  }

  @Test
  public void writeCardsToFile_oneVietnameseCard_fileWithOneLine() {
    fileWriter = new FileWriter(cardWriter);

    when(cardWriter.writeCard(VIETNAMESE_CARD1)).thenReturn(VIETNAMESE_LINE1);

    fileWriter.writeCardsToFile("test/resources/" + CSV_FILE_NAME, VIETNAMESE_CARDS);
  }
}
