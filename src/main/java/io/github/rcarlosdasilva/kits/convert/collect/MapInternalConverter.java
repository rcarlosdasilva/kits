package io.github.rcarlosdasilva.kits.convert.collect;

/**
 * Collection集合转Map实现
 * 
 * @param <C>
 *          集合中元素类型
 * @param <K>
 *          Map key
 * @param <V>
 *          Map value
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public interface MapInternalConverter<C, K, V> {

  /**
   * 是否转换.
   * 
   * @param elem
   *          集合元素
   * @return boolean
   */
  boolean support(C elem);

  /**
   * 转换.
   * 
   * @param elem
   *          集合元素
   * @return 键值对，可以为null（null不会包含在结果Map中）
   */
  Pair<K, V> interalConvert(C elem);

}
