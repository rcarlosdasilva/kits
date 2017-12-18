package io.github.rcarlosdasilva.kits.convert;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convert.primitive.BooleanConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.ByteConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.CharacterConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.DoubleConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.FloatConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.IntegerConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.LongConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.ShortConverter;

public class PrimitiveConverter {

  private static final BooleanConverter BOOLEAN_CONVERTER = new BooleanConverter();
  private static final ByteConverter BYTE_CONVERTER = new ByteConverter();
  private static final CharacterConverter CHARACTER_CONVERTER = new CharacterConverter();
  private static final DoubleConverter DOUBLE_CONVERTER = new DoubleConverter();
  private static final FloatConverter FLOAT_CONVERTER = new FloatConverter();
  private static final IntegerConverter INTEGER_CONVERTER = new IntegerConverter();
  private static final LongConverter LONG_CONVERTER = new LongConverter();
  private static final ShortConverter SHORT_CONVERTER = new ShortConverter();

  private PrimitiveConverter() {
    throw new IllegalStateException("PrimitiveConverter class");
  }

  @SuppressWarnings("unchecked")
  public static <T> Convertible<Object, T> of(Class<T> clazz) {
    if (clazz == Boolean.class) {
      return (Convertible<Object, T>) BOOLEAN_CONVERTER;
    } else if (clazz == Byte.class) {
      return (Convertible<Object, T>) BYTE_CONVERTER;
    } else if (clazz == Character.class) {
      return (Convertible<Object, T>) CHARACTER_CONVERTER;
    } else if (clazz == Double.class) {
      return (Convertible<Object, T>) DOUBLE_CONVERTER;
    } else if (clazz == Float.class) {
      return (Convertible<Object, T>) FLOAT_CONVERTER;
    } else if (clazz == Integer.class) {
      return (Convertible<Object, T>) INTEGER_CONVERTER;
    } else if (clazz == Long.class) {
      return (Convertible<Object, T>) LONG_CONVERTER;
    } else if (clazz == Short.class) {
      return (Convertible<Object, T>) SHORT_CONVERTER;
    }
    return null;
  }

}
