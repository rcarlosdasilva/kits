package io.github.rcarlosdasilva.kits.sys;

import java.util.Properties;

/**
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public class SystemHelper {

  private static final Properties PROPS = System.getProperties();

  /**
   * 默认的临时文件路径
   *
   * @return dir
   */
  public static String tempDir() {
    return PROPS.getProperty(SysConstant.TMPDIR);
  }

  public static String fileSeparator() {
    return PROPS.getProperty(SysConstant.SEPARATOR_FILE);
  }

  public static String pathSeparator() {
    return PROPS.getProperty(SysConstant.SEPARATOR_PATH);
  }

  public static String lineSeparator() {
    return PROPS.getProperty(SysConstant.SEPARATOR_LINE);
  }

}
