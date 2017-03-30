package io.github.rcarlosdasilva.kits.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Bean属性助手
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class PropertyHelper {

  /**
   * 获取一个类的所有属性描述.
   * 
   * @param clazz
   *          class type
   * @return 属性描述集合
   */
  public static List<PropertyDescriptor> propertyDescriptors(Class<?> clazz) {
    final Field[] fields = clazz.getDeclaredFields();
    List<PropertyDescriptor> descriptors = Lists.newArrayList();

    for (int i = 0; i < fields.length; i++) {
      final Field field = fields[i];
      String name = field.getName();

      if ("class".equals(name)) {
        continue;
      }

      PropertyDescriptor descriptor = propertyDescriptor(clazz, name);
      if (descriptor != null) {
        descriptors.add(descriptor);
      }
    }

    return descriptors;
  }

  /**
   * 获取一个类中指定属性名的属性描述.
   * 
   * @param clazz
   *          class type
   * @param propertyName
   *          属性名
   * @return 属性描述
   */
  public static PropertyDescriptor propertyDescriptor(Class<?> clazz, String propertyName) {
    try {
      return new PropertyDescriptor(propertyName, clazz);
    } catch (IntrospectionException e) {
      return null;
    }
  }

  /**
   * 属性是否可写.
   * 
   * @param descriptor
   *          属性描述
   * @return boolean
   */
  public static boolean isWriteable(PropertyDescriptor descriptor) {
    if (descriptor != null) {
      return descriptor.getWriteMethod() != null;
    }
    return false;
  }

  /**
   * 属性是否可读.
   * 
   * @param descriptor
   *          属性描述
   * @return boolean
   */
  public static boolean isReadable(PropertyDescriptor descriptor) {
    if (descriptor != null) {
      return descriptor.getReadMethod() != null;
    }
    return false;
  }

  /**
   * 根据属性名将值写入对象.
   * 
   * @param target
   *          目标对象
   * @param propertyName
   *          属性名
   * @param value
   *          值
   * @return boolean
   */
  public static boolean write(Object target, String propertyName, Object value) {
    if (target == null) {
      return false;
    }

    PropertyDescriptor pd = propertyDescriptor(target.getClass(), propertyName);
    if (isWriteable(pd)) {
      try {
        write(target, pd, value);
        return true;
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }

    return false;
  }

  /**
   * 根据属性描述将值写入对象.
   * 
   * @param target
   *          目标对象
   * @param descriptor
   *          属性描述
   * @param value
   *          值
   */
  public static void write(Object target, PropertyDescriptor descriptor, Object value)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    descriptor.getWriteMethod().invoke(target, value);
  }

  /**
   * 根据属性描述从目标对象中读出.
   * 
   * @param source
   *          目标对象
   * @param descriptor
   *          属性描述
   * @return 值
   */
  public static Object read(Object source, String propertyName) {
    if (source == null) {
      return false;
    }

    PropertyDescriptor pd = propertyDescriptor(source.getClass(), propertyName);
    if (isReadable(pd)) {
      try {
        return pd.getReadMethod().invoke(source);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }

    return null;

  }

  /**
   * 根据属性描述从目标对象中读出.
   * 
   * @param source
   *          目标对象
   * @param descriptor
   *          属性描述
   * @return 值
   */
  public static Object read(Object source, PropertyDescriptor descriptor)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    return descriptor.getReadMethod().invoke(source);
  }

}
