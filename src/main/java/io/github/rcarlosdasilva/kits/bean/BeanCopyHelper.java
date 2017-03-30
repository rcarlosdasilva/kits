package io.github.rcarlosdasilva.kits.bean;

import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Bean转换
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class BeanCopyHelper {

  /**
   * 将多个源，拷贝属性形成新的目标对象集合.
   * 
   * @param <T>
   *          type
   * @param clazz
   *          class type
   * @param sources
   *          拷贝源集合
   * @return 目标对象集合
   */
  public static <T> List<T> copyBeanList(Class<T> clazz, List<Object> sources) {
    if (sources == null || sources.size() == 0) {
      return Collections.emptyList();
    }

    List<T> targets = Lists.newArrayListWithCapacity(sources.size());
    for (Object source : sources) {
      targets.add(copyBean(clazz, source));
    }
    return null;
  }

  /**
   * 将传入的多个源sources中的属性，覆盖入给定的targt对象（注：多个源中的属性如果有重复，会按顺序被最后的源覆盖）.
   * 
   * @param <T>
   *          type
   * @param clazz
   *          class type
   * @param target
   *          已有目标对象
   * @param sources
   *          拷贝源
   * @return 目标对象
   */
  public static <T> T copyBeans(Class<T> clazz, T target, Object... sources) {
    Preconditions.checkNotNull(sources);

    for (Object source : sources) {
      copyBean(clazz, target, source);
    }

    return target;
  }

  /**
   * 将传入的多个源sources中的属性，拷贝到一个新创建的目标对象中（注：多个源中的属性如果有重复，会按顺序被最后的源覆盖）.
   * 
   * @param <T>
   *          type
   * @param clazz
   *          class type
   * @param sources
   *          拷贝源
   * @return 目标对象
   */
  public static <T> T copyBeans(Class<T> clazz, Object... sources) {
    try {
      return copyBeans(clazz, clazz.newInstance(), sources);
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 将source的属性覆盖入给定的targt对象.
   * 
   * @param <T>
   *          type
   * @param clazz
   *          class type
   * @param target
   *          已有目标对象
   * @param source
   *          拷贝源
   * @return 目标对象
   */
  public static <T> T copyBean(Class<T> clazz, T target, Object source) {
    Preconditions.checkNotNull(source);
    Preconditions.checkNotNull(target);

    if (source instanceof Map) {
      @SuppressWarnings("unchecked")
      final Map<String, Object> propMap = (Map<String, Object>) source;

      for (final Map.Entry<String, Object> entry : propMap.entrySet()) {
        final String name = entry.getKey();
        PropertyHelper.write(target, name, entry.getValue());
      }
    } else {
      final Class<?> sourceType = source.getClass();
      final List<PropertyDescriptor> sourceDescriptors = PropertyHelper
          .propertyDescriptors(sourceType);

      for (PropertyDescriptor sourceDescriptor : sourceDescriptors) {
        final String name = sourceDescriptor.getName();
        final Object value = PropertyHelper.read(source, name);
        PropertyHelper.write(target, name, value);
      }
    }

    return target;
  }

  /**
   * 将source的属性拷贝到一个新创建的目标对象中.
   * 
   * @param <T>
   *          type
   * @param clazz
   *          class type
   * @param source
   *          拷贝源
   * @return 目标对象
   */
  public static <T> T copyBean(Class<T> clazz, Object source) {
    try {
      return copyBean(clazz, clazz.newInstance(), source);
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

}
