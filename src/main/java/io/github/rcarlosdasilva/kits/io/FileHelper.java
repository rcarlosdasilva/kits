package io.github.rcarlosdasilva.kits.io;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import io.github.rcarlosdasilva.kits.net.HttpHelper;
import io.github.rcarlosdasilva.kits.net.http.ContentType;
import io.github.rcarlosdasilva.kits.net.http.HttpMethod;
import io.github.rcarlosdasilva.kits.net.http.ResponseDigest;
import io.github.rcarlosdasilva.kits.string.TextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * 文件工具
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class FileHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

  private static final String HTTP_SCHEME = "http";
  private static final String HTTPS_SCHEME = "https";
  private static final String FILE_SCHEME = "file";
  private static final String FTP_SCHEME = "ftp";
  private static final FileSignatures NONE_FILE_SIGNATURE = FileSignatures.NON;

  private static final List<FileSignatures> fsCache;

  static {
    List<FileSignatures> temp = Lists.newArrayList(FileSignatures.values());
    temp.sort(new Ordering<FileSignatures>() {

      @Override
      public int compare(FileSignatures left, FileSignatures right) {
        if (left.getPosition() == right.getPosition()) {
          return right.getPattern().length() - left.getPattern().length();
        }
        return left.getPosition() - right.getPosition();
      }

    });
    fsCache = ImmutableList.copyOf(temp);
  }

  private FileHelper() {
    throw new IllegalStateException("FileHelper class");
  }

  public static FileSignatures type(String path) {
    Preconditions.checkNotNull(path);

    Optional<File> file = from(path);
    if (file.isPresent()) {
      return type(file.get());
    }
    LOGGER.warn("找不到文件 - {}", path);
    return NONE_FILE_SIGNATURE;
  }

  public static FileSignatures type(File file) {
    Preconditions.checkNotNull(file);

    if (!file.exists() || file.isDirectory()) {
      return NONE_FILE_SIGNATURE;
    }

    try (InputStream fis = new FileInputStream(file)) {
      return type(fis);
    } catch (SecurityException | IOException ex) {
      LOGGER.error("", ex);
    }
    return NONE_FILE_SIGNATURE;
  }

  public static FileSignatures type(InputStream stream) {
    int matchedLength = 0;
    FileSignatures result = NONE_FILE_SIGNATURE;
    int pos = -1;
    String hex = "";
    for (FileSignatures sig : fsCache) {
      if (sig.getPosition() > pos) {
        pos = sig.getPosition();
        try {
          byte[] bytes = StreamHelper.readBytes(stream,
              pos + FileSignatures.MAX_PATTERN_CHARACTER_LENGTH / 2);
          if (bytes.length <= 0) {
            break;
          }
          hex = TextHelper.toHexString(bytes);
          hex = hex.substring(pos).toUpperCase();
        } catch (Exception ex) {
          LOGGER.error("", ex);
          break;
        }
      }

      if (hex.startsWith(sig.getPattern())) {
        if (sig.getPattern().length() > matchedLength) {
          matchedLength = sig.getPattern().length();
          result = sig;
        }
      }
    }

    return result;
  }

  // -----------------------------------------------------------------

  /**
   * 获取存!在!的!指定资源文件.
   * <p>
   * 可按照相对路径获取资源文件，若资源文件不存在，不返回File实例，即只要有File实例，则file.exists()为true
   *
   * @param path 文件路径（相对或绝对）
   * @return {@link File}
   */
  public static Optional<File> from(String path) {
    Preconditions.checkNotNull(path);

    File file = new File(path);
    if (file.exists()) {
      return Optional.of(file);
    }
    return Optional.absent();
  }

  // ==============================================

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

}
