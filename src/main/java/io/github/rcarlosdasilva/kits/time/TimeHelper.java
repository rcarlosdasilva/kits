package io.github.rcarlosdasilva.kits.time;

import com.google.common.base.Preconditions;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Locale;

/**
 * 针对joda-time的扩展使用
 * <p>
 * joda-time对周的计算默认是是使用ISO8601周算法。按照这个算法，认为一年中至少要占有四天，那么这个周才属于这一年。
 * 例如一月一号是周四，那么意味着这个周在这一年中刚好占有四天，这个周就属于这一年。如果一月一号是周五，
 * 那这个周在这一年中只占有三天，根据算法，这个周属于上一年。年的最后一周也是按照这个逻辑计算。
 * <p>
 * 国内对周的概念可能会和默认的ISO算法有出入，有的业务需求可能会认为完整的周一至周日7天都在一年中，才算是第一周，
 * 另外有些需求可能会认为一月一号所在的周，不论这天是周几，这周都是这一年的第一周。为了适应这两种需要，
 * 这里提供了两个配置参数：useIsoWeekAlgorithm和admitWholeFirstWeek。 1. useIsoWeekAlgorithm
 * 默认是true，如果设置为false，则不使用joda-time的默认周算法，具体算法参考admitWholeFirstWeek参数 2.
 * admitWholeFirstWeek 默认是true，即认为完整的7天在一年中则为第一周。否则一月一号所在的周即为第一周
 *
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public final class TimeHelper {

  private static final int DEFAULT_MAX_WEEKS_ON_YEAR = 52;

  private static boolean useIsoWeekAlgorithm = true;
  private static boolean admitWholeFirstWeek = true;
  private static TimeWeekName weekName = TimeWeekName.ZHOU;

  private TimeHelper() {
    throw new IllegalStateException("TimeHelper class");
  }

  /**
   * 设置是否使用ISO8601周算法.
   * <p>
   * 该设置影响所有与周相关的计算
   *
   * @param use use
   */
  public static void useIsoWeekAlgorithm(boolean use) {
    useIsoWeekAlgorithm = use;
  }

  /**
   * 是否以完整的一周天数（7天）作为一年的第一周或最后一周。false时以一月一号所在的周作为一年的第一周，不论这天是周几（即便一月一号是周日，也算第一周，一月二号是第二周），默认true.
   * <p>
   * <b>useIsoWeekAlgorithm设置为false时有效</b>，该设置影响所有与周相关的计算
   *
   * @param admit admit
   */
  public static void admitWholeFirstWeek(boolean admit) {
    admitWholeFirstWeek = admit;
  }

  /**
   * 指定获取周名称的方式，默认中文周几.
   *
   * @param style {@link TimeWeekName}
   */
  public static void setWeekNameStyle(TimeWeekName style) {
    weekName = style;
  }

  /**
   * 获取当前年的第n周.
   * <p>
   * 该方法可理解为，以当前年的第一周，向后（weeks - 1周），如果指定的周数大于52或53，日期会调整到n年之后
   * <p>
   * 计算规则参考 {@link #firstWeek()}
   *
   * @param weeks 第n周
   * @return {@link DateTime}
   */
  public static DateTime weekOn(int weeks) {
    return weekOn(DateTime.now(), weeks);
  }

  /**
   * 获取给定年的第n周.
   * <p>
   * 该方法可理解为，以当前年的第一周，向后（weeks - 1周），如果指定的周数大于52或53，日期会调整到n年之后
   * <p>
   * 计算规则参考 {@link #firstWeek()}
   *
   * @param year  指定哪一年
   * @param weeks 第n周
   * @return {@link DateTime}
   */
  public static DateTime weekOn(int year, int weeks) {
    return weekOn(DateTime.now().year().setCopy(year), weeks);
  }

  /**
   * 获取指定时间所在年的第n周.
   * <p>
   * 该方法可理解为，以当前年的第一周，向后（weeks - 1周），如果指定的周数大于52或53，日期会调整到n年之后
   * <p>
   * 计算规则参考 {@link #firstWeek()}
   *
   * @param time  指定时间
   * @param weeks 第n周
   * @return {@link DateTime}
   */
  public static DateTime weekOn(DateTime time, int weeks) {
    Preconditions.checkNotNull(time);
    Preconditions.checkArgument(weeks > 0);

    DateTime temp = time;
    // 如果指定时间是一年的最后几天，根据周算法，很有可能周所在的年和当前时间年不一样，例如2001年12月31日
    // 星期一，这天其实是2002年的第一周，所以周年是2002。为了保证给定时间再生活习惯上的一致，将时间向前调整一周，使周年和实际年统一起来
    if (time.getYear() != time.weekyear().get()) {
      temp = time.weekOfWeekyear().addToCopy(-1);
    }
    return firstWeek(temp).weekOfWeekyear().addToCopy(weeks - 1);
  }

  /**
   * 获取当前年的第一周.
   * <p>
   * 按照标准的ISO8601周算法，当年表中，第一周在当年周至少要占4天。<br>
   * 换句话说：<br>
   * 1. 如果一月一号在周五及以后，则这个周在当前年只占有3天以内， 那么当前年的第一周应该在这个周的后边一个周。（例如2016年的第一周应为
   * 2016-01-04 星期一） <br>
   * 2. 如果一月一号在周四及以前，则这个周在当前年占有3天以上，那么这个周就是当前年的第一周，这个周的第一天就会是当前年的前一年12月中的一天。
   * （例如2015年的第一周应为2014-12-29 星期一）
   * <p>
   * <p>
   * PS: 如果不想使用该算法，可调用 {@link #useIsoWeekAlgorithm(boolean)} 方法禁用。禁用后第一周的判断取决于
   * {@link #admitWholeFirstWeek(boolean)}（禁用后默认2015年的第一周为2015-01-05
   * 星期一，如果admitWholeFirstWeek设置为false，2016年的第一周应为 2015-12-28 星期一）
   *
   * @return {@link DateTime}
   */
  public static DateTime firstWeek() {
    return firstWeek(DateTime.now());
  }

  /**
   * 获取给定年的第一周.
   * <p>
   * 按照标准的ISO8601周算法，当年表中，第一周在当年周至少要占4天。<br>
   * 换句话说：<br>
   * 1. 如果一月一号在周五及以后，则这个周在当前年只占有3天以内， 那么当前年的第一周应该在这个周的后边一个周。（例如2016年的第一周应为
   * 2016-01-04 星期一） <br>
   * 2. 如果一月一号在周四及以前，则这个周在当前年占有3天以上，那么这个周就是当前年的第一周，这个周的第一天就会是当前年的前一年12月中的一天。
   * （例如2015年的第一周应为2014-12-29 星期一）
   * <p>
   * <p>
   * PS: 如果不想使用该算法，可调用 {@link #useIsoWeekAlgorithm(boolean)} 方法禁用。禁用后第一周的判断取决于
   * {@link #admitWholeFirstWeek(boolean)}（禁用后默认2015年的第一周为2015-01-05
   * 星期一，如果admitWholeFirstWeek设置为false，2016年的第一周应为 2015-12-28 星期一）
   *
   * @param year 年
   * @return {@link DateTime}
   */
  public static DateTime firstWeek(int year) {
    return firstWeek(DateTime.now().year().setCopy(year));
  }

  /**
   * 获取指定时间所在年的第一周.
   * <p>
   * 按照标准的ISO8601周算法，当年表中，第一周在当年周至少要占4天。<br>
   * 换句话说：<br>
   * 1. 如果一月一号在周五及以后，则这个周在当前年只占有3天以内， 那么当前年的第一周应该在这个周的后边一个周。（例如2016年的第一周应为
   * 2016-01-04 星期一） <br>
   * 2. 如果一月一号在周四及以前，则这个周在当前年占有3天以上，那么这个周就是当前年的第一周，这个周的第一天就会是当前年的前一年12月中的一天。
   * （例如2015年的第一周应为2014-12-29 星期一）
   * <p>
   * <p>
   * PS: 如果不想使用该算法，可调用 {@link #useIsoWeekAlgorithm(boolean)} 方法禁用。禁用后第一周的判断取决于
   * {@link #admitWholeFirstWeek(boolean)}（禁用后默认2015年的第一周为2015-01-05
   * 星期一，如果admitWholeFirstWeek设置为false，2016年的第一周应为 2015-12-28 星期一）
   *
   * @param time 指定时间
   * @return {@link DateTime}
   */
  public static DateTime firstWeek(DateTime time) {
    Preconditions.checkNotNull(time);

    DateTime result = time.weekOfWeekyear().setCopy(1).dayOfWeek().setCopy(1);
    if (useIsoWeekAlgorithm) {
      return result;
    }

    if (admitWholeFirstWeek) {
      // 以完整周作为第一周时，如果结果年在参数年之前，则将时间向后调一周
      if (result.getYear() < time.getYear()) {
        result = result.weekOfWeekyear().addToCopy(1);
      }
    } else {
      // 不以完整周作为第一周时，如果结果年没变，并且当前日期不在一月一日，将时间向前调一周
      if (result.getYear() == time.getYear() && result.getDayOfMonth() > 1) {
        result = result.weekOfWeekyear().addToCopy(-1);
      }
    }
    return result;
  }

  /**
   * 当前年的最后一周.
   * <p>
   * 1. 如果是按照标准的ISO周算法，最后一周的判断依据是，12月31号是否是周一周二周三或周四<br>
   * 2. 否则，默认一年按照52周计算。在admitWholeFirstWeek为true的设置下，第52周很可能会在第二年，这种情况下，会按照51周计算
   *
   * @return {@link DateTime}
   */
  public static DateTime lastWeek() {
    return lastWeek(DateTime.now());
  }

  /**
   * 给定年的最后一周.
   * <p>
   * 1. 如果是按照标准的ISO周算法，最后一周的判断依据是，12月31号是否是周一周二周三或周四<br>
   * 2. 否则，默认一年按照52周计算。在admitWholeFirstWeek为true的设置下，第52周很可能会在第二年，这种情况下，会按照51周计算
   *
   * @param year 年
   * @return {@link DateTime}
   */
  public static DateTime lastWeek(int year) {
    return lastWeek(DateTime.now().year().setCopy(year));
  }

  /**
   * 一年的最后一周.
   * <p>
   * 1. 如果是按照标准的ISO周算法，最后一周的判断依据是，12月31号是否是周一周二周三或周四<br>
   * 2. 否则，默认一年按照52周计算。在admitWholeFirstWeek为true的设置下，第52周很可能会在第二年，这种情况下，会按照51周计算
   *
   * @param time 指定时间
   * @return {@link DateTime}
   */
  public static DateTime lastWeek(DateTime time) {
    Preconditions.checkNotNull(time);

    if (useIsoWeekAlgorithm) {
      int maxWeeks = time.weekOfWeekyear().getMaximumValue();
      return time.weekOfWeekyear().setCopy(maxWeeks).dayOfWeek().setCopy(1);
    } else {
      // 默认从第一周向后推第52周
      DateTime result = firstWeek(time).weekOfWeekyear().addToCopy(DEFAULT_MAX_WEEKS_ON_YEAR);
      if (result.getYear() > time.getYear()) {
        result = result.weekOfWeekyear().addToCopy(-1);
      }
      return result;
    }
  }

  /**
   * 计算当前年共有多少周.
   * <p>
   * 如果按照标准的ISO周算法时，并且admitWholeFirstWeek为false时，因为一年有52个周零2天或3天（闰年），所以都是53周
   *
   * @return weeks
   */
  public static int weeksIn() {
    return weeksIn(DateTime.now());
  }

  /**
   * 计算给定年共有多少周.
   * <p>
   * 如果按照标准的ISO周算法时，并且admitWholeFirstWeek为false时，因为一年有52个周零2天或3天（闰年），所以都是53周
   *
   * @param year 年
   * @return weeks
   */
  public static int weeksIn(int year) {
    return weeksIn(DateTime.now().year().setCopy(year));
  }

  /**
   * 计算当前年共有多少周.
   * <p>
   * 如果按照标准的ISO周算法时，并且admitWholeFirstWeek为false时，因为一年有52个周零2天或3天（闰年），所以都是53周
   *
   * @param time {@link DateTime}
   * @return weeks
   */
  public static int weeksIn(DateTime time) {
    Preconditions.checkNotNull(time);

    return weeksOf(lastWeek(time));
  }

  /**
   * 当前周是第几周.
   * <p>
   * 当使用整周计算时（admitWholeFirstWeek为true），如果时间为一月初的几天，可能不会被认为是第一周，这时返回0周
   *
   * @return weeks of a year
   */
  public static int weeksOf() {
    return weeksOf(DateTime.now());
  }

  /**
   * 给定时间是第几周.
   * <p>
   * 当使用整周计算时（admitWholeFirstWeek为true），如果时间为一月初的几天，可能不会被认为是第一周，这时返回0周
   *
   * @param time {@link DateTime}
   * @return weeks of a year
   */
  public static int weeksOf(DateTime time) {
    Preconditions.checkNotNull(time);

    if (useIsoWeekAlgorithm) {
      return time.weekOfWeekyear().get();
    } else {
      int temp = time.getMonthOfYear();
      // 将日期置为周一
      time = time.dayOfWeek().setCopy(1);
      // 如果以整周计算，并且重置星期一前的月份小于重置后的月份，则表示当前周跨年（向前），返回0周
      if (admitWholeFirstWeek && temp < time.getMonthOfYear()) {
        return 0;
      }
      // 以当前周的前一天（周日）计算天数
      int days = time.getDayOfYear() - 1;

      // 按照上一步得到的不数，计算共有多少个整周（7天）
      // 再加上最后一周
      int weeks = days / 7 + 1;
      if (!admitWholeFirstWeek) {
        // 如果不按照整周计算，那么上边得到的天数不能整除7，意味着年初有几天可以单独算做一周，所以需要再加一周
        if (days % 7 != 0) {
          weeks++;
        }
      }
      return weeks;
    }
  }

  /**
   * 当前周几名称，例如周一周二.
   * <p>
   * 参考 {@link #setWeekNameStyle(TimeWeekName)}
   *
   * @return name
   */
  public static String weekName() {
    return weekName(DateTime.now());
  }

  /**
   * 给定时间的周几名称，例如周一周二.
   * <p>
   * 参考 {@link #setWeekNameStyle(TimeWeekName)}
   *
   * @param time {@link DateTime}
   * @return name
   */
  public static String weekName(DateTime time) {
    Preconditions.checkNotNull(time);

    if (weekName == TimeWeekName.EN) {
      return time.dayOfWeek().getAsText(Locale.ENGLISH);
    } else if (weekName == TimeWeekName.EN_SHORT) {
      return time.dayOfWeek().getAsShortText(Locale.ENGLISH);
    } else {
      return weekName(time, weekName.getValues());
    }
  }

  /**
   * 给定时间的周几名称，可自定义，适用于 {@link TimeWeekName} 无法满足需要的情况.
   * <p>
   * 参考 {@link #setWeekNameStyle(TimeWeekName)}
   *
   * @param time  {@link DateTime}
   * @param names 备选名称数组，长度必须为7
   * @return name
   */
  public static String weekName(DateTime time, String... names) {
    Preconditions.checkNotNull(time);
    Preconditions.checkNotNull(names);
    Preconditions.checkArgument(names.length == 7);

    return names[time.getDayOfWeek() - 1];
  }

  /**
   * 当前周是否跨越两个月.
   * <p>
   * 即周一和周日不在同一个月份
   *
   * @return boolean
   */
  public static boolean weekSpansTwoMonth() {
    return weekSpansTwoMonth(DateTime.now());
  }

  /**
   * 给定时间周是否跨越两个月.
   * <p>
   * 即周一和周日不在同一个月份
   *
   * @param time {@link DateTime}
   * @return boolean
   */
  public static boolean weekSpansTwoMonth(DateTime time) {
    Preconditions.checkNotNull(time);
    return time.dayOfWeek().withMinimumValue().getMonthOfYear() != time.dayOfWeek()
        .withMaximumValue().getMonthOfYear();
  }

  /**
   * 给定时间周是否跨越两年.
   * <p>
   * 即周一和周日不在同一年
   *
   * @return boolean
   */
  public static boolean weekSpansTwoYear() {
    return weekSpansTwoYear(DateTime.now());
  }

  /**
   * 给定时间周是否跨越两年.
   * <p>
   * 即周一和周日不在同一年
   *
   * @param time {@link DateTime}
   * @return boolean
   */
  public static boolean weekSpansTwoYear(DateTime time) {
    Preconditions.checkNotNull(time);
    return time.dayOfWeek().withMinimumValue().getYear() != time.dayOfWeek().withMaximumValue()
        .getYear();
  }

  /**
   * 当前年，二月有几天.
   *
   * @return days in february
   */
  public static int daysInFebruary() {
    return daysInFebruary(DateTime.now());
  }

  /**
   * 当前年，二月有几天.
   *
   * @param time {@link DateTime}
   * @return days in february
   */
  public static int daysInFebruary(DateTime time) {
    Preconditions.checkNotNull(time);

    return time.withMonthOfYear(2).monthOfYear().getMaximumValue();
  }

  /**
   * 计算年龄（公历年龄）.
   *
   * @param dob 出生年月日（时分秒等信息不参与计算）
   * @return age
   */
  public static int age(DateTime dob) {
    return age(dob, DateTime.now());
  }

  /**
   * 计算年龄（公历年龄）.
   *
   * @param dob  出生年月日（时分秒等信息不参与计算）
   * @param time 年龄计算的参照时间
   * @return age
   */
  public static int age(DateTime dob, DateTime time) {
    Preconditions.checkNotNull(dob);
    Preconditions.checkNotNull(time);

    Period period = new Period(dob, time, PeriodType.yearMonthDay());
    return period.getYears();
  }

}
