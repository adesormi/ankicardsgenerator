package com.adesormi.ankicardsgenerator.cards;

import com.adesormi.ankicardsgenerator.cards.CardFactory.InvalidNumberOfFieldsException;
import com.adesormi.ankicardsgenerator.fields.FieldFactory;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.adesormi.ankicardsgenerator.fields.FieldType.ENGLISH;
import static com.adesormi.ankicardsgenerator.fields.FieldType.VIETNAMESE_VNI;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class CardFactoryTest {

  private static final FieldFactory FIELD_FACTORY = new FieldFactory();
  private static final Card CARD = new Card(
      1,
      ImmutableList.of(
          FIELD_FACTORY.createField(ENGLISH, "field1"),
          FIELD_FACTORY.createField(VIETNAMESE_VNI, "field2")));

  private CardFactory cardFactory;

  @Test(expected = InvalidNumberOfFieldsException.class)
  public void createCard_tooManyValues_throwInvalidNumberOfFieldsException() {
    cardFactory = new CardFactory(1, ImmutableList.of(ENGLISH, VIETNAMESE_VNI));

    cardFactory.createCard(ImmutableList.of("field1", "field2", "field3"));
  }

  @Test
  public void createCard_2fields_success() {
    cardFactory = new CardFactory(1, ImmutableList.of(ENGLISH, VIETNAMESE_VNI));

    Card card = cardFactory.createCard(ImmutableList.of("field1", "field2"));

    assertThat(card).isEqualTo(CARD);
  }
}
