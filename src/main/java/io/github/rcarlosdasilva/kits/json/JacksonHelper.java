package io.github.rcarlosdasilva.kits.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

/**
 * JSON工具的Jackson实现
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class JacksonHelper implements JsonHelper {

  private final ObjectMapper normalOM;

  JacksonHelper() {
    normalOM = new ObjectMapper();
  }

  @Override
  public String toJson(Object obj) {
    return toJson(obj, Object.class);
  }

  @Override
  public <T> String toJson(T obj, Class<T> clazz) {
    try {
      return normalOM.writerFor(clazz).writeValueAsString(obj);
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
      return null;
    }
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

    try {
      normalOM.writeValue(file, obj);
      return true;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return false;
  }

  @Override
  public Object fromJson(String json) {
    return fromJson(json, Object.class);
  }

  @Override
  public <T> T fromJson(String json, Class<T> clazz) {
    try {
      return normalOM.readerFor(clazz).readValue(json);
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
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

    try {
      return normalOM.readerFor(clazz).readValue(file);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
