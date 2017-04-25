package io.github.rcarlosdasilva.kits.string;

public class Characters {

  public static final String NUMBERS = "0123456789";
  public static final String NUMBERS_WITHOUT_ZERO = "123456789";

  public static final String NUMBERS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
  public static final String NUMBERS_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static final String LETTERS = NUMBERS_LOWER_CASE + NUMBERS_UPPER_CASE;

  public static final String NUMBERS_AND_LETTERS = NUMBERS + LETTERS;
  public static final String NUMBERS_AND_LETTERS_LOWER_CASE = NUMBERS + NUMBERS_LOWER_CASE;
  public static final String NUMBERS_AND_LETTERS_UPPER_CASE = NUMBERS + NUMBERS_UPPER_CASE;

  public static final String SPECIAL_USUAL = "-_@#=:.$&";
  public static final String SPECIAL_ALL = "~!@#$%^&*()-_=+[{}];:'\",.<>/?";

}
