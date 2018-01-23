package io.github.rcarlosdasilva.kits.convert;

import org.junit.Assert;
import org.junit.Test;

public class PrimitiveConverterTest {

  @Test
  public void test() {
    Assert.assertTrue(PrimitiveConverter.of(Boolean.class).convert("yes"));
    Assert.assertTrue(PrimitiveConverter.of(Boolean.class).convert(null, true));

    Assert.assertEquals(1L, PrimitiveConverter.of(Byte.class).convert("1").longValue());

    Assert.assertEquals(String.valueOf('?'),
        PrimitiveConverter.of(Character.class).convert(null, '?').toString());

    Assert.assertEquals(0.123d, PrimitiveConverter.of(Double.class).convert(".123"), 0.00001d);
    Assert.assertEquals(0.456f, PrimitiveConverter.of(Float.class).convert(".456"), 0.00001f);

    Assert.assertEquals(1L, PrimitiveConverter.of(Integer.class).convert("1").longValue());
    Assert.assertEquals(1L, PrimitiveConverter.of(Long.class).convert("1").longValue());
    Assert.assertEquals(1L, PrimitiveConverter.of(Short.class).convert("1").longValue());
  }

}
