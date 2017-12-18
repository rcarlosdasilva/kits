package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convention.PatternProvider;

public class DoubleConverter implements Convertible<Object, Double> {

  @Override
  public Double convert(Object original) {
    return convert(original, 0.0);
  }

  @Override
  public Double convert(Object original, Double defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof Number) {
      return ((Number) original).doubleValue();
    }

    final String str = original.toString();
    if (str.length() == 0) {
      return defaultValue;
    }

    if (str.matches(PatternProvider.NUMBER_DOT)) {
      try {
        return Double.parseDouble(str);
      } catch (NumberFormatException ex) {
        // do nothing
      }
    }
    return defaultValue;
  }

}
