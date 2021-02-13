package com.adesormi.ankicardsgenerator;

import com.adesormi.ankicardsgenerator.cards.Card;
import com.adesormi.ankicardsgenerator.cards.CardFactory;
import com.google.common.collect.ImmutableList;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.adesormi.ankicardsgenerator.fields.FieldType.*;

public class TestUtil {

  private static final String TEST_RESOURCES_DIR = "src/test/resources/";

  public CardFactory chineseCardFactory() {
    return new CardFactory(3, ImmutableList.of(0), ImmutableList.of(ENGLISH, CHINESE, CHINESE_PINYIN));
  }

  public static String chineseLine() {
    return "hello, 你好, ni3 hao3";
  }

  public static String chineseLine2() { return "bye, 再见, zai4 jian4"; }

  public static ImmutableList<String> chineseFields() {
    return ImmutableList.of("hello", "你好", "ni3 hao3");
  }

  public static ImmutableList<String> chineseFields2() {
    return ImmutableList.of("bye", "再见", "zai4 jian4");
  }

  public Card chineseCard() {
    return chineseCardFactory().createCard(chineseFields());
  }

  public Card chineseCard2() { return chineseCardFactory().createCard(chineseFields2()); }

  public static String chineseOutput() {
    return "hello, 你好, ni3 hao3";
  }

  public static String chineseOutput2() {
    return "bye, 再见, zai4 jian4";
  }

  public CardFactory vietnameseCardFactory() {
    return new CardFactory(3, ImmutableList.of(0), ImmutableList.of(ENGLISH, VIETNAMESE, VIETNAMESE_VNI));
  }

  public static String vietnameseLine() {
    return "I do, tôi làm, toi6 lam2";
  }

  public static ImmutableList<String> vietnameseFields() {
    return ImmutableList.of("I do", "tôi làm", "toi6 lam2");
  }

  public Card vietnameseCard() {
    return vietnameseCardFactory().createCard(vietnameseFields());
  }

  public static String vietnameseOutput() {
    return "I do, tôi làm, toi6 lam2";
  }

  public static Path testFile(String fileName) {
    return Paths.get(TEST_RESOURCES_DIR, fileName);
  }

  public static String testFileAsStr(String fileName) {
    return TEST_RESOURCES_DIR + fileName;
  }
}
