package io.github.rcarlosdasilva.kits.collection;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import io.github.rcarlosdasilva.kits.bean.PropertyHelper;

/**
 * 集合相关助手
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class CollectionsHelper {

  /**
   * 按照源对象列表中，指定的对象属性值作为Key，对象作为Value，重新封装为一个Map.
   * 
   * @param <K>
   *          key type
   * @param <V>
   *          value type
   * @param propertyName
   *          对象属性名
   * @param sources
   *          源集合
   * @return map
   */
  @SuppressWarnings("unchecked")
  public static <K, V> Map<K, V> listToMap(String propertyName, List<Object> sources) {
    Preconditions.checkNotNull(propertyName);
    Preconditions.checkNotNull(sources);

    if (sources.size() == 0) {
      return Collections.emptyMap();
    }

    Map<K, V> map = Maps.newHashMapWithExpectedSize(sources.size());
    for (Object source : sources) {
      Object key = PropertyHelper.read(source, propertyName);
      if (key != null) {
        map.put((K) key, (V) source);
      }
    }
    return map;

  }

  /**
   * 按照源对象列表中，指定的对象属性值作为Key，将属性值Key相同的对象聚合为Value列表，重新封装为一个Map.
   * 
   * @param <K>
   *          key type
   * @param <V>
   *          value type
   * @param propertyName
   *          对象属性名
   * @param sources
   *          源集合
   * @return map
   */
  @SuppressWarnings("unchecked")
  public static <K, V> Map<K, List<V>> listToMapWithClustering(String propertyName,
      List<Object> sources) {
    Preconditions.checkNotNull(propertyName);
    Preconditions.checkNotNull(sources);

    if (sources.size() == 0) {
      return Collections.emptyMap();
    }

    Map<K, List<V>> map = Maps.newHashMapWithExpectedSize(sources.size());
    for (Object source : sources) {
      Object key = PropertyHelper.read(source, propertyName);
      if (key != null) {
        if (map.get((K) key) == null) {
          map.put((K) key, Lists.newArrayList());
        }
        map.get((K) key).add((V) source);
      }
    }
    return map;
  }

  /**
   * 按照源对象列表中，指定的对象属性值作为值，重新封装为一个List.
   * 
   * @param <V>
   *          value type
   * @param propertyName
   *          对象属性名
   * @param sources
   *          源集合
   * @return list
   */
  @SuppressWarnings("unchecked")
  public static <V> List<V> listToListWithSeparate(String propertyName, List<Object> sources) {
    Preconditions.checkNotNull(propertyName);
    Preconditions.checkNotNull(sources);

    if (sources.size() == 0) {
      return Collections.emptyList();
    }

    List<V> list = Lists.newArrayList();
    for (Object source : sources) {
      Object value = PropertyHelper.read(source, propertyName);
      if (value != null) {
        list.add((V) value);
      }
    }
    return list;
  }

  /**
   * 按照源对象列表中，指定的对象属性值作为值，重新封装为一个Set.
   * 
   * @param <V>
   *          value type
   * @param propertyName
   *          对象属性名
   * @param sources
   *          源集合
   * @return set
   */
  public static <V> Set<V> listToSetWithSeparate(String propertyName, List<Object> sources) {
    return Sets.newHashSet(listToListWithSeparate(propertyName, sources));
  }

}
