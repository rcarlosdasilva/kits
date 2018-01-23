package io.github.rcarlosdasilva.kits.convert;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlConverterTest {

  @Test
  public void test() throws URISyntaxException {
    UrlConverter converter = new UrlConverter();
    File noneFile = new File("none");
    URI realUri = new URI("http://www.baidu.com");

    Assert.assertNull(converter.convert(null));
    Assert.assertNotNull(converter.convert(noneFile));
    Assert.assertNotNull(converter.convert(realUri));
  }

}
