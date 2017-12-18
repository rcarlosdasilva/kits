package io.github.rcarlosdasilva.kits.convert.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import io.github.rcarlosdasilva.kits.able.Convertible;

public abstract class AbstractMapConverter<F extends Collection<C>, T extends Map<K, V>, C, K, V>
    implements Convertible<F, T> {

  protected final MapInternalConverter<C, K, V> internalConverter;

  public AbstractMapConverter(MapInternalConverter<C, K, V> internalConverter) {
    if (internalConverter == null) {
      throw new IllegalArgumentException();
    }
    this.internalConverter = internalConverter;
  }

  @Override
  public T convert(F original) {
    return convert(original, null);
  }

  @Override
  public T convert(F original, T defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    T result = mapInstance();
    Iterator<C> iterator = original.iterator();
    while (iterator.hasNext()) {
      C elem = iterator.next();
      if (internalConverter.support(elem)) {
        Entry<K, V> entry = internalConverter.interalConvert(elem);
        if (entry != null) {
          result.put(entry.getKey(), entry.getValue());
        }
      }
    }
    return result;
  }

  /**
   * 结果实现类型实例.
   * 
   * @return instance of {@link Map}
   */
  public abstract T mapInstance();

}
