package io.github.rcarlosdasilva.kits.convert.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.github.rcarlosdasilva.kits.able.Convertible;

public abstract class AbstractCollectionConverter<F extends Map<K, V>, T extends Collection<C>, K, V, C>
    implements Convertible<F, T> {

  protected final CollectionInternalConverter<K, V, C> internalConverter;

  public AbstractCollectionConverter(CollectionInternalConverter<K, V, C> internalConverter) {
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

    T result = collectionInstance();
    Set<Entry<K, V>> entries = original.entrySet();
    Iterator<Entry<K, V>> iterator = entries.iterator();
    while (iterator.hasNext()) {
      Entry<K, V> entry = iterator.next();
      K key = entry.getKey();
      V value = entry.getValue();
      if (internalConverter.support(key, value)) {
        C elem = internalConverter.interalConvert(key, value);
        if (elem != null) {
          result.add(elem);
        }
      }
    }
    return result;
  }

  /**
   * 结果实现类型实例.
   * 
   * @return instance of {@link Collection}
   */
  public abstract T collectionInstance();

}
