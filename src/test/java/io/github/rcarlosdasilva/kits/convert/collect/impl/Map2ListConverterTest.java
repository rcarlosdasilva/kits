package io.github.rcarlosdasilva.kits.convert.collect.impl;

import com.google.common.collect.Maps;
import io.github.rcarlosdasilva.kits.convert.collect.CollectionInternalConverter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class Map2ListConverterTest {

  @Test
  public void test() {
    Map2ListConverter<String, String, String> converter = new Map2ListConverter<>(
        new CollectionInternalConverter<String, String, String>() {

          @Override
          public boolean support(String key, String value) {
            return key.startsWith("a_");
          }

          @Override
          public String interalConvert(String key, String value) {
            return key + ":" + value;
          }
        });

    Map<String, String> source = Maps.newHashMap();
    source.put("a_1", "one");
    source.put("a_2", "two");
    source.put("b_1", "one");
    source.put("b_2", "two");
    List<String> result = converter.convert(source);

    Assert.assertNotNull(result);
    Assert.assertEquals(2, result.size());
    Assert.assertEquals("a_1:one", result.get(0));
  }

}
