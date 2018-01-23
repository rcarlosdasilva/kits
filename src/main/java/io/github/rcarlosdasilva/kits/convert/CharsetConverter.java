package io.github.rcarlosdasilva.kits.convert;

import io.github.rcarlosdasilva.kits.able.Convertible;

import java.nio.charset.Charset;

/**
 * 字符集转换
 *
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public class CharsetConverter implements Convertible<String, Charset> {

  private static final String[] REUSEDKEY;
  private static final Charset[] REUSEDVALUE;

  static {
    REUSEDKEY = new String[4];
    REUSEDVALUE = new Charset[4];

    REUSEDKEY[0] = "utf-8";
    REUSEDVALUE[0] = Charset.forName("UTF-8");
    REUSEDKEY[1] = "gbk";
    REUSEDVALUE[1] = Charset.forName("GBK");
    REUSEDKEY[2] = "gb2312";
    REUSEDVALUE[2] = Charset.forName("GB2312");
    REUSEDKEY[3] = "big5";
    REUSEDVALUE[3] = Charset.forName("BIG5");
  }

  @Override
  public Charset convert(String original) {
    return convert(original, Charset.defaultCharset());
  }

  @Override
  public Charset convert(String original, Charset defaultValue) {
    if (original == null) {
      return defaultValue;
    }

    if (original.equalsIgnoreCase(REUSEDKEY[0])) {
      return REUSEDVALUE[0];
    }
    if (original.equalsIgnoreCase(REUSEDKEY[1])) {
      return REUSEDVALUE[1];
    }
    if (original.equalsIgnoreCase(REUSEDKEY[2])) {
      return REUSEDVALUE[2];
    }
    if (original.equalsIgnoreCase(REUSEDKEY[3])) {
      return REUSEDVALUE[3];
    }

    try {
      return Charset.forName(original);
    } catch (Exception ex) {
      return defaultValue;
    }
  }

}
