package com.adesormi.ankicardsgenerator.cards;

import com.adesormi.ankicardsgenerator.fields.Field;
import com.adesormi.ankicardsgenerator.fields.FieldFactory;
import com.adesormi.ankicardsgenerator.fields.FieldType;
import com.google.common.collect.ImmutableList;

import java.util.Objects;

public class CardFactory {

  private final FieldFactory fieldFactory = new FieldFactory();
  private final int masterFieldIndex;
  private final ImmutableList<Integer> immutableFieldsIndex;
  private final ImmutableList<FieldType> fieldTypes;
  private final int fieldsNumber;

  public CardFactory(
      int masterFieldIndex,
      ImmutableList<Integer> immutableFieldsIndex,
      ImmutableList<FieldType> fieldTypes) {
    this.masterFieldIndex = masterFieldIndex;
    this.immutableFieldsIndex = immutableFieldsIndex;
    this.fieldTypes = fieldTypes;
    fieldsNumber = fieldTypes.size();
  }

  public Card createCard(ImmutableList<String> fieldsValues) {
    if (fieldsValues.size() != fieldsNumber) throw new InvalidNumberOfFieldsException();
    ImmutableList.Builder<Field> builder = ImmutableList.builder();
    for (int i = 0; i < fieldsValues.size(); ++i) {
      builder.add(fieldFactory.createField(fieldTypes.get(i), fieldsValues.get(i)));
    }
    Card card = new Card(masterFieldIndex, builder.build());
    immutableFieldsIndex.forEach(i -> card.getFields().get(i).setImmutable(true));
    return card;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CardFactory)) return false;
    CardFactory that = (CardFactory) o;
    return masterFieldIndex == that.masterFieldIndex &&
        Objects.equals(fieldTypes, that.fieldTypes);
  }

  public static class InvalidNumberOfFieldsException extends RuntimeException {}
}
