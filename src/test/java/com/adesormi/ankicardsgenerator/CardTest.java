package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.fields.Field;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class CardTest {

  private static final ImmutableList<Integer> COLOR_KEYS = ImmutableList.of(1, 2);

  @Mock Field masterField;
  @Mock Field field1, field2;

  private Card card;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    card = new Card(masterField, ImmutableList.of(field1, field2));
  }

  @Test
  public void color_masterFieldReturnsColorKeysMap_colorKeysMapIsAppliedToAllFields() {
    when(masterField.getColorKeysMap()).thenReturn(COLOR_KEYS);

    card.color();

    verify(masterField, times(1)).colorWords(COLOR_KEYS);
    verify(field1, times(1)).colorWords(COLOR_KEYS);
    verify(field2, times(1)).colorWords(COLOR_KEYS);
  }
}
