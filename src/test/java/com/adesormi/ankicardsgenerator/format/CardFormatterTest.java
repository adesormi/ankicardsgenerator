package com.adesormi.ankicardsgenerator.format;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.fields.Field;
import com.adesormi.ankicardsgenerator.fields.FieldFactory;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.adesormi.ankicardsgenerator.format.Color.*;
import static com.adesormi.ankicardsgenerator.format.Form.CIRCLE;
import static com.adesormi.ankicardsgenerator.format.Form.RECTANGLE;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class CardFormatterTest {

  private static final ImmutableList<Color> COLORS =
      ImmutableList.of(Color.NONE, Color.NONE, RED, Color.NONE, GREEN, Color.NONE, BLUE);
  private static final ImmutableList<Form> FORMS =
      ImmutableList.of(Form.NONE, Form.NONE, RECTANGLE, Form.NONE, CIRCLE, Form.NONE, Form.NONE);

  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(2, ImmutableList.of(ENGLISH, VIETNAMESE, VIETNAMESE_VNI));

  private static final FieldFactory FIELD_FACTORY = new FieldFactory();
  private static final Field VIETNAMESE_FIELD = FIELD_FACTORY.createField(VIETNAMESE, "tôi làm");

  private CardFormatter cardFormatter;
  private Card vietnameseCard;

  @Before
  public void setUp() {
    cardFormatter = new CardFormatter(COLORS, FORMS);
    vietnameseCard =
        VIETNAMESE_CARD_FACTORY.createCard(ImmutableList.of("I do", "tôi làm", "toi6 lam2"));
  }

  @Test
  public void formatCard_immutableField_notFormatted() {
    vietnameseCard.getFields().get(1).setImmutable(true);

    cardFormatter.formatCard(vietnameseCard);

    assertThat(vietnameseCard.getFields().get(1)).isEqualTo(VIETNAMESE_FIELD);
  }

  @Test
  public void formatCard_keys6And2_format6And2() {
    cardFormatter.formatCard(vietnameseCard);

    assertThat(vietnameseCard.getFields().get(1).getWords().get(0).getValue())
        .isEqualTo("<span color=\"blue\">tôi</span>");
    assertThat(vietnameseCard.getFields().get(1).getWords().get(1).getValue())
        .isEqualTo("<span color=\"red\"><i>làm</i></span>");
  }
}
