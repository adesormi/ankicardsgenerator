package com.adesormi.ankicardsgenerator.io;

import com.adesormi.ankicardsgenerator.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.TestUtil.chineseLine;
import static com.adesormi.ankicardsgenerator.TestUtil.vietnameseLine;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class CardWriterTest {

  private TestUtil testData;
  private CardWriter cardWriter;

  @Before
  public void setUp() {
    testData = new TestUtil();
  }

  @Test
  public void writeCard_chineseCard_chineseLine() {
    cardWriter = new CardWriter();

    assertThat(cardWriter.writeCard(testData.chineseCard())).isEqualTo(chineseLine());
  }

  @Test
  public void writeCard_vietnameseCard_vietnameseLine() {
    cardWriter = new CardWriter();

    assertThat(cardWriter.writeCard(testData.vietnameseCard())).isEqualTo(vietnameseLine());
  }
}
