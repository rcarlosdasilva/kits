package io.github.rcarlosdasilva.kits.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;

public class PropertiesFileHelper {

  public static Map<String, String> read(String filePath) throws FileNotFoundException {
    return read(new File(filePath));
  }

  public static Map<String, String> read(File file) throws FileNotFoundException {
    if (!file.exists() || file.isDirectory()) {
      throw new FileNotFoundException("file not found or expect properties file but directory");
    }

    Properties properties = new Properties();
    Reader reader = null;
    try {
      reader = new FileReader(file);
      properties.load(reader);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    Map<String, String> map = Maps.newHashMap();
    @SuppressWarnings("rawtypes")
    Enumeration names = properties.propertyNames();
    while (names.hasMoreElements()) {
      String key = names.nextElement().toString();
      String value = properties.getProperty(key);
      map.put(key, value);
    }
    return map;
  }

  public static void write(String filePath, Map<String, String> values, boolean append)
      throws IOException {
    write(new File(filePath), values, append);
  }

  public static void write(String filePath, String key, Object value, boolean append)
      throws IOException {
    write(new File(filePath), key, value, append);
  }

  public static void write(File file, Map<String, String> values, boolean append)
      throws IOException {
    if (file == null || values == null) {
      throw new NullPointerException();
    }

    if (file.isDirectory()) {
      throw new FileNotFoundException("expect properties file but directory");
    }

    final boolean fileExists = file.exists();
    Properties properties = new Properties();

    if (append && fileExists) {
      Map<String, String> original = read(file);
      properties.putAll(original);
    }

    if (!fileExists) {
      file.createNewFile();
    }

    properties.putAll(values);

    Writer writer = null;
    try {
      writer = new FileWriter(file);
      properties.store(writer, null);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }

  public static void write(File file, String key, Object value, boolean append) throws IOException {
    if (key == null || value == null) {
      throw new NullPointerException();
    }

    Map<String, String> values = Maps.newHashMap();
    values.put(key, String.valueOf(value));
    write(file, values, append);
  }

}
