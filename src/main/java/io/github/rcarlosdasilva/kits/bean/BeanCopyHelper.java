package io.github.rcarlosdasilva.kits.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

  public static <T> T copyBeans(Class<T> type, T target, Object... sources) {
    Preconditions.checkNotNull(sources);

    for (Object source : sources) {
      copyBean(type, target, source);
    }

    return target;
  }

  public static <T> T copyBeans(Class<T> type, Object... sources) {
    try {
      return copyBeans(type, type.newInstance(), sources);
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static <T> T copyBean(Class<T> clazz, T target, Object source) {
    Preconditions.checkNotNull(source);
    Preconditions.checkNotNull(target);

    if (source instanceof Map) {
      @SuppressWarnings("unchecked")
      final Map<String, Object> propMap = (Map<String, Object>) source;

      for (final Map.Entry<String, Object> entry : propMap.entrySet()) {
        final String name = entry.getKey();

        PropertyDescriptor descriptor = propertyDescriptor(clazz, name);

        if (isWriteable(descriptor)) {
          write(target, descriptor, entry.getValue());
        }
      }
    } else {
      final Class<?> sourceType = source.getClass();
      final List<PropertyDescriptor> sourceDescriptors = propertyDescriptors(sourceType);

      for (PropertyDescriptor sourceDescriptor : sourceDescriptors) {
        final String name = sourceDescriptor.getName();

        PropertyDescriptor targetDescriptor = propertyDescriptor(clazz, name);

        if (isReadable(sourceDescriptor) && isWriteable(targetDescriptor)) {
          final Object value = read(source, sourceDescriptor);
          if (value != null) {
            write(target, targetDescriptor, value);
          }
        }
      }
    }

    return target;
  }

  public static <T> T copyBean(Class<T> type, Object source) {
    try {
      return copyBean(type, type.newInstance(), source);
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static List<PropertyDescriptor> propertyDescriptors(Class<?> clazz) {
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

  private static PropertyDescriptor propertyDescriptor(Class<?> clazz, String propertyName) {
    try {
      return new PropertyDescriptor(propertyName, clazz);
    } catch (IntrospectionException e) {
      return null;
    }
  }

  private static boolean isWriteable(PropertyDescriptor descriptor) {
    if (descriptor != null) {
      return descriptor.getWriteMethod() != null;
    }
    return false;
  }

  private static boolean isReadable(PropertyDescriptor descriptor) {
    if (descriptor != null) {
      return descriptor.getReadMethod() != null;
    }
    return false;
  }

  private static void write(Object target, PropertyDescriptor descriptor, Object value) {
    try {
      descriptor.getWriteMethod().invoke(target, value);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  private static Object read(Object source, PropertyDescriptor descriptor) {
    try {
      return descriptor.getReadMethod().invoke(source);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
      return null;
    }
  }

}
