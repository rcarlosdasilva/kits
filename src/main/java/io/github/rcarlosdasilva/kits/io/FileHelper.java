package io.github.rcarlosdasilva.kits.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import io.github.rcarlosdasilva.kits.net.HttpHelper;
import io.github.rcarlosdasilva.kits.net.http.ContentType;
import io.github.rcarlosdasilva.kits.net.http.HttpMethod;
import io.github.rcarlosdasilva.kits.net.http.ResponseDigest;
import io.github.rcarlosdasilva.kits.string.TextHelper;

/**
 * 文件助手
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class FileHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

  private static final String HTTP_SCHEME = "http";
  private static final String HTTPS_SCHEME = "https";
  private static final String FILE_SCHEME = "file";
  private static final String FTP_SCHEME = "ftp";

  private FileHelper() {
    throw new IllegalStateException("FileHelper class");
  }

  public static FileHeaderSign type(InputStream is) {
    String header = readFileHeader(is);

    if (header == null || header.length() == 0) {
      return null;
    }

    // mp4文件头，有以00 00 00 1? 开头和另一种4位偏移量的形式，参考文档
    // http://www.garykessler.net/library/file_sigs.html
    // 例子： (MP4 - ftypmp42)
    // 00 00 00 18 66 74 79 70 6D 70 34 32 和 66 74 79 70 6D 70 34 32
    if (header.startsWith("0000001")) {
      header = header.substring(8);
    }

    return FileHeaderSign.byValue(header);
  }

  private static String readFileHeader(InputStream is) {
    byte[] header = new byte[28];

    try {
      if (is.markSupported()) {
        is.mark(32);
      }
      is.read(header, 0, 28);
      if (is.markSupported()) {
        is.reset();
      }
      return TextHelper.toHexString(header);
    } catch (IOException ex) {
      LOGGER.error("", ex);
    }
    return null;
  }

  public static boolean copy(String from, String to) {
    try {
      return copy(new URI(from), new File(to));
    } catch (URISyntaxException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean copy(URI from, File to) {
    Preconditions.checkNotNull(from);

    if (to == null || to.isDirectory()) {
      return false;
    }

    final String scheme = from.getScheme();
    final boolean isUrlScheme = HTTP_SCHEME.equalsIgnoreCase(scheme)
        || HTTPS_SCHEME.equalsIgnoreCase(scheme) || FILE_SCHEME.equalsIgnoreCase(scheme)
        || FTP_SCHEME.equalsIgnoreCase(scheme);

    if (isUrlScheme && FILE_SCHEME.equalsIgnoreCase(scheme)) {
      return copy(new File(from), to);
    } else if (isUrlScheme) {
      try {
        return copy(from.toURL(), to);
      } catch (MalformedURLException e) {
        e.printStackTrace();
        return false;
      }
    } else {
      return copy(new File(from.getPath()), to);
    }
  }

  public static boolean copy(URL from, File to) {
    if (from == null) {
      return false;
    }

    ResponseDigest digest = HttpHelper.requestStreamWithBodyContent(from.toString(), HttpMethod.GET,
        null, ContentType.TEXT);
    if (digest.isSuccessful()) {
      to.getParentFile().mkdirs();

      InputStream in = digest.getStreamValue();
      FileOutputStream out = null;

      try {
        out = new FileOutputStream(to);
        StreamHelper.io(in, out);
        return true;
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      } finally {
        StreamHelper.close(in);
        StreamHelper.close(out);
      }
    }

    return false;
  }

  public static boolean copy(File from, File to) {
    to.getParentFile().mkdirs();

    FileInputStream in = null;
    FileOutputStream out = null;
    try {
      in = new FileInputStream(from);
      out = new FileOutputStream(to);
      StreamHelper.io(in, out);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      StreamHelper.close(in);
      StreamHelper.close(out);
    }
  }

  public static void main(String[] args) throws URISyntaxException, MalformedURLException {
    // URL aURL = new URL(
    // "http://example.com:80/docs/books/tutorial" +
    // "/index.html?name=networking#DOWNLOADING");
    // // URL a = new URL("‪D:\\123.jpg");
    // URL b = new URL("‪http://www.math.uio/faq/compression-faq/part1.html");
    // URL c = new URL("‪..\\abc.js");
    // URL d = new URL("‪./zz.jsl");
    // URL e = new URL("‪/mnt/zz.jsl");
    // copy("‪D:\\123.jpg", "D:\\testcopy\\a01.json");
  }

}
