package io.github.rcarlosdasilva.kits.convert.primitive;

import io.github.rcarlosdasilva.kits.able.Convertible;

public class CharacterConverter implements Convertible<Object, Character> {

  @Override
  public Character convert(Object original) {
    return convert(original, ' ');
  }

  @Override
  public Character convert(Object original, Character defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original instanceof Character) {
      return (Character) original;
    }

    final String str = original.toString();
    if (str.length() == 0) {
      return defaultValue;
    }
    return str.charAt(0);
  }

}
