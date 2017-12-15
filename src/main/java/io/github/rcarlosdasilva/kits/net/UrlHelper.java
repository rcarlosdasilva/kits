package io.github.rcarlosdasilva.kits.net;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import io.github.rcarlosdasilva.kits.Default;
import io.github.rcarlosdasilva.kits.bean.ClassHelper;
import io.github.rcarlosdasilva.kits.string.TextHelper;

/**
 * URL工具
 * <p>
 * <b>Thanks for Hutool authors! 略有修改</b><br>
 * 
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public class UrlHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(UrlHelper.class);

  /** 针对ClassPath路径的伪协议前缀（兼容Spring）: "classpath:" */
  public static final String URL_PREFIX_CLASSPATH = "classpath:";

  private UrlHelper() {
    throw new IllegalStateException("UrlHelper class");
  }

  /**
   * 转换路径为URI.
   * 
   * @param location
   *          字符串路径
   * @return URI
   * @exception UtilException
   *              包装URISyntaxException
   */
  public static Optional<URI> uri(String str) {
    Preconditions.checkNotNull(str);

    Optional<String> decoded = decode(str);
    if (decoded.isPresent()) {
      try {
        return Optional.of(new URI(decoded.get()));
      } catch (URISyntaxException ex) {
        LOGGER.debug("", ex);
      }
    }
    return Optional.absent();
  }

  /**
   * 转换路径为URL（兼容classpath:资源）.
   * 
   * @param str
   *          资源路径
   * @return {@link URL}
   */
  public static Optional<URL> url(String str) {
    Preconditions.checkNotNull(str);

    // 兼容Spring的ClassPath路径
    if (str.startsWith(URL_PREFIX_CLASSPATH)) {
      Optional<ClassLoader> classLoader = ClassHelper.classLoader();
      if (classLoader.isPresent()) {
        URL url = classLoader.get().getResource(TextHelper.trim(str, URL_PREFIX_CLASSPATH, -1));
        return Optional.fromNullable(url);
      }
      return Optional.absent();
    }

    try {
      return Optional.of(new URL(str));
    } catch (MalformedURLException ex) {
      LOGGER.warn("", ex);
    }

    // 尝试文件路径
    return url(new File(str));
  }

  /**
   * 转换文件为URL.
   * 
   * @param file
   *          文件
   * @return {@link URL}
   */
  public static Optional<URL> url(File file) {
    Preconditions.checkNotNull(file);

    try {
      return Optional.of(file.toURI().toURL());
    } catch (MalformedURLException ex) {
      LOGGER.warn("", ex);
    }
    return Optional.absent();
  }

  /**
   * URL Encode，默认UTF-8编码.
   * 
   * @param str
   *          url
   * @return encoded
   */
  public static Optional<String> encode(String str) {
    return encode(str, Default.DEFAULT_CHARSET);
  }

  /**
   * URL Encode.
   * 
   * @param str
   *          url
   * @param charset
   *          {@link Charset}
   * @return encoded
   */
  public static Optional<String> encode(String str, Charset charset) {
    Preconditions.checkNotNull(str);
    Preconditions.checkNotNull(charset);

    ByteBuffer bb = charset.encode(str);
    return Optional.of(bb.toString());
  }

  /**
   * URL Decode，默认UTF-8编码.
   * 
   * @param str
   *          url
   * @return decoded
   */
  public static Optional<String> decode(String str) {
    return decode(str, Default.DEFAULT_CHARSET);
  }

  /**
   * URL Decode.
   * 
   * @param str
   *          url
   * @param charset
   *          {@link Charset}
   * @return decoded
   */
  public static Optional<String> decode(String str, Charset charset) {
    Preconditions.checkNotNull(str);
    Preconditions.checkNotNull(charset);

    ByteBuffer bb = ByteBuffer.wrap(str.getBytes());
    return Optional.of(charset.decode(bb).toString());
  }

  /**
   * 获取URL路径.
   * <p>
   * 从URL对象中获取不被编码的路径Path<br>
   * 对于本地路径，URL对象的getPath方法对于包含中文或空格时会被编码，导致本读路径读取错误。<br>
   * 此方法将URL转为URI后获取路径用于解决路径被编码的问题
   * 
   * @param url
   *          {@link URL}
   * @return 路径
   */
  public static Optional<String> path(URL url) {
    Preconditions.checkNotNull(url);

    try {
      // URL对象的getPath方法对于包含中文或空格的问题
      return Optional.fromNullable(url.toURI().getPath());
    } catch (URISyntaxException ex) {
      LOGGER.warn("", ex);
    }
    String path = url.getPath();
    if (!Strings.isNullOrEmpty(path)) {
      return Optional.of(path);
    }
    return Optional.absent();
  }

}
