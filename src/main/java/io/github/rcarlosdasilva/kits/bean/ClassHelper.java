package io.github.rcarlosdasilva.kits.bean;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

/**
 * 类工具
 * <p>
 * <b>Thanks for Hutool authors! 略有修改</b><br>
 * 
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public class ClassHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClassHelper.class);

  private ClassHelper() {
    throw new IllegalStateException("ClassHelper class");
  }

  /**
   * 获得资源相对路径对应的URL.
   * 
   * @param resource
   *          资源相对路径
   * @param clazz
   *          基准Class，获得的相对路径相对于此Class所在路径，如果为{@code null}则相对ClassPath
   * @return {@link URL}
   */
  public static Optional<URL> resource(String resource, Class<?> clazz) {
    if (clazz != null) {
      return Optional.fromNullable(clazz.getResource(resource));
    } else {
      Optional<ClassLoader> classLoader = classLoader();
      if (classLoader.isPresent()) {
        return Optional.fromNullable(classLoader.get().getResource(resource));
      }
      return Optional.absent();
    }
  }

  /**
   * 获取{@link ClassLoader}<br>
   * 获取顺序如下：<br>
   * 
   * <pre>
   * 1、获取当前线程的ContextClassLoader
   * 2、获取{@link ClassHelper}类对应的ClassLoader
   * 3、获取系统ClassLoader（{@link ClassLoader#getSystemClassLoader()}）
   * </pre>
   * 
   * @return 类加载器
   */
  public static Optional<ClassLoader> classLoader() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null) {
      classLoader = ClassHelper.class.getClassLoader();
    }
    if (classLoader == null) {
      classLoader = ClassLoader.getSystemClassLoader();
    }
    if (classLoader == null) {
      LOGGER.error("获取不到ClassLoader");
    }
    return Optional.fromNullable(classLoader);
  }

}
