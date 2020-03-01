package com.adesormi.ankicardsgenerator.chinese;

import com.adesormi.ankicardsgenerator.Card;
import com.adesormi.ankicardsgenerator.CardInputParser.NotEnoughFieldsException;
import com.adesormi.ankicardsgenerator.CardInputParser.TooManyFieldsException;
import com.adesormi.ankicardsgenerator.chinese.ChineseCard;
import com.adesormi.ankicardsgenerator.chinese.ChineseCardInputParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class ChineseCardInputParserTest {

  private static final String BASE_VALUE = "base";
  private static final String LOGOGRAPHIC_VALUE = "logographic";
  private static final String ROMANIZED_VALUE = "romanized";
  private static final String INPUT =
      BASE_VALUE + ", " + LOGOGRAPHIC_VALUE + ", " + ROMANIZED_VALUE;

  private static final Card CARD = new ChineseCard(BASE_VALUE, LOGOGRAPHIC_VALUE, ROMANIZED_VALUE);

  private ChineseCardInputParser chineseCardInputParser;

  @Before
  public void setUp() {
    chineseCardInputParser = ChineseCardInputParser.getInstance();
  }

  @Test(expected = NotEnoughFieldsException.class)
  public void parseCardInput_emptyInput_throwNotEnoughFieldsException() {
    chineseCardInputParser.parseCardInput("");
  }

  @Test(expected = NotEnoughFieldsException.class)
  public void parseCardInput_notEnoughFields_throwNotEnoughFieldsException() {
    chineseCardInputParser.parseCardInput("field1, field2");
  }

  @Test(expected = TooManyFieldsException.class)
  public void parseCardInput_tooManyFields_throwTooManyFieldsException() {
    chineseCardInputParser.parseCardInput("field1, field2, field3, extraField");
  }

  @Test
  public void parseCardInput_validInput_card() {
    assertThat(chineseCardInputParser.parseCardInput(INPUT)).isEqualTo(CARD);
  }
}
