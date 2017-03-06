package io.github.rcarlosdasilva.kits.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.common.base.Strings;
import com.google.gson.Gson;

/**
 * JSON工具的Gson实现
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class GsonHelper extends JsonHelper {

  private final Gson normalGson;

  GsonHelper() {
    normalGson = new Gson();
  }

  @Override
  public String toJson(Object obj) {
    return normalGson.toJson(obj);
  }

  @Override
  public <T> String toJson(T obj, Class<T> clazz) {
    return normalGson.toJson(obj, clazz);
  }

  @Override
  public boolean writeJosnToFile(String path, Object obj) {
    return writeJsonToFile(path, obj, Object.class);
  }

  @Override
  public boolean writeJosnToFile(File file, Object obj) {
    return writeJsonToFile(file, obj, Object.class);
  }

  @Override
  public <T> boolean writeJsonToFile(String path, T obj, Class<T> clazz) {
    if (Strings.isNullOrEmpty(path)) {
      return false;
    }

    File file = new File(path);
    return writeJsonToFile(file, obj, clazz);
  }

  @Override
  public <T> boolean writeJsonToFile(File file, T obj, Class<T> clazz) {
    if (obj == null || file == null || file.isDirectory()) {
      return false;
    }

    FileWriter writer = null;
    try {
      writer = new FileWriter(file);
      writer.write(toJson(obj, clazz));
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  @Override
  public Object fromJson(String json) {
    return normalGson.fromJson(json, Object.class);
  }

  @Override
  public <T> T fromJson(String json, Class<T> clazz) {
    return normalGson.fromJson(json, clazz);
  }

  @Override
  public Object readJsonFromFile(String path) {
    return readJsonFromFile(path, Object.class);
  }

  @Override
  public Object readJsonFromFile(File file) {
    return readJsonFromFile(file, Object.class);
  }

  @Override
  public <T> T readJsonFromFile(String path, Class<T> clazz) {
    if (Strings.isNullOrEmpty(path)) {
      return null;
    }

    File file = new File(path);
    return readJsonFromFile(file, clazz);
  }

  @Override
  public <T> T readJsonFromFile(File file, Class<T> clazz) {
    if (file == null || !file.exists() || file.isDirectory()) {
      return null;
    }

    FileReader reader = null;
    try {
      reader = new FileReader(file);
      return normalGson.fromJson(reader, clazz);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

}
