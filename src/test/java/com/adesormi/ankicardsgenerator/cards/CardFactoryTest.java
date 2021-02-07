package com.adesormi.ankicardsgenerator.cards;

import com.adesormi.ankicardsgenerator.fields.FieldFactory;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static com.adesormi.ankicardsgenerator.fields.FieldType.ENGLISH;
import static com.adesormi.ankicardsgenerator.fields.FieldType.VIETNAMESE_VNI;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CardFactoryTest {

  private static final FieldFactory FIELD_FACTORY = new FieldFactory();
  private static final Card CARD = new Card(
      1,
      ImmutableList.of(
          FIELD_FACTORY.createField(ENGLISH, "field1"),
          FIELD_FACTORY.createField(VIETNAMESE_VNI, "field2")));

  private CardFactory cardFactory;

  @Test
  void createCard_tooManyValues_throwInvalidCardException() {
    cardFactory = new CardFactory(1, ImmutableList.of(0), ImmutableList.of(ENGLISH, VIETNAMESE_VNI));

    assertThrows(
        InvalidCardException.class,
        () -> cardFactory.createCard(ImmutableList.of("field1", "field2", "field3"))
    );
  }

  @Test
  void createCard_2fields1Immutable_success() {
    cardFactory = new CardFactory(1, ImmutableList.of(1), ImmutableList.of(ENGLISH, VIETNAMESE_VNI));
    CARD.getFields().get(1).setImmutable(true);

    Card card = cardFactory.createCard(ImmutableList.of("field1", "field2"));

    assertThat(card).isEqualTo(CARD);
  }
}
