package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.TestUtil;
import com.adesormi.ankicardsgenerator.cards.Card;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import static com.adesormi.ankicardsgenerator.TestUtil.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


public class FileWriterTest {

  private static final String OUTPUT_FILE = "filename_output.csv";

  private AutoCloseable closeable;
  @Mock private CardWriter cardWriter;
  private TestUtil testUtil;
  private ImmutableList<Card> chineseCards;
  private FileWriter fileWriter;

  @BeforeEach
  void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
    testUtil = new TestUtil();
    chineseCards = ImmutableList.of(testUtil.chineseCard(), testUtil.chineseCard2());
  }

  @AfterEach
  void tearDown() throws Exception {
    Files.deleteIfExists(testFile(OUTPUT_FILE));
    closeable.close();
  }

  @Test
  void writeCardsToFile_twoChineseCards_fileWithTwoLines() {
    fileWriter = new FileWriter(cardWriter);
    when(cardWriter.writeCard(testUtil.chineseCard())).thenReturn(chineseLine());
    when(cardWriter.writeCard(testUtil.chineseCard2())).thenReturn(chineseLine2());

    fileWriter.writeCardsToFile(testFile(OUTPUT_FILE), chineseCards);
    assertThat(output()).containsExactly(chineseOutput(), chineseOutput2());
  }

  @Test
  void writeCardsToFile_oneVietnameseCard_fileWithOneLine() {
    fileWriter = new FileWriter(cardWriter);
    when(cardWriter.writeCard(testUtil.vietnameseCard())).thenReturn(vietnameseLine());

    fileWriter.writeCardsToFile(testFile(OUTPUT_FILE), ImmutableList.of(testUtil.vietnameseCard()));
    assertThat(output()).containsExactly(vietnameseOutput());
  }

  private ImmutableList<String> output() {
    ImmutableList.Builder<String> builder = ImmutableList.builder();
    try (Stream<String> lines = Files.lines(testFile(OUTPUT_FILE))) {
      lines.forEach(builder::add);
    } catch (IOException e) {
      return ImmutableList.of();
    }
    return builder.build();
  }
}
