package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convention.PatternProvider;

public class ShortConverter implements Convertible<Object, Short> {

  @Override
  public Short convert(Object original) {
    return convert(original, (short) 0);
  }

  @Override
  public Short convert(Object original, Short defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof Number) {
      return ((Number) original).shortValue();
    }

    final String str = original.toString();
    if (str.length() == 0 || str.length() > 10) {
      return defaultValue;
    }

    if (str.matches(PatternProvider.NUMBER)) {
      int i = Integer.parseInt(str);
      if (i < Short.MIN_VALUE || i > Short.MAX_VALUE) {
        return defaultValue;
      }
      return Short.valueOf((short) i);
    }
    return defaultValue;
  }

}
