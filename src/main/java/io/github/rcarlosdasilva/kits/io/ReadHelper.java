package io.github.rcarlosdasilva.kits.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import io.github.rcarlosdasilva.kits.able.Convertible;

/**
 * 文件读取助手
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class ReadHelper {

  private static final String DEFAULT_CHARSET_NAME = "UTF-8";
  private static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);

  private static final Convertible<String, String> STRING_CONVERTER = new Convertible<String, String>() {

    @Override
    public String convert(String original) {
      return original;
    }
  };

  public static List<String> read(File file) throws IOException {
    return read(file, String.class, STRING_CONVERTER, DEFAULT_CHARSET);
  }

  public static <T> List<T> read(File file, Class<T> clazz, Convertible<String, T> convertible)
      throws IOException {
    return read(file, clazz, convertible, DEFAULT_CHARSET);
  }

  public static List<String> read(File file, Charset charset) throws IOException {
    return read(file, String.class, STRING_CONVERTER, charset);
  }

  public static <T> List<T> read(File file, Class<T> clazz, Convertible<String, T> convertible,
      Charset charset) throws IOException {
    Preconditions.checkState(file != null && file.isFile());
    Preconditions.checkNotNull(convertible);

    BufferedReader reader = null;
    List<T> result = Lists.newArrayList();
    try {
      FileInputStream fis = new FileInputStream(file);
      InputStreamReader isr = new InputStreamReader(fis, charset);
      reader = new BufferedReader(isr);
      String line = null;
      while ((line = reader.readLine()) != null) {
        result.add(convertible.convert(line));
      }
      return result;
    } finally {
      StreamHelper.close(reader);
    }
  }

}
