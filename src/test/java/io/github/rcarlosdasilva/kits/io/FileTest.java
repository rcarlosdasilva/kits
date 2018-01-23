package io.github.rcarlosdasilva.kits.io;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileTest {

  @Ignore
  @Test
  public void test1() {
    FileSignatures fs0 = FileHelper.type("d:\\tmp\\【微矩阵】大日程_2017.xlsx");
    FileSignatures fs1 = FileHelper.type("d:\\tmp\\2016年互联网行业年终总结.docx");
    FileSignatures fs2 = FileHelper.type("d:\\tmp\\2016年中国互联网学习白皮书.pdf");
    FileSignatures fs3 = FileHelper.type("d:\\tmp\\2016年终总结-研发部赵长升.doc");
    FileSignatures fs4 = FileHelper.type("d:\\tmp\\2017年微矩阵上半年社会招聘计划.xls");
    FileSignatures fs5 = FileHelper.type("d:\\tmp\\Zee Avi - Bitter Heart.mp3");
    FileSignatures fs6 = FileHelper.type("d:\\tmp\\yuwen7_1_1.mp4");
    FileSignatures fs7 = FileHelper.type("d:\\tmp\\HAHA.jpg");
    FileSignatures fs8 = FileHelper.type("d:\\tmp\\gogs.png");
    FileSignatures fs9 = FileHelper.type("d:\\tmp\\fbdf9fd761814a9fa43e94bd9f855e5b.png.gif");

    System.out.println(Arrays.toString(fs0.getExtensions()));
    System.out.println(Arrays.toString(fs1.getExtensions()));
    System.out.println(Arrays.toString(fs2.getExtensions()));
    System.out.println(Arrays.toString(fs3.getExtensions()));
    System.out.println(Arrays.toString(fs4.getExtensions()));
    System.out.println(Arrays.toString(fs5.getExtensions()));
    System.out.println(Arrays.toString(fs6.getExtensions()));
    System.out.println(Arrays.toString(fs7.getExtensions()));
    System.out.println(Arrays.toString(fs8.getExtensions()));
    System.out.println(Arrays.toString(fs9.getExtensions()));
  }

}
