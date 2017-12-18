package io.github.rcarlosdasilva.kits.convert.collect;

/**
 * Map转Collection集合实现
 * 
 * @param <K>
 *          Map key
 * @param <V>
 *          Map value
 * @param <C>
 *          转换集合泛型
 * 
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public interface CollectionInternalConverter<K, V, C> {

  /**
   * 是否转换.
   * 
   * @param key
   *          map key
   * @param value
   *          map value
   * @return boolean
   */
  boolean support(K key, V value);

  /**
   * 转换类型.
   * 
   * @param key
   *          map key
   * @param value
   *          map value
   * @return 转换后对象，可以为null（null不会包含在结果集里）
   */
  C interalConvert(K key, V value);

}
