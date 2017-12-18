package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convention.PatternProvider;

public class IntegerConverter implements Convertible<Object, Integer> {

  @Override
  public Integer convert(Object original) {
    return convert(original, 0);
  }

  @Override
  public Integer convert(Object original, Integer defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof Number) {
      return ((Number) original).intValue();
    }

    final String str = original.toString();
    if (str.length() == 0 || str.length() > 12) {
      return defaultValue;
    }

    if (str.matches(PatternProvider.NUMBER)) {
      try {
        return Integer.parseInt(str);
      } catch (NumberFormatException ex) {
        // do nothing
      }
    }
    return defaultValue;
  }

}
