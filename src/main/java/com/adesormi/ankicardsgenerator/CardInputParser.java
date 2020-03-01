package com.adesormi.ankicardsgenerator;

public abstract class CardInputParser {

  public abstract Card parseCardInput(String line);

  public static class TooManyFieldsException extends RuntimeException {}

  public static class NotEnoughFieldsException extends RuntimeException {}
}
