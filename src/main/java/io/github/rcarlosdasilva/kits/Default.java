package io.github.rcarlosdasilva.kits;

import java.nio.charset.Charset;

/**
 * 默认参数
 *
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public final class Default {

  public static final String DEFAULT_CHARSET_VALUE = "UTF-8";
  public static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_VALUE);

  public static final int DEFAULT_STREAM_BUFFER_SIZE = 2048;

  public static final byte[] EMPTY_TYBES = new byte[0];

  private Default() {
    throw new IllegalStateException("Default class");
  }

}
