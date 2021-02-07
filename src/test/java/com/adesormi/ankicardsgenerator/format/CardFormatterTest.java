package com.adesormi.ankicardsgenerator.format;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.adesormi.ankicardsgenerator.fields.Field;
import com.adesormi.ankicardsgenerator.fields.FieldFactory;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;
import static com.adesormi.ankicardsgenerator.format.Color.*;
import static com.adesormi.ankicardsgenerator.format.Font.BOLD;
import static com.adesormi.ankicardsgenerator.format.Font.ITALIC;
import static com.google.common.truth.Truth.assertThat;


public class CardFormatterTest {

  private static final ImmutableList<Color> COLORS =
      ImmutableList.of(Color.NONE, Color.NONE, RED, Color.NONE, GREEN, Color.NONE, BLUE);
  private static final ImmutableList<Font> FONTS =
      ImmutableList.of(Font.NONE, Font.NONE, ITALIC, Font.NONE, BOLD, Font.NONE, Font.NONE);

  private static final CardFactory VIETNAMESE_CARD_FACTORY =
      new CardFactory(
          2, ImmutableList.of(0), ImmutableList.of(ENGLISH, VIETNAMESE, VIETNAMESE_VNI));

  private static final FieldFactory FIELD_FACTORY = new FieldFactory();
  private static final Field VIETNAMESE_FIELD = FIELD_FACTORY.createField(VIETNAMESE, "tôi làm");

  private CardFormatter cardFormatter;
  private Card vietnameseCard;

  @BeforeEach
  void setUp() {
    cardFormatter = new CardFormatter(COLORS, FONTS);
    vietnameseCard =
        VIETNAMESE_CARD_FACTORY.createCard(ImmutableList.of("I do", "tôi làm", "toi6 lam2"));
  }

  @Test
  void formatCard_immutableField_notFormatted() {
    vietnameseCard.getFields().get(1).setImmutable(true);
    VIETNAMESE_FIELD.setImmutable(true);

    cardFormatter.formatCard(vietnameseCard);

    assertThat(vietnameseCard.getFields().get(1)).isEqualTo(VIETNAMESE_FIELD);
  }

  @Test
  void formatCard_keys6And2_format6And2() {
    cardFormatter.formatCard(vietnameseCard);

    assertThat(vietnameseCard.getFields().get(1).getWords().get(0).getValue())
        .isEqualTo("<span color=\"blue\">tôi</span>");
    assertThat(vietnameseCard.getFields().get(1).getWords().get(1).getValue())
        .isEqualTo("<span color=\"red\"><i>làm</i></span>");
  }
}
