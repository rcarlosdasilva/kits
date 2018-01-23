package io.github.rcarlosdasilva.kits.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;

import java.io.*;

/**
 * JSON工具的FastJson实现
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class FastJsonHelper implements JsonHelper {

  FastJsonHelper() {
  }

  @Override
  public String toJson(Object obj) {
    return JSON.toJSONString(obj);
  }

  @Override
  public <T> String toJson(T obj, Class<T> clazz) {
    return toJson(obj);
  }

  @Override
  public boolean writeJosnToFile(String path, Object obj) {
    return writeJosnToFile(new File(path), obj);
  }

  @Override
  public boolean writeJosnToFile(File file, Object obj) {
    JSONWriter writer = null;
    try {
      writer = new JSONWriter(new FileWriter(file));
      writer.writeObject(obj);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  @Override
  public <T> boolean writeJsonToFile(String path, T obj, Class<T> clazz) {
    return writeJosnToFile(path, (Object) obj);
  }

  @Override
  public <T> boolean writeJsonToFile(File file, T obj, Class<T> clazz) {
    return writeJosnToFile(file, (Object) obj);
  }

  @Override
  public Object fromJson(String json) {
    return JSON.parse(json);
  }

  @Override
  public <T> T fromJson(String json, Class<T> clazz) {
    return JSON.parseObject(json, clazz);
  }

  @Override
  public Object readJsonFromFile(String path) {
    return readJsonFromFile(new File(path));
  }

  @Override
  public Object readJsonFromFile(File file) {
    return readJsonFromFile(file, Object.class);
  }

  @Override
  public <T> T readJsonFromFile(String path, Class<T> clazz) {
    return readJsonFromFile(new File(path), clazz);
  }

  @Override
  public <T> T readJsonFromFile(File file, Class<T> clazz) {
    JSONReader reader = null;
    try {
      reader = new JSONReader(new FileReader(file));
      return reader.readObject(clazz);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
    return null;
  }

}
