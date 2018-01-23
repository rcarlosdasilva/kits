package io.github.rcarlosdasilva.kits.io;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 将TrID_sigs_GCK中的文件签名信息XML文件，转换为JSON格式
 *
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public class ConvertTrIdSigs2JavaBean {

  @Test
  public void test() {
    URL url = ClassLoader.getSystemClassLoader().getResource("templates");

    Converter converter = new Converter();
    converter.setTridDirPath("D:\\working\\tmp\\TrID_sigs_GCK");
    converter.setTemplatePath(url.getPath());
    converter.setTemplateName("FileSignatures.fm");
    converter.setOutput("D:\\Xyz.java");

    try {
      converter.run();
      Assert.assertTrue(true);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.assertTrue(false);
    }
  }

  public static class Converter {

    private String tridDirPath;
    private String templatePath;
    private String templateName;
    private Map<String, Object> data;
    private String output;

    public void setTridDirPath(String tridDirPath) {
      this.tridDirPath = tridDirPath;
    }

    public void setData(Map<String, Object> data) {
      this.data = data;
    }

    public void setTemplatePath(String templatePath) {
      this.templatePath = templatePath;
    }

    public void setTemplateName(String templateName) {
      this.templateName = templateName;
    }

    public void setOutput(String output) {
      this.output = output;
    }

    public void run() throws Exception {
      turn();
      gen();
    }

    private void turn() throws Exception {
      File dir = new File(this.tridDirPath);
      File[] files = dir.listFiles();

      XmlMapper xm = new XmlMapper();
      List<Object> defins = Lists.newArrayList();
      for (File file : files) {
        @SuppressWarnings("unchecked")
        Map<String, String> values = xm.readValue(file, Map.class);
        defins.add(values);
      }

      this.data = Maps.newHashMap();
      this.data.put("defins", defins);
    }

    private void gen() throws Exception {
      final Configuration conf = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
      conf.setOutputEncoding("UTF-8");
      conf.setDirectoryForTemplateLoading(new File(this.templatePath));

      try (Writer out = new OutputStreamWriter(new FileOutputStream(this.output), "UTF-8")) {
        Template template = conf.getTemplate(this.templateName);
        template.process(this.data, out);
      } catch (Exception e) {
        throw e;
      }
    }

  }

}
