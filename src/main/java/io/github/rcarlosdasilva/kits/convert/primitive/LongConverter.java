package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convention.PatternProvider;

public class LongConverter implements Convertible<Object, Long> {

  @Override
  public Long convert(Object original) {
    return convert(original, 0L);
  }

  @Override
  public Long convert(Object original, Long defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof Number) {
      return ((Number) original).longValue();
    }

    final String str = original.toString();
    if (str.length() == 0 || str.length() > 20) {
      return defaultValue;
    }

    if (str.matches(PatternProvider.NUMBER)) {
      try {
        return Long.parseLong(str);
      } catch (NumberFormatException ex) {
        // do nothing
      }
    }
    return defaultValue;
  }

}
