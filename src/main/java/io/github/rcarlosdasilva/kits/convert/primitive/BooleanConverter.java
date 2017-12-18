package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;

public class BooleanConverter implements Convertible<Object, Boolean> {

  @Override
  public Boolean convert(Object original) {
    return convert(original, false);
  }

  @Override
  public Boolean convert(Object original, Boolean defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    final String str = original.toString();
    return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("ok")
        || str.equalsIgnoreCase("right") || str.equalsIgnoreCase("1");
  }

}
