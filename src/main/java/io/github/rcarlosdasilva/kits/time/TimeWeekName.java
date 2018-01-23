package io.github.rcarlosdasilva.kits.time;

/**
 * 周名称显示方式
 *
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public enum TimeWeekName {

  /**
   * 中文周几，周日
   */
  ZHOU(new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"}),
  /**
   * 中文礼拜几，礼拜日
   */
  LIBAI(new String[]{"礼拜一", "礼拜二", "礼拜三", "礼拜四", "礼拜五", "礼拜六", "礼拜日"}),
  /**
   * 中文星期几，星期日
   */
  XINGQI(new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"}),
  /**
   * 中文周几，周天
   */
  ZHOU_TIAN(new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周天"}),
  /**
   * 中文礼拜几，礼拜天
   */
  LIBAI_TIAN(new String[]{"礼拜一", "礼拜二", "礼拜三", "礼拜四", "礼拜五", "礼拜六", "礼拜天"}),
  /**
   * 中文星期几，星期天
   */
  XINGQI_TIAN(new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"}),
  /**
   * 英文日期
   */
  EN(null),
  /**
   * 英文日期缩写
   */
  EN_SHORT(null);

  private String[] values;

  private TimeWeekName(String[] values) {
    this.values = values;
  }

  public String[] getValues() {
    return values;
  }

}
