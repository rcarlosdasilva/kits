package io.github.rcarlosdasilva.kits.io;

import com.google.common.base.Preconditions;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 写文件助手
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class WriteHelper {

  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final String DEFAULT_CHARSET_NAME = "UTF-8";
  private static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);

  public static void write(File file, Iterable<?> lines) throws IOException {
    write(file, lines, false);
  }

  public static void write(File file, Iterable<?> lines, boolean append) throws IOException {
    write(file, lines, append, DEFAULT_CHARSET);
  }

  public static void write(File file, Iterable<?> lines, boolean append, Charset charset)
      throws IOException {
    Preconditions.checkState(file != null && file.isFile());
    Preconditions.checkState(lines != null);
    Preconditions.checkState(append && file.exists(), "文件不存在");

    file.getParentFile().mkdirs();

    Writer writer = null;
    try {
      OutputStream fos = new FileOutputStream(file, append);
      Writer osw = new OutputStreamWriter(fos, charset);
      writer = new BufferedWriter(osw);

      for (Object data : lines) {
        writer.append(data.toString()).append(LINE_SEPARATOR);
      }
    } finally {
      StreamHelper.close(writer);
    }
  }

}
