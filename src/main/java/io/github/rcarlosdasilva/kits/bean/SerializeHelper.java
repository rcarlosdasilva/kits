package io.github.rcarlosdasilva.kits.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化助手
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class SerializeHelper {

  public static byte[] serialize(Object object) {
    try {
      return serialize_(object);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static byte[] serialize_(Object object) throws IOException {
    if (object == null) {
      return null;
    }

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(object);
    byte[] result = baos.toByteArray();
    oos.close();
    baos.close();
    return result;
  }

  public static Object unserialize(byte[] bytes) {
    try {
      return unserialize_(bytes);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T unserialize(byte[] bytes, Class<T> clazz) {
    try {
      return (T) unserialize_(bytes);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static Object unserialize_(byte[] bytes) throws ClassNotFoundException, IOException {
    if (bytes == null || bytes.length <= 0) {
      return null;
    }

    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    ObjectInputStream ois = new ObjectInputStream(bais);
    Object result = ois.readObject();
    ois.close();
    bais.close();
    return result;
  }

}
