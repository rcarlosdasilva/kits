package io.github.rcarlosdasilva.kits.convert.collect.impl;

import com.google.common.collect.Lists;
import io.github.rcarlosdasilva.kits.convert.collect.MapInternalConverter;
import io.github.rcarlosdasilva.kits.convert.collect.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class List2MapConverterTest {

  @Test
  public void test() {
    List2MapConverter<Bean, String, Bean> converter = new List2MapConverter<>(
        new MapInternalConverter<Bean, String, Bean>() {

          @Override
          public boolean support(Bean elem) {
            return elem != null && elem.getA().startsWith("a_");
          }

          @Override
          public Pair<String, Bean> interalConvert(Bean elem) {
            elem.setB(elem.getB() + ":" + elem.getC());
            elem.setC("done");
            return new Pair<String, Bean>(elem.getA(), elem);
          }
        });

    List<Bean> source = Lists.newArrayList();
    source.add(new Bean("a_1", "one", "x"));
    source.add(new Bean("a_2", "two", "y"));
    source.add(new Bean("b_1", "one", "x"));
    source.add(new Bean("b_2", "two", "y"));
    Map<String, Bean> result = converter.convert(source);

    Assert.assertNotNull(result);
    Assert.assertEquals(2, result.size());
    Assert.assertEquals("one:x", result.get("a_1").getB());
    Assert.assertEquals("done", result.get("a_2").getC());
  }

  public static class Bean {

    private String a;
    private String b;
    private String c;

    public Bean(String a, String b, String c) {
      super();
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public String getA() {
      return a;
    }

    public void setA(String a) {
      this.a = a;
    }

    public String getB() {
      return b;
    }

    public void setB(String b) {
      this.b = b;
    }

    public String getC() {
      return c;
    }

    public void setC(String c) {
      this.c = c;
    }

  }

}
