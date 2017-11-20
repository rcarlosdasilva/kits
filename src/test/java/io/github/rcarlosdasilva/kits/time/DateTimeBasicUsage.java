package io.github.rcarlosdasilva.kits.time;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;

public class DateTimeBasicUsage {

  /**
   * 常用{@link DateTime}实例创建
   */
  @SuppressWarnings("unused")
  public void usage1() {
    // 常用
    DateTime byJoda = DateTime.now();
    // 通过JDK的Date转换
    DateTime byJvmDate = new DateTime(new Date());
    // 通过JDK的Calendar转换
    DateTime byCalender = new DateTime(Calendar.getInstance());
    // 通过毫秒数转换
    DateTime byMillis = new DateTime(System.currentTimeMillis());
    // 通过具体年月日时分秒创建
    DateTime bySpecific = new DateTime(2017, 1, 1, 0, 0, 0);
    // 通过解析字符串创建（字符串日期格式默认为ISO）
    DateTime byStringNew = new DateTime("2017-01-01T00:00:00");
    DateTime byStringParse = DateTime.parse("2017-01-01T00:00:00");
    DateTime byFormatterConstant = TimeFormatPatterns.STANDARD_DATE_TIME.parseDateTime("2017-01-01 00:00:00");
    DateTime byFormatterCustom = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2017-01-01 00:00:00");
  }

  /**
   * 常用转换
   */
  @SuppressWarnings("unused")
  public void usage2() {
    DateTime now = DateTime.now();

    // 将Datetime格式化为字符串，joda-time内部已经提供格式化Pattern的缓存机制，无需顾虑性能问题
    String print = now.toString("yyyy-MM-dd HH:mm:ss");
    Calendar calendar = now.toCalendar(Locale.CHINA);
    Date date = now.toDate();
  }

  /**
   * 常用功能
   */
  @SuppressWarnings("unused")
  public void usage3() {
    DateTime now = DateTime.now();

    // 年月日时分秒都可单独获取，使用get...方法
    long millis = now.getMillis();
    int year = now.getYear();
    int month = now.getMonthOfYear();

    // 通过时间单位指定获取
    int dayOfWeek = now.get(DateTimeFieldType.dayOfWeek());
    int dayOfMonth = now.get(DateTimeFieldType.dayOfMonth());
    int dayOfYear = now.get(DateTimeFieldType.dayOfYear());

    // ---------------------- 业务中常用 ----------------------
    // 当天的0点0分0秒
    now = now.withMillisOfDay(0);
    // 当天的21点30分0秒
    now = now.withHourOfDay(21).withMinuteOfHour(30).withSecondOfMinute(0);

    // 3天前这个时间
    now = now.minusDays(3);
    // 上个周天
    now = now.minusWeeks(1).dayOfWeek().withMaximumValue();
    // 这个周三
    now = now.withDayOfWeek(3);

    // 今年2月的最后一天
    now = now.withMonthOfYear(2).dayOfMonth().withMaximumValue();
    // 今年的第100天
    now = now.withDayOfYear(100);

    // 明年的今天
    now = now.plusYears(1);
    // 十年前的今天
    now = now.minusYears(10);
    // 今年是否闰年
    now.year().isLeap();

    // 两个时间的对比
    DateTime someTime = DateTime.parse("2017-01-01T00:00:00");
    // now是否在someTime之前
    now.isBefore(someTime);
    // now是否在someTime之后
    now.isAfter(someTime);

    // 两个时间间隔多少个月
    int months = Months.monthsBetween(now, someTime).getMonths();
    // 两个时间间隔多少天
    int days = Days.daysBetween(now, someTime).getDays();
    // 还可以对比两个时间相隔的小时、分钟等时间单位
    // ====================== 业务中常用 ======================
  }

  /**
   * 扩展
   */
  @SuppressWarnings("unused")
  public void usage4() {
    DateTime now = DateTime.now();
    // ---------------------- 关于周 ----------------------
    // 禁用ISO周算法，具体解释参考TimeHelper.firstWeek方法
    // TimeHelper.useIsoWeekAlgorithm(false);
    // 以一年中完整的周一至周日视作一周，禁用ISO周算法后有效
    // TimeHelper.admitWholeFirstWeek(true);

    // 当前年一共多少周
    int weeksAll = TimeHelper.weeksIn(now);

    // 当前周是当前年的第几周
    int weeksNow = TimeHelper.weeksOf(now);

    // 当前年的第n周（开始时间）
    now = TimeHelper.weekOn(now, 1);

    // 当前年的第一周
    now = TimeHelper.firstWeek(now);

    // 当前年最后一周
    now = TimeHelper.lastWeek(now);

    // 当前周是否跨月（周一与周日不在同一个月）
    boolean spansTwoMonth = TimeHelper.weekSpansTwoMonth(now);
    // 当前周是否跨年（周一与周日不在同一年）
    boolean spansTwoYear = TimeHelper.weekSpansTwoYear(now);

    // 获取周名称（周一周二或礼拜一礼拜二之类）
    // 周名称使用礼拜，只需设置一次
    TimeHelper.setWeekNameStyle(TimeWeekName.LIBAI);
    String weekDayName = TimeHelper.weekName(now);

    // ====================== 关于周 ======================

    // 当前年二月几天
    int days = TimeHelper.daysInFebruary(now);

    // 计算年龄
    int age = TimeHelper.age(now);
  }

  public static void main(String[] args) {
    DateTimeBasicUsage usage = new DateTimeBasicUsage();
    usage.usage1();
    usage.usage2();
    usage.usage3();
    usage.usage4();
  }

}
