package io.github.rcarlosdasilva.kits.convention;

/**
 * 正则表达式
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class PatternProvider {

  public static final String EMAIL = "[\\w\\.]+@(\\w+\\.)+[a-zA-Z]{2,4}";
  /**
   * 手机号码:
   * <p>
   * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[0, 1, 6, 7, 8],
   * 18[0-9]<br>
   * 移动号段:
   * 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188<br>
   * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186<br>
   * 电信号段: 133,149,153,170,173,177,180,181,189
   */
  public static final String MOBILE = "^1[34578]\\d{9}$";
  /**
   * 固话号码，可包含国际区号、区号、分机号
   */
  public static final String TELEPHONE = "^((\\(0\\d{2,3}\\)\\s?)|(0\\d{2,3}-))?\\d{7,8}(-\\d{1,4})?$";
  /**
   * URL
   */
  public static final String URL = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
  /**
   * IP4地址
   */
  public static final String IP4 = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";
  /**
   * 国内邮编
   */
  public static final String POSTCODE = "^\\d{6}$";

}
