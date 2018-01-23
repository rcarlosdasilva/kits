package io.github.rcarlosdasilva.kits.able;

/**
 * 类型转换
 *
 * @param <F> 原始类型
 * @param <T> 转换类型
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public interface Convertible<F, T> {

  /**
   * 转换.
   *
   * @param original 原始值
   * @return 转换值
   */
  T convert(F original);

  /**
   * 转换.
   *
   * @param original     原始值
   * @param defaultValue 默认值
   * @return 转换值
   */
  T convert(F original, T defaultValue);

}
