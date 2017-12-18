package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convention.PatternProvider;

public class ByteConverter implements Convertible<Object, Byte> {

  @Override
  public Byte convert(Object original) {
    return convert(original, (byte) 0);
  }

  @Override
  public Byte convert(Object original, Byte defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof Number) {
      return ((Number) original).byteValue();
    }

    final String str = original.toString();
    if (str.length() == 0 || str.length() > 10) {
      return defaultValue;
    }

    if (str.matches(PatternProvider.NUMBER)) {
      int i = Integer.parseInt(str);
      if (i < Byte.MIN_VALUE || i > Byte.MAX_VALUE) {
        return defaultValue;
      }
      return Byte.valueOf((byte) i);
    }
    return defaultValue;
  }

}
