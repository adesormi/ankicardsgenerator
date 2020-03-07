package com.adesormi.ankicardsgenerator;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class FieldTest {

  private static final ImmutableList<Integer> COLORS = ImmutableList.of(1, 2);
  private static final Word WORD1 = new Word("word1");
  private static final Word WORD2 = new Word("word2");

  @Test
  public void getColumnIndex_columnIndexIsNotProvided_columnIndexIs0() {
    assertThat(new LatinField(true, "value").getColumnIndex()).isEqualTo(0);
  }

  @Test(expected = NullPointerException.class)
  public void constructor_valueIsNull_throwNullPointerException() {
    new LatinField(0, true, null);
  }

  @Test
  public void equals_fieldsHaveSameWords_true() {
    assertThat(new LatinField(true, "word"))
        .isEqualTo(new LatinField(true, "word"));
  }

  @Test
  public void equals_fieldsHaveDifferentWords_false() {
    assertThat(new LatinField(true, "word"))
        .isNotEqualTo(new LatinField(true, "otherWord"));
  }

  @Test
  public void equals_fieldsAreColorableAndNot_false() {
    assertThat(new LatinField(false, "word"))
        .isNotEqualTo(new LatinField(true, "word"));
  }

  @Test
  public void getWords_constructorValueHas1Word_listHas1Word() {
    assertThat(new LatinField(true, "word1").getWords())
        .containsExactly(WORD1);
  }

  @Test
  public void getWords_constructorValueHas2Words_listHas2Words() {
    assertThat(new LatinField(true, "word1 word2").getWords())
        .containsExactly(WORD1, WORD2);
  }

  @Test
  public void getWords_constructorValueHas2Characters_listHas2Characters() {
    assertThat(new LogographicField(true, "你好").getWords())
        .containsExactly(new Word("你"), new Word("好"));
  }

  @Test
  public void color_fieldIsNotColorable_wordsAreUnchanged() {
    LatinField field = new LatinField(false, "word1 word2");

    field.mapWordsWithColorKeys(COLORS);

    assertThat(field.getWords()).containsExactly(WORD1, WORD2);
  }

  @Test
  public void color_fieldIsColorable_wordsAreColored() {
    LatinField field = new LatinField(true, "word1 word2");
    Word word1 = new Word("word1");
    word1.setColorKey(1);
    Word word2 = new Word("word2");
    word2.setColorKey(2);

    field.mapWordsWithColorKeys(COLORS);

    assertThat(field.getWords()).containsExactly(word1, word2);
  }
}
