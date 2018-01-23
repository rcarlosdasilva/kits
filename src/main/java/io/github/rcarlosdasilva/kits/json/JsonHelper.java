package io.github.rcarlosdasilva.kits.json;

import java.io.File;

/**
 * 封装JSON处理工具类，使用不同的JSON包来实现当前接口的功能
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public interface JsonHelper {

  public static final JsonHelper fastjson = new FastJsonHelper();
  public static final JsonHelper gson = new GsonHelper();
  public static final JsonHelper jackson = new JacksonHelper();

  /**
   * 转成JSON字符串.
   *
   * @param obj 被转换对象
   * @return JSON字符串
   */
  String toJson(Object obj);

  /**
   * 根据给定的类型，转换成JSON字符串
   *
   * @param <T>   目标类型
   * @param obj   被转换对象
   * @param clazz 类型
   * @return JSON字符串
   */
  <T> String toJson(T obj, Class<T> clazz);

  /**
   * 将转换后的JSON字符串写入文件.
   *
   * @param path 文件路径
   * @param obj  被转换对象
   * @return 是否成功
   */
  boolean writeJosnToFile(String path, Object obj);

  /**
   * 将转换后的JSON字符串写入文件.
   *
   * @param file {@link File} 文件对象
   * @param obj  被转换对象
   * @return 是否成功
   */
  boolean writeJosnToFile(File file, Object obj);

  /**
   * 将转换后的JSON字符串写入文件.
   *
   * @param <T>   目标类型
   * @param path  文件路径
   * @param obj   被转换对象
   * @param clazz 类型
   * @return 是否成功
   */
  abstract <T> boolean writeJsonToFile(String path, T obj, Class<T> clazz);

  /**
   * 将转换后的JSON字符串写入文件.
   *
   * @param <T>   目标类型
   * @param file  {@link File} 文件对象
   * @param obj   被转换对象
   * @param clazz 类型
   * @return 是否成功
   */
  <T> boolean writeJsonToFile(File file, T obj, Class<T> clazz);

  /**
   * 将JSON字符串转换成Object对象.
   *
   * @param json JSON字符串
   * @return Object对象
   */
  Object fromJson(String json);

  /**
   * 将JSON字符串转换成指定类型的对象.
   *
   * @param <T>   目标类型
   * @param json  JSON字符串
   * @param clazz 类型
   * @return 指定类型对象
   */
  <T> T fromJson(String json, Class<T> clazz);

  /**
   * 从JSON文件中读取对象.
   *
   * @param path 文件路径
   * @return Object对象
   */
  Object readJsonFromFile(String path);

  /**
   * 从JSON文件中读取对象.
   *
   * @param file 文件对象
   * @return Object对象
   */
  Object readJsonFromFile(File file);

  /**
   * 从JSON文件中读取指定类型的对象.
   *
   * @param <T>   目标类型
   * @param path  文件路径
   * @param clazz 类型
   * @return 指定类型对象
   */
  <T> T readJsonFromFile(String path, Class<T> clazz);

  /**
   * 从JSON文件中读取指定类型的对象.
   *
   * @param <T>   目标类型
   * @param file  文件对象
   * @param clazz 类型
   * @return 指定类型对象
   */
  <T> T readJsonFromFile(File file, Class<T> clazz);

}
