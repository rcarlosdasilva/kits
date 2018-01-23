package io.github.rcarlosdasilva.kits.encryption;

import io.github.rcarlosdasilva.kits.string.Characters;
import io.github.rcarlosdasilva.kits.string.TextHelper;

import java.util.Random;

public class JustEncryption {

  // TODO 未完

  private static final Random RAND = new Random();

  public static String encrypt(final String identifier, final String password, final int saltSize) {
    final long time = System.currentTimeMillis();
    final String salt = salt(saltSize);

    final String[] chips = {identifier, password, salt, String.valueOf(time)};
    final int serial = RAND.nextInt(4);
    final String signature = signature(chips, serial);
    System.out.println(signature);
    return null;
  }

  private static String signature(final String[] chips, final int serial) {
    for (String c : chips) {
      System.out.println(c);
    }

    final String[] newChips = new String[chips.length];
    System.arraycopy(chips, serial, newChips, 0, chips.length - serial);
    System.arraycopy(chips, 0, newChips, chips.length - serial, serial);
    System.out.println("----------------------   " + serial);
    for (String c : newChips) {
      System.out.println(c);
    }

    String.join("$", newChips);

    return null;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println("|||||||||||||||||||||||||||||");
      System.out.println(encrypt("A", "B", 10));
    }
  }

  private static String salt(final int saltSize) {
    return TextHelper.random(saltSize, Characters.NUMBERS_AND_LETTERS);
  }

  public Object decrypt(String ciphertext) {
    return null;
  }

}
