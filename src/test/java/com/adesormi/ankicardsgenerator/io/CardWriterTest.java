package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.TestUtil.chineseLine;
import static com.adesormi.ankicardsgenerator.TestUtil.vietnameseLine;
import static com.google.common.truth.Truth.assertThat;


public class CardWriterTest {

  private TestUtil testData;
  private CardWriter cardWriter;

  @BeforeEach
  void setUp() {
    testData = new TestUtil();
  }

  @Test
  void writeCard_chineseCard_chineseLine() {
    cardWriter = new CardWriter();

    assertThat(cardWriter.writeCard(testData.chineseCard())).isEqualTo(chineseLine());
  }

  @Test
  void writeCard_vietnameseCard_vietnameseLine() {
    cardWriter = new CardWriter();

    assertThat(cardWriter.writeCard(testData.vietnameseCard())).isEqualTo(vietnameseLine());
  }
}
