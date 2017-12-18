package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convention.PatternProvider;

public class FloatConverter implements Convertible<Object, Float> {

  @Override
  public Float convert(Object original) {
    return convert(original, 0.0f);
  }

  @Override
  public Float convert(Object original, Float defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof Number) {
      return ((Number) original).floatValue();
    }

    final String str = original.toString();
    if (str.length() == 0) {
      return defaultValue;
    }

    if (str.matches(PatternProvider.NUMBER_DOT)) {
      try {
        return Float.parseFloat(str);
      } catch (NumberFormatException ex) {
        // do nothing
      }
    }
    return defaultValue;
  }

}
