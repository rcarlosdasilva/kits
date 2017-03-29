package io.github.rcarlosdasilva.kits.convention;

public enum DateTimePattern {

  /**
   * HHmmss.
   */
  HMS("HHmmss"),
  /**
   * HH:mm.
   */
  H_M("HH:mm"),
  /**
   * HH:mm:ss.
   */
  H_M_S("HH:mm:ss"),
  /**
   * yyyyMMdd.
   */
  YMD("yyyyMMdd"),
  /**
   * yyyy-MM-dd.
   */
  Y_M_D("yyyy-MM-dd"),
  /**
   * yyyy-MM
   */
  Y_M("yyyy-MM"),
  /**
   * yyyy-MM-dd.
   */
  M_D("MM-dd"),
  /**
   * yyyyMMddHHmmss.
   */
  YMDHMS("yyyyMMddHHmmss"),
  /**
   * MM-dd HH:mm.
   */
  M_D_H_M("MM-dd HH:mm"),
  /**
   * yyyy-MM-dd HH:mm:ss.
   */
  Y_M_D_H_M_S("yyyy-MM-dd HH:mm:ss");

  private String pattern;

  private DateTimePattern(String pattern) {
    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }

}
