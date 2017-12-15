package io.github.rcarlosdasilva.kits.io;

import java.net.URL;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import io.github.rcarlosdasilva.kits.bean.ClassHelper;
import io.github.rcarlosdasilva.kits.net.UrlHelper;
import io.github.rcarlosdasilva.kits.string.TextHelper;

/**
 * 本地路径工具
 * <p>
 * <b>Thanks for Hutool authors! 略有修改</b><br>
 * 
 * @author <a href="mailto:rcarlosdasilva@qq.com">Dean Zhao</a>
 */
public class PathHelper {

  private PathHelper() {
    throw new IllegalStateException("PathHelper class");
  }

  /**
   * 修复路径.
   * <p>
   * 如果原路径尾部有分隔符，则保留为标准分隔符（/），否则不保留
   * <ol>
   * <li>统一用"/"作为分隔符</li>
   * <li>多个"/"转换为一个"/"</li>
   * <li>去除两边空格</li>
   * <li>".."和 "."转换为绝对路径，当".."多于已有路径时，直接返回根路径</li>
   * </ol>
   * 
   * 例如：
   * 
   * <pre>
   * "/foo//" =》 "/foo/"
   * "/foo/./" =》 "/foo/"
   * "/foo/../bar" =》 "/bar"
   * "/foo/../bar/" =》 "/bar/"
   * "/foo/../bar/../baz" =》 "/baz"
   * "/../" =》 "/"
   * "foo/bar/.." =》 "foo"
   * "foo/../bar" =》 "bar"
   * "foo/../../bar" =》 "bar"
   * "//server/foo/../bar" =》 "/server/bar"
   * "//server/../bar" =》 "/bar"
   * "C:\\foo\\..\\bar" =》 "C:/bar"
   * "C:\\..\\bar" =》 "C:/bar"
   * "~/foo/../bar/" =》 "~/bar/"
   * "~/../bar" =》 "bar"
   * </pre>
   * 
   * @param path
   *          原路径
   * @return 修复后的路径
   */
  public static String fix(final String path) {
    if (Strings.isNullOrEmpty(path)) {
      return path;
    }

    String raw = path.trim();
    raw = path.replaceAll("[/\\\\]{1,}", "/").trim();

    String drive = "";
    if (raw.contains(":")) {
      int pos = raw.indexOf(':') + 2;
      drive = raw.substring(0, pos);
      raw = raw.substring(pos);
    }

    String[] parts = raw.split("/");
    List<String> results = Lists.newLinkedList();
    int tops = 0;

    for (int i = parts.length - 1; i >= 0; i--) {
      String element = parts[i];

      if (".".equals(element)) {
        // 当前目录，丢弃
      } else if ("..".equals(element)) {
        tops++;
      } else {
        if (tops > 0) {
          // Merging path element with element corresponding to top path.
          tops--;
        } else {
          // Normal path element found.
          results.add(0, element);
        }
      }
    }

    String result = TextHelper.join("/", results);
    if (path.endsWith("/")) {
      result += "/";
    }
    return drive + result;
  }

  /**
   * 获取绝对路径.
   * <p>
   * <b>不保证路径资源绝对存在</b>
   * <p>
   * 获取不到会将入参path返回
   * 
   * @param path
   *          文件（夹）名或路径
   * @param clazz
   *          相对路径所相对的类，可为null
   * @return 绝对路径
   */
  public static String absolutePath(final String path, Class<?> clazz) {
    if (Strings.isNullOrEmpty(path)) {
      return path;
    }

    String rawPath = fix(path);

    // 给定的路径已经是绝对路径了
    if ('/' == rawPath.charAt(0) || ':' == rawPath.charAt(1)) {
      return path;
    }

    // 兼容Spring风格的ClassPath路径，去除前缀，不区分大小写
    rawPath = TextHelper.trim(rawPath, "classpath:", -1);

    // 相对于ClassPath路径
    Optional<URL> resourceUrl = ClassHelper.resource(rawPath, clazz);
    if (resourceUrl.isPresent()) {
      // 解决中文或空格路径被编码的问题
      return UrlHelper.path(resourceUrl.get()).or(path);
    }

    // 如果资源不存在，则返回一个拼接的资源绝对路径
    Optional<URL> classesUrl = ClassHelper.resource("", clazz);
    if (!classesUrl.isPresent()) {
      // 基本不可能
      return path;
    }
    Optional<String> classesPath = UrlHelper.path(classesUrl.get());
    if (!classesPath.isPresent()) {
      return path;
    }
    return classesPath.get().concat(path);
  }

}
