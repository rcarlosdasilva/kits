package io.github.rcarlosdasilva.kits.convert;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import io.github.rcarlosdasilva.kits.able.Convertible;
import io.github.rcarlosdasilva.kits.convert.primitive.BooleanConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.ByteConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.CharacterConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.DoubleConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.FloatConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.IntegerConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.LongConverter;
import io.github.rcarlosdasilva.kits.convert.primitive.ShortConverter;

/**
 * JDK基本类型转换
 * 
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public final class PrimitiveConverter<T> implements Convertible<Object, T> {

  private static final Map<Class<?>, PrimitiveConverter<?>> INSTANCES;
  private final Convertible<Object, T> internal;

  static {
    Map<Class<?>, PrimitiveConverter<?>> tmp = Maps.newHashMap();
    tmp.put(Boolean.class, new PrimitiveConverter<>(new BooleanConverter()));
    tmp.put(Byte.class, new PrimitiveConverter<>(new ByteConverter()));
    tmp.put(Character.class, new PrimitiveConverter<>(new CharacterConverter()));
    tmp.put(Double.class, new PrimitiveConverter<>(new DoubleConverter()));
    tmp.put(Float.class, new PrimitiveConverter<>(new FloatConverter()));
    tmp.put(Integer.class, new PrimitiveConverter<>(new IntegerConverter()));
    tmp.put(Long.class, new PrimitiveConverter<>(new LongConverter()));
    tmp.put(Short.class, new PrimitiveConverter<>(new ShortConverter()));
    INSTANCES = ImmutableMap.copyOf(tmp);
  }

  private PrimitiveConverter(Convertible<Object, T> internal) {
    this.internal = internal;
  }

  /**
   * 指定转换类型.
   * 
   * @param <T>
   *          转换类型
   * @param clazz
   *          基本类型
   * @return {@link PrimitiveConverter}
   * @throws IllegalArgumentException
   *           如果 clazz不是基本类型
   */
  @SuppressWarnings("unchecked")
  public static <T> PrimitiveConverter<T> of(Class<T> clazz) throws IllegalArgumentException {
    PrimitiveConverter<?> instance = INSTANCES.get(clazz);
    if (instance == null) {
      throw new IllegalArgumentException();
    }
    return (PrimitiveConverter<T>) instance;
  }

  @Override
  public T convert(Object original) {
    return internal.convert(original);
  }

  @Override
  public T convert(Object original, T defaultValue) {
    return internal.convert(original, defaultValue);
  }

}
