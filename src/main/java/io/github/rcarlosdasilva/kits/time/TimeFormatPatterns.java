package io.github.rcarlosdasilva.kits.time;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期时间格式化常用Pattern
 *
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public final class TimeFormatPatterns {

  public static final DateTimeFormatter STANDARD_DATE_ONLY = DateTimeFormat
      .forPattern("yyyy-MM-dd");
  public static final DateTimeFormatter STANDARD_TIME_ONLY = DateTimeFormat.forPattern("HH:mm:ss");
  public static final DateTimeFormatter STANDARD_DATE_TIME = DateTimeFormat
      .forPattern("yyyy-MM-dd HH:mm:ss");
  public static final DateTimeFormatter STANDARD_DATE_TIME_WEEK = DateTimeFormat
      .forPattern("yyyy-MM-dd HH:mm:ss EE");

  public static final DateTimeFormatter CHINESE_DATE_ONLY = DateTimeFormat
      .forPattern("yyyy年MM月dd日");
  public static final DateTimeFormatter CHINESE_TIME_ONLY = DateTimeFormat.forPattern("HH时mm分ss秒");
  public static final DateTimeFormatter CHINESE_DATE_TIME = DateTimeFormat
      .forPattern("yyyy年MM月dd日 HH时mm分ss秒");

  private TimeFormatPatterns() {
    throw new IllegalStateException("TimeFormatPatterns class");
  }

}
