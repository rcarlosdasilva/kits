package io.github.rcarlosdasilva.kits.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Helper {

  private static final MessageDigest digest;
  private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
      'b', 'c', 'd', 'e', 'f'};

  static {
    try {
      digest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public static String md5(byte bytes[]) {
    return bufferToHex(digest.digest(bytes));
  }

  public static String md5(String value) {
    if (value == null) {
      return null;
    }

    return md5(value.getBytes());
  }

  private static String bufferToHex(byte bytes[]) {
    return bufferToHex(bytes, 0, bytes.length);
  }

  private static String bufferToHex(byte bytes[], int m, int n) {
    StringBuilder builder = new StringBuilder(2 * n);
    int k = m + n;
    for (int l = m; l < k; l++) {
      appendHexPair(bytes[l], builder);
    }
    return builder.toString();
  }

  private static void appendHexPair(byte bt, StringBuilder builder) {
    char c0 = hexDigits[(bt & 0xf0) >> 4];
    char c1 = hexDigits[bt & 0xf];
    builder.append(c0);
    builder.append(c1);
  }

}
