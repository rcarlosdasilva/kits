package io.github.rcarlosdasilva.kits.io;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PathTest {

  @Test
  public void test1() {
    String path = PathHelper.absolutePath("templates", null);
    System.out.println(path);
    Assert.assertNotNull(path);
  }

}
