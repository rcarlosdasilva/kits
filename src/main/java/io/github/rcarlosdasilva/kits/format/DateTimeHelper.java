package io.github.rcarlosdasilva.kits.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import io.github.rcarlosdasilva.kits.convention.DateTimePattern;

/**
 * 日期时间助手，更多有用的方法，参考Joda的DateTime
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class DateTimeHelper {

  private static TimeZone defaultTimeZone = TimeZone.getTimeZone("GMT+8");
  private static Map<String, DateFormat> formats = new HashMap<String, DateFormat>();
  private static DateFormat parser = new SimpleDateFormat();

  /**
   * 默认格式化yyyy-MM-dd HH:mm:ss.
   * 
   * @param date
   *          日期
   * @return String 格式化后字符串
   */
  public static String format(final Date date) {
    return format(date, DateTimePattern.Y_M_D_H_M_S);
  }

  /**
   * 默认格式化yyyy-MM-dd HH:mm:ss.
   * 
   * @param timestamp
   *          时间戳
   * @return String 格式化后字符串
   */
  public static String format(final long timestamp) {
    return format(timestamp, DateTimePattern.Y_M_D_H_M_S);
  }

  /**
   * 使用指定的格式
   * 
   * @param date
   *          日期
   * @param pattern
   *          格式
   * @return 格式化后字符串
   */
  public static String format(final Date date, final DateTimePattern pattern) {
    if (pattern != null) {
      return format(date, pattern.getPattern());
    }
    return null;
  }

  /**
   * 使用指定的格式
   * 
   * @param timestamp
   *          时间戳
   * @param pattern
   *          格式
   * @return 格式化后字符串
   */
  public static String format(final long timestamp, final DateTimePattern pattern) {
    if (pattern != null) {
      return format(timestamp, pattern.getPattern());
    }
    return null;
  }

  /**
   * 使用自定义格式
   * 
   * @param date
   *          日期
   * @param pattern
   *          格式字符串
   * @return 格式化后字符串
   */
  public static String format(final Date date, final String pattern) {
    if (date != null && pattern != null && !pattern.trim().equals("")) {
      return instance(pattern).format(date);
    }
    return null;
  }

  /**
   * 使用自定义格式
   * 
   * @param timestamp
   *          时间戳
   * @param pattern
   *          格式字符串
   * @return 格式化后字符串
   */
  public static String format(final long timestamp, final String pattern) {
    return format(new Date(timestamp), pattern);
  }

  /**
   * 默认格式化yyyy-MM-dd HH:mm:ss.
   * 
   * @return 格式化后字符串
   */
  public static String currentDateTime() {
    return currentDateTime(defaultTimeZone);
  }

  /**
   * 默认格式化yyyy-MM-dd.
   * 
   * @return 格式化后字符串
   */
  public static String currentDate() {
    return currentDate(defaultTimeZone);
  }

  /**
   * 默认格式化HH:mm:ss.
   * 
   * @return 格式化后字符串
   */
  public static String currentTime() {
    return currentDate(defaultTimeZone);
  }

  /**
   * 默认格式化yyyy-MM-dd HH:mm:ss.
   * 
   * @param zone
   *          时区
   * @return 格式化后字符串
   */
  public static String currentDateTime(TimeZone zone) {
    return format(now(zone), DateTimePattern.Y_M_D_H_M_S);
  }

  /**
   * 默认格式化yyyy-MM-dd.
   * 
   * @param zone
   *          时区
   * @return 格式化后字符串
   */
  public static String currentDate(TimeZone zone) {
    return format(now(zone), DateTimePattern.Y_M_D);
  }

  /**
   * 默认格式化HH:mm:ss.
   * 
   * @param zone
   *          时区
   * @return 格式化后字符串
   */
  public static String currentTime(TimeZone zone) {
    return format(now(zone), DateTimePattern.H_M_S);
  }

  /**
   * 使用指定格式，当前时间
   * 
   * @param pattern
   *          格式
   * @return 格式化后字符串
   */
  public static String current(final DateTimePattern pattern) {
    return format(now(defaultTimeZone), pattern);
  }

  /**
   * 使用自定义格式，当前时间
   * 
   * @param pattern
   *          格式
   * @return 格式化后字符串
   */
  public static String current(final String pattern) {
    return format(now(defaultTimeZone), pattern);
  }

  /**
   * 转换日期字符串，转换不成功，返回Null
   * 
   * @param source
   *          字符串
   * @return 日期
   */
  public static Date from(final String source) {
    if (source == null || source.trim().equals("")) {
      return null;
    }

    try {
      return parser.parse(source);
    } catch (ParseException e) {
      return null;
    }
  }

  private static DateFormat instance(String pattern) {
    DateFormat format = formats.get(pattern);
    if (format == null) {
      synchronized (formats) {
        if (format == null) {
          format = new SimpleDateFormat(pattern);
          formats.put(pattern, format);
        }
      }
    }
    return format;
  }

  private static Date now(TimeZone zone) {
    return Calendar.getInstance(zone).getTime();
  }

}
