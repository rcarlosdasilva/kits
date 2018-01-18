package io.github.rcarlosdasilva.kits.string;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.base.Ascii;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import io.github.rcarlosdasilva.kits.convention.PatternProvider;

/**
 * 文本帮助类
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public final class TextHelper {

  private static final Joiner NON_SEPARATOR_JOINER = Joiner.on("");
  private static final Random DEFAULT_RANDOM = new Random();

  private TextHelper() {
  }

  public static String value(final String value, final String defaultValue) {
    if (value == null || value.trim().length() == 0) {
      return defaultValue;
    }
    return value;
  }

  /**
   * 拼接多个字符串.
   * <p>
   * <pre>
   * TextHelper.concat("1", "", "2", null, "3"); // return "123";
   * </pre>
   *
   * @param parts an array of strings to joint
   * @return processed string
   */
  public static String concat(final String... parts) {
    return NON_SEPARATOR_JOINER.join(clean(parts, true));
  }

  /**
   * 拼接多个字符串.
   * <p>
   * <pre>
   * List strings = new ArrayList();
   * strings.add("1");
   * strings.add("");
   * strings.add("2");
   * strings.add(null);
   * strings.add("3");
   * TextHelper.concat(strings); // return "123";
   * </pre>
   *
   * @param parts an array of strings to joint
   * @return processed string
   */
  public static String concat(final Iterable<String> parts) {
    return NON_SEPARATOR_JOINER.join(clean(parts, true));
  }

  /**
   * 拼接多个字符串，之间用separator间隔.
   * <p>
   * <pre>
   * // return "1::2:3";
   * TextHelper.concat(":", "1", "", "2", null, "3");
   * </pre>
   *
   * @param separator separator
   * @param parts     an array of strings to joint
   * @return processed string
   */
  public static String join(String separator, final String... parts) {
    if (Strings.isNullOrEmpty(separator)) {
      return concat(parts);
    }

    return Joiner.on(separator).join(clean(parts, true));
  }

  /**
   * 拼接多个字符串，之间用separator间隔.
   * <p>
   * <pre>
   * List strings = new ArrayList();
   * strings.add("1");
   * strings.add("");
   * strings.add("2");
   * strings.add(null);
   * strings.add("3");
   * TextHelper.join(":", strings); // return "1::2:3";
   * </pre>
   *
   * @param parts     an array of strings to joint
   * @param separator separator
   * @return processed string
   */
  public static String join(String separator, final Iterable<String> parts) {
    if (Strings.isNullOrEmpty(separator)) {
      return concat(parts);
    }

    return Joiner.on(separator).join(clean(parts, true));
  }

  /**
   * 获取指定下标的单个字母字符串，index为负数时，从后向前找，index从0开始计数，0返回左侧第一个字符.
   * <p>
   * <pre>
   * TextHelper.at("0123456789", 4); // return "4";
   * TextHelper.at("0123456789", -4); // return "6";
   * </pre>
   *
   * @param source original string
   * @param index  location to find
   * @return processed string
   */
  public static String at(final String source, int index) {
    if (source == null) {
      return null;
    }

    int size = source.length();
    if (index < 0) {
      index = size + index;
    }

    if (index < size && index >= 0) {
      return String.valueOf(source.charAt(index));
    } else {
      return null;
    }
  }

  /**
   * 获取从指定下标开始之前/之后的子字符串，index为负数时，从后向前找，子字符串的长度由length决定，length为负数时，向前截取.
   * <p>
   * <pre>
   * TextHelper.sub("0123456789", 3, 3); // return "345"
   * TextHelper.sub("0123456789", 3, -3); // return "123"
   * TextHelper.sub("0123456789", -3, 3); // return "789"
   * TextHelper.sub("0123456789", -3, -3); // return "567"
   *
   * TextHelper.sub("0123456789", 5, 8); // return "56789"
   * TextHelper.sub("0123456789", -5, -8); // return "012345"
   * </pre>
   *
   * @param source original string
   * @param index  location to find
   * @param length length to cut out
   * @return processed string
   */
  public static String sub(final String source, int index, int length) {
    if (source == null) {
      return null;
    }
    if (length == 0) {
      return "";
    }

    boolean front = length < 0;
    int size = source.length();
    if (index < 0) {
      index = size + index;
    }

    if (index < size && index >= 0) {
      if (front) {
        index += 1;
      }

      length = index + length;
      length = length > size ? size : length;
      length = length < 0 ? 0 : length;

      if (index < length) {
        return source.substring(index, length);
      } else {
        return source.substring(length, index);
      }
    } else {
      return "";
    }
  }

  /**
   * 截取在给定开始、结束字符串之间的子字符串，before指定截取的子字符串前的字符串，after指定子字符串之后的.
   * <p>
   * <pre>
   * TextHelper.sub("abc0123xyz", "abc", "xyz"); // return "0123"
   * TextHelper.sub("abcabc0123xyzxyz", "abc", "xyz"); // return "0123"
   * </pre>
   *
   * @param source original string
   * @param before string before substring
   * @param after  string after substring
   * @return processed string
   */
  public static String sub(final String source, final String before, final String after) {
    String substring = trim(source, before, -1);
    substring = trim(substring, after, 1);
    return substring;
  }

  /**
   * 统计子字符串出现的次数.
   * <p>
   * <pre>
   * TextHelper.subTimes("AbcdFgBChbcdeAbc", "bc", true); // return 3
   * TextHelper.subTimes("AbcdFgBChbcdeAbc", "bc", false); // return 4
   * </pre>
   *
   * @param source        original string
   * @param looking4      string to find out
   * @param caseSensitive should be case sensitive
   * @return processed string
   */
  public static long subTimes(final String source, final String looking4,
                              final boolean caseSensitive) {
    if (source == null || looking4 == null) {
      return -1;
    }

    String sourceCopy = caseSensitive ? source : source.toLowerCase();
    String looking4Copy = caseSensitive ? looking4 : looking4.toLowerCase();

    int times = 0;
    int position = 0;
    while (true) {
      position = sourceCopy.indexOf(looking4Copy, position);
      if (position == -1) {
        break;
      }
      position = position + looking4.length();
      times++;
    }

    return times;
  }

  /**
   * 获取指定的字符区间内容.
   * <p>
   * <pre>
   * // return ["abc", "def"]
   * TextHelper.between("[abc] xyz [def]", "[", "]");
   * </pre>
   *
   * @param source original string
   * @param start  start string or character
   * @param end    end string or character
   * @return processed strings collection
   */
  public static Collection<String> between(final String source, final String start,
                                           final String end) {
    if (source == null || start == null || end == null) {
      return null;
    }

    String[] parts = source.split(end);
    for (int i = 0; i < parts.length; i++) {
      int startAt = parts[i].indexOf(start);
      if (startAt < 0) {
        parts[i] = null;
        continue;
      }
      startAt += start.length();
      parts[i] = parts[i].substring(startAt);
    }

    return clean(parts, false);
  }

  /**
   * 去掉字符串中多余空格，只保留单词间的一个空格.
   * <p>
   * <pre>
   * // return "sample sentence.";
   * TextHelper.simplifyWhitespace("  sample   sentence.   ");
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String simplifyWhitespace(String source) {
    if (source == null) {
      return null;
    }
    return source.trim().replaceAll("\\s\\s+", " ");
  }

  /**
   * 查找是否包含指定的字符串.
   * <p>
   * <pre>
   * TextHelper.have("Abcde", "bcd"); // return true;
   * </pre>
   *
   * @param source   original string
   * @param looking4 string to find out
   * @return true if have string looking for
   */
  public static boolean have(final String source, final String looking4) {
    if (source == null || looking4 == null) {
      return false;
    }
    return source.contains(looking4);
  }

  /**
   * 查找是否包含指定的字符串，忽略大小写.
   * <p>
   * <pre>
   * TextHelper.haveIgnoreCase("Abcde", "abc"); // return true;
   * </pre>
   *
   * @param source   original string
   * @param looking4 string to find out
   * @return true if have string looking for without case
   */
  public static boolean haveIgnoreCase(final String source, final String looking4) {
    if (source == null || looking4 == null) {
      return false;
    }
    return source.toLowerCase().contains(looking4.toLowerCase());
  }

  /**
   * 查找是否包含给的所有字符串.
   * <p>
   * <pre>
   * TextHelper.haveAll("AbcdeFG", "bcd", "FG"); // return true;
   * TextHelper.haveAll("AbcdeFG", "bcd", "fg"); // return false;
   * </pre>
   *
   * @param source   original string
   * @param looking4 strings to find out
   * @return true if have all strings looking for
   */
  public static boolean haveAll(final String source, final String... looking4) {
    if (source == null || looking4 == null || looking4.length == 0) {
      return false;
    }

    return Iterables.all(Lists.newArrayList(looking4), new Predicate<String>() {

      @Override
      public boolean apply(String input) {
        return have(source, input);
      }

      @Override
      public boolean test(String input) {
        return true;
      }
    });
  }

  /**
   * 查找是否包含给的所有字符串，忽略大小写.
   * <p>
   * <pre>
   * TextHelper.haveAllIgnoreCase("AbcdeFG", "bcd", "FG"); // return true;
   * TextHelper.haveAllIgnoreCase("AbcdeFG", "bcd", "fg"); // return true;
   * </pre>
   *
   * @param source   original string
   * @param looking4 strings to find out
   * @return true if have all strings looking for without case
   */
  public static boolean haveAllIgnoreCase(final String source, final String... looking4) {
    if (source == null || looking4 == null || looking4.length == 0) {
      return false;
    }

    final String sourceCopy = source.toLowerCase();
    return Iterables.all(Lists.newArrayList(looking4), new Predicate<String>() {

      @Override
      public boolean apply(String input) {
        if (input != null) {
          return have(sourceCopy, input.toLowerCase());
        }
        return false;
      }

      @Override
      public boolean test(String input) {
        return true;
      }
    });
  }

  /**
   * 查找是否包含给的任意字符串.
   * <p>
   * <pre>
   * TextHelper.haveAny("AbcdeFG", "bcd", "fg"); // return true;
   * TextHelper.haveAny("AbcdeFG", "123", "fg"); // return false;
   * </pre>
   *
   * @param source   original string
   * @param looking4 strings to find out
   * @return true if have any string looking for
   */
  public static boolean haveAny(final String source, final String... looking4) {
    if (source == null || looking4 == null || looking4.length == 0) {
      return false;
    }

    return Iterables.any(Lists.newArrayList(looking4), new Predicate<String>() {

      @Override
      public boolean apply(String input) {
        return have(source, input);
      }

      @Override
      public boolean test(String input) {
        return true;
      }
    });
  }

  /**
   * 查找是否包含给的任意字符串，忽略大小写.
   * <p>
   * <pre>
   * TextHelper.haveAnyIgnoreCase("AbcdeFG", "BCD", "xyz"); // return true;
   * TextHelper.haveAnyIgnoreCase("AbcdeFG", "BCD123", "xyz"); // return false;
   * </pre>
   *
   * @param source   original string
   * @param looking4 strings to find out
   * @return true if have any string looking for without case
   */
  public static boolean haveAnyIgnoreCase(final String source, final String... looking4) {
    if (source == null || looking4 == null || looking4.length == 0) {
      return false;
    }

    final String sourceCopy = source.toLowerCase();
    return Iterables.any(Lists.newArrayList(looking4), new Predicate<String>() {

      @Override
      public boolean apply(String input) {
        if (input != null) {
          return have(sourceCopy, input.toLowerCase());
        }
        return false;
      }

      @Override
      public boolean test(String input) {
        return true;
      }
    });
  }

  /**
   * 确保字符串以给定的前缀开始.
   * <p>
   * <pre>
   * TextHelper.insureStartsWith("xyz", "abc", true); // return abcxyz
   * TextHelper.insureStartsWith("ABCxyz", "abc", true); // return abcABCxyz
   * TextHelper.insureStartsWith("ABCxyz", "abc", false); // return ABCxyz
   * </pre>
   *
   * @param source        original string
   * @param prefix        prefix
   * @param caseSensitive should be case sensitive
   * @return processed string
   */
  public static String insureStartsWith(final String source, final String prefix,
                                        final boolean caseSensitive) {
    if (source == null) {
      return null;
    }
    if (prefix == null) {
      return source;
    }

    if (caseSensitive) {
      return source.startsWith(prefix) ? source : (prefix + source);
    } else {
      String sourceCopy = source.toLowerCase();
      String prefixCopy = prefix.toLowerCase();
      return sourceCopy.startsWith(prefixCopy) ? source : (prefix + source);
    }
  }

  /**
   * 确保字符串以给定的后缀结束.
   * <p>
   * <pre>
   * TextHelper.insureEndsWith("abc", "xyz", true); // return abcxyz
   * TextHelper.insureEndsWith("abcXYZ", "xyz", true); // return abcXYZxyz
   * TextHelper.insureEndsWith("abcXYZ", "xyz", false); // return abcXYZ
   * </pre>
   *
   * @param source        original string
   * @param suffix        suffix
   * @param caseSensitive should be case sensitive
   * @return processed string
   */
  public static String insureEndsWith(final String source, final String suffix,
                                      final boolean caseSensitive) {
    if (source == null) {
      return null;
    }
    if (suffix == null) {
      return source;
    }

    if (caseSensitive) {
      return source.endsWith(suffix) ? source : (source + suffix);
    } else {
      String sourceCopy = source.toLowerCase();
      String suffixCopy = suffix.toLowerCase();
      return sourceCopy.endsWith(suffixCopy) ? source : (source + suffix);
    }
  }

  /**
   * 判断字符串是否是以给定的字符串集合中任意一个后缀结尾.
   *
   * @param source           original string
   * @param suffixCollection suffix collection
   * @return boolean
   */
  public static boolean endsWith(final String source, final String... suffixCollection) {
    if (source != null && suffixCollection != null && suffixCollection.length > 0) {
      for (String suffix : suffixCollection) {
        if (source.endsWith(suffix)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 判断字符串是否是以给定的字符串集合中任意一个前缀开头.
   *
   * @param source           original string
   * @param prefixCollection prefix collection
   * @return boolean
   */
  public static boolean startsWith(final String source, final String... prefixCollection) {
    return startsWhich(source, prefixCollection) != null;
  }

  /**
   * 判断字符串是否是以给定的字符串集合中任意一个前缀开头.
   *
   * @param source           original string
   * @param prefixCollection prefix collection
   * @return boolean
   */
  public static boolean startsWith(final String source, final List<String> prefixCollection) {
    return startsWhich(source, prefixCollection) != null;
  }

  /**
   * 判断字符串是否是以给定的字符串集合中任意一个前缀开头.
   *
   * @param source           original string
   * @param prefixCollection prefix collection
   * @return boolean
   */
  public static String startsWhich(final String source, final String... prefixCollection) {
    return startsWhich(source, Lists.newArrayList(prefixCollection));
  }

  /**
   * 判断字符串是否是以给定的字符串集合中任意一个前缀开头.
   *
   * @param source           original string
   * @param prefixCollection prefix collection
   * @return boolean
   */
  public static String startsWhich(final String source, final List<String> prefixCollection) {
    if (source != null && prefixCollection != null && !prefixCollection.isEmpty()) {
      for (String prefix : prefixCollection) {
        if (source.startsWith(prefix)) {
          return prefix;
        }
      }
    }
    return null;
  }

  /**
   * 统计字符串中处以给定字符结尾出现的次数.
   *
   * @param source   original string
   * @param looking4 char
   * @return count
   */
  public static int endsCount(final String source, final char looking4) {
    if (source == null || source.length() == 0) {
      return 0;
    }

    char[] sequence = source.toCharArray();
    int count = 0;
    for (int i = sequence.length - 1; i > 0; i--) {
      char ch = sequence[i];
      if (looking4 == ch) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  /**
   * 统计字符串中处以给定字符开始出现的次数.
   *
   * @param source   original string
   * @param looking4 char
   * @return count
   */
  public static int startsCount(final String source, final char looking4) {
    if (source == null || source.length() == 0) {
      return 0;
    }

    char[] sequence = source.toCharArray();
    int count = 0;
    for (int i = 0; i < sequence.length; i++) {
      char ch = sequence[i];
      if (looking4 == ch) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  /**
   * 插入子字符串，index为负数时，从字符串尾部定位.
   * <p>
   * <pre>
   * TextHelper.insert("0123456789", "ABC", 3); // return "012ABC3456789";
   * TextHelper.insert("0123456789", "ABC", -3); // return "0123456ABC789";
   * </pre>
   *
   * @param source original string
   * @param part   string to insert
   * @param index  The index to insert substr
   * @return processed string
   */
  public static String insert(final String source, final String part, final int index) {
    if (source == null || Math.abs(index) > source.length()) {
      return null;
    }

    int absPosition = index >= 0 ? index : source.length() + index;
    return concat(source.substring(0, absPosition), part, source.substring(absPosition));
  }

  /**
   * 删除指定的子字符串，默认区分大小写.
   * <p>
   * <pre>
   * TextHelper.remove("abc123abc123abc", "abc"); // return "123123";
   * </pre>
   *
   * @param source original string
   * @param parts  strings to be removed
   * @return processed string
   */
  public static String remove(final String source, final String... parts) {
    return remove(source, 0, parts);
  }

  /**
   * 删除指定的子字符串，默认区分大小写.
   * <p>
   * <pre>
   * TextHelper.remove("abc123abc123abc", 0, "abc"); // return "123123";
   * TextHelper.remove("abc123abc123abc", 1, "abc"); // return "123abc123abc";
   * TextHelper.remove("abc123abc123abc", -1, "abc"); // return "abc123abc123";
   * </pre>
   *
   * @param source original string
   * @param counts remove times
   * @param parts  strings to be removed
   * @return processed string
   */
  public static String remove(final String source, final int counts, final String... parts) {
    return remove(source, counts, true, parts);
  }

  /**
   * 删除指定的子字符串.
   * <p>
   * <pre>
   * // return "123123";
   * TextHelper.remove("abc123abc123abc", 0, false, "abc");
   * // return "123abc123abc";
   * TextHelper.remove("abc123abc123abc", 1, false, "abc");
   * // return "abc123abc123";
   * TextHelper.remove("abc123abc123abc", -1, false, "abc");
   * </pre>
   *
   * @param source        original string
   * @param counts        remove times
   * @param caseSensitive should be case sensitive
   * @param parts         strings to be removed
   * @return processed string
   */
  public static String remove(final String source, final int counts, boolean caseSensitive,
                              final String... parts) {
    if (source == null) {
      return null;
    }
    if (parts == null || parts.length == 0) {
      return source;
    }

    String sourceCopy = caseSensitive ? source : source.toLowerCase();
    if (counts == 0) {
      for (String part : parts) {
        sourceCopy = sourceCopy.replace(caseSensitive ? part : part.toLowerCase(), "");
      }
      return sourceCopy;
    } else {
      for (String part : parts) {
        if (counts > 0) {
          int index = sourceCopy.indexOf(part);
          sourceCopy = index >= 0 ? sourceCopy.substring(part.length()) : sourceCopy;
        } else {
          int index = sourceCopy.lastIndexOf(part);
          sourceCopy = index >= 0
              ? (sourceCopy.substring(0, index) + sourceCopy.substring(index + part.length()))
              : sourceCopy;
        }
      }
      if (counts == 1 || counts == -1) {
        return sourceCopy;
      }
      return remove(sourceCopy, counts > 0 ? counts - 1 : counts + 1, caseSensitive, parts);
    }
  }

  /**
   * 判断是否全为小写.
   * <p>
   * <pre>
   * TextHelper.isAllUpperCase("ABC"); // return false
   * TextHelper.isAllUpperCase("abc"); // return true
   * </pre>
   *
   * @param source original string
   * @return true if all characters in string is lower case
   */
  public static boolean isAllLowerCase(final String source) {
    return source.equals(source.toLowerCase());
  }

  /**
   * 判断是否全为大写.
   * <p>
   * <pre>
   * TextHelper.isAllUpperCase("ABC"); // return true
   * TextHelper.isAllUpperCase("abc"); // return false
   * </pre>
   *
   * @param source original string
   * @return true if all characters in string is upper case
   */
  public static boolean isAllUpperCase(final String source) {
    return source.equals(source.toUpperCase());
  }

  /**
   * 重复给定的字符串N次.
   * <p>
   * <pre>
   * TextHelper.repeat("0", 3); // return "000"
   * TextHelper.repeat("00", 3); // return "000000"
   * </pre>
   *
   * @param source original string
   * @param times  repeat times
   * @return processed string
   */
  public static String repeat(final String source, final int times) {
    if (source == null || times <= 0) {
      return null;
    }
    return Strings.repeat(source, times);
  }

  /**
   * 重复给定的字符串直到字符串达到或超过给定长度.
   * <p>
   * <pre>
   * TextHelper.repeatUntil("0", 3); // return "000"
   * TextHelper.repeatUntil("00", 5); // return "0000"
   * </pre>
   *
   * @param source original string
   * @param length string length
   * @return processed string
   */
  public static String repeatUntil(final String source, final int length) {
    if (source == null || length <= 0) {
      return null;
    }

    StringBuilder result = new StringBuilder(source);
    while (result.length() < length) {
      result.append(source);
    }
    return result.toString();
  }

  /**
   * 补充字符串达到给定长度， length如果为负数，将字符串填充至尾部.
   * <p>
   * <pre>
   * TextHelper.fill("123", "0", 5); // return "00123"
   * TextHelper.fill("123", "0", -5); // return "12300"
   * </pre>
   *
   * @param source original string
   * @param filler string will fill to
   * @param length string length
   * @return processed string
   */
  public static String fill(final String source, final String filler, final int length) {
    if (source == null || length == 0) {
      return null;
    }
    if (filler == null || source.length() > Math.abs(length)) {
      return source;
    }

    String fill = repeatUntil(filler, Math.abs(length) - source.length());
    return length > 0 ? (fill + source) : (source + fill);
  }

  /**
   * 清除集合中所有空字符串.
   * <p>
   * <pre>
   * // return ["123", "abc"]
   * TextHelper.clean(new String[] { "", "123", "", "abc", null }, false);
   * // return ["", "123", "", "abc"]
   * TextHelper.clean(new String[] { "", "123", "", "abc", null }, true);
   * </pre>
   *
   * @param source   an array of strings to clear
   * @param justNull just clean null value
   * @return processed string
   */
  public static Collection<String> clean(final String[] source, final boolean justNull) {
    if (source == null) {
      return null;
    }

    return clean(Lists.newArrayList(source), justNull);
  }

  /**
   * 清除集合中所有空字符串.
   * <p>
   * <pre>
   * // return ["123", "abc"]
   * TextHelper.clean(new String[] { "", "123", "", "abc" });
   * </pre>
   *
   * @param source   an array of strings to clear
   * @param justNull just clean null value
   * @return processed string
   */
  public static Collection<String> clean(final Iterable<String> source, final boolean justNull) {
    if (source == null) {
      return null;
    }

    return Collections2.filter(Lists.newArrayList(source), new Predicate<String>() {

      @Override
      public boolean apply(String input) {
        if (justNull) {
          return input != null;
        } else {
          return input != null && input.trim().length() > 0;
        }
      }

      @Override
      public boolean test(String input) {
        return true;
      }
    });
  }

  /**
   * 获取字符串指定长度的缩略，尾部跟填充物.
   * <p>
   * <pre>
   * // return "Install the plugi...";
   * TextHelper.brief("Install the plugin; Restart Eclipse and go to Window", 20, "...");
   * // return "默认逻辑是当表单验证失败时,把按钮...";
   * TextHelper.brief("默认逻辑是当表单验证失败时,把按钮给变灰色", 20, "...");
   * </pre>
   *
   * @param source original string
   * @param length string length
   * @param filler the brief fill string
   * @return processed string
   */
  public static String brief(final String source, final int length, final String filler) {
    if (source == null) {
      return null;
    }
    if (length <= 0) {
      return "";
    }
    if (length >= source.length()) {
      return source;
    }

    if (Strings.isNullOrEmpty(filler)) {
      return source.substring(0, length);
    } else {
      return source.substring(0, length - filler.length()) + filler;
    }
  }

  /**
   * 使用给定的前缀与后缀包裹指定的字符串.
   * <p>
   * <pre>
   * TextHelper.wrap("abcabcabc", "bc", "[", "]"); // return "a[bc]a[bc]a[bc]"
   * </pre>
   *
   * @param source   original string
   * @param looking4 string to find out
   * @param prefix   prefix
   * @param suffix   suffix
   * @return processed string
   */
  public static String wrap(final String source, final String looking4, final String prefix,
                            final String suffix) {
    if (source == null) {
      return null;
    }

    return source.replace(looking4, concat(prefix, looking4, suffix));
  }

  /**
   * 转换成驼峰式，首字母小写.
   * <p>
   * <pre>
   * TextHelper.camelCase("there is a word"); // return "thereIsAWord"
   * TextHelper.camelCase("there_is_a_word"); // return "thereIsAWord"
   * TextHelper.camelCase("there-is-a-word"); // return "thereIsAWord"
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String camelCase(final String source) {
    if (source == null) {
      return null;
    }
    return lowerFirst(studlyCase(source));
  }

  /**
   * 转换成驼峰式，首字母大写.
   * <p>
   * <pre>
   * TextHelper.studlyCase("there is a word"); // return "ThereIsAWord"
   * TextHelper.studlyCase("there_is_a_word"); // return "ThereIsAWord"
   * TextHelper.studlyCase("there-is-a-word"); // return "ThereIsAWord"
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String studlyCase(final String source) {
    if (source == null) {
      return null;
    }

    String[] words = simplifyWhitespace(source.trim()).split("\\s*(_|-|\\s)\\s*");
    Iterable<String> casedWords = Iterables.transform(Lists.newArrayList(words),
        new Function<String, String>() {

          @Override
          public String apply(String input) {
            if (input == null) {
              return "";
            }
            return upperFirst(input);
          }

        });

    return NON_SEPARATOR_JOINER.join(casedWords);
  }

  /**
   * 转换成Snake Case，以下划线间隔.
   * <p>
   * <pre>
   * TextHelper.snakeCase("there is a word"); // return "there_is_a_word"
   * TextHelper.snakeCase("thereIsAWord"); // return "there_is_a_word"
   * TextHelper.snakeCase("there-is-a-word"); // return "there_is_a_word"
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String snakeCase(final String source) {
    if (source == null) {
      return null;
    }

    String copy = studlyCase(source);
    StringBuilder result = new StringBuilder();
    char[] chars = copy.toCharArray();
    for (char c : chars) {
      if (Character.isUpperCase(c)) {
        result.append('_');
        c = Character.toLowerCase(c);
      }
      result.append(c);
    }
    if (result.charAt(0) == '_') {
      result.deleteCharAt(0);
    }
    return result.toString();
  }

  /**
   * 转换成Kebab Case，以中划线间隔.
   * <p>
   * <pre>
   * TextHelper.kebabCase("there is a word"); // return "there-is-a-word"
   * TextHelper.kebabCase("there_is_a_word"); // return "there-is-a-word"
   * TextHelper.kebabCase("thereIsAWord"); // return "there-is-a-word"
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String kebabCase(final String source) {
    if (source == null) {
      return null;
    }

    return snakeCase(source).replace('_', '-');
  }

  /**
   * 首字母大写.
   * <p>
   * <pre>
   * TextHelper.upperFirst("abcdef"); // return "Abcdef";
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String upperFirst(final String source) {
    if (source == null) {
      return null;
    }
    char[] chars = source.toCharArray();
    if (chars.length > 0) {
      chars[0] = Ascii.toUpperCase(chars[0]);
    }
    return String.valueOf(chars);
  }

  /**
   * 首字母小写.
   * <p>
   * <pre>
   * TextHelper.lowerFirst("ABCDEF"); // return "aBCDEF";
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String lowerFirst(final String source) {
    if (source == null) {
      return null;
    }
    char[] chars = source.toCharArray();
    if (chars.length > 0) {
      chars[0] = Ascii.toLowerCase(chars[0]);
    }
    return String.valueOf(chars);
  }

  /**
   * 清除字符串两侧的空格.
   * <p>
   * <pre>
   * TextHelper.trim("   clean string    "); // return "clean string"
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String trim(final String source) {
    return trim(source, " ");
  }

  /**
   * 修剪字符串两侧指定字符串.
   * <p>
   * <pre>
   * TextHelper.trim("abab123abab", "ab"); // return "123"
   * </pre>
   *
   * @param source original string
   * @param odd    string to be removed both sides
   * @return processed string
   */
  public static String trim(final String source, final String odd) {
    return trim(source, odd, 0);
  }

  /**
   * 按指定方向修剪字符串两侧指定字符串.
   * <p>
   * <pre>
   * TextHelper.trim("abab123abab", "ab", 0); // return "123"
   * TextHelper.trim("abab123abab", "ab", -1); // return "123abab"
   * TextHelper.trim("abab123abab", "ab", 1); // return "abab123"
   * </pre>
   *
   * @param source original string
   * @param odd    string to be removed
   * @param place  0清除两侧，负数清除左侧，正数清除右侧
   * @return processed string
   */
  public static String trim(final String source, final String odd, final int place) {
    if (source == null) {
      return null;
    }
    if (Strings.isNullOrEmpty(odd)) {
      return source;
    }

    int length = source.length();
    int step = odd.length();
    int start = 0;
    int end = length;

    if (place <= 0) {
      while (start < end && source.indexOf(odd, start) == start) {
        start += step;
      }
    }

    if (place >= 0) {
      while (start < end && source.lastIndexOf(odd, end - 1) == (end - step)) {
        end -= step;
      }
    }

    if (start > 0 || end < length) {
      return source.substring(start, end);
    }

    return source;
  }

  /**
   * 生成随机字符串，可用字符可使用 {@link Characters}指定
   * <p>
   * <pre>
   * TextHelper.random(10, Characters.NUMBERS_AND_LETTERS); // return
   *                                                        // "3xlW9fq01z"
   * </pre>
   *
   * @param length  随机字符串长度
   * @param sources 随机字符串可用字符集合
   * @return processed string
   */
  public static String random(final int length, final String... sources) {
    if (length <= 0) {
      return "";
    }

    String source = concat(sources);
    if (Strings.isNullOrEmpty(source)) {
      return "";
    }

    char[] chars = new char[length];
    int size = source.length();
    for (int i = 0; i < length; i++) {
      chars[i] = source.charAt(DEFAULT_RANDOM.nextInt(size));
    }

    return new String(chars);
  }

  /**
   * 只保留纯字符，清除所有的XML或HTML标签
   *
   * @param source      original string
   * @param clean       清除多余空格
   * @param singleSpace 清除多余空格时，保留一个空格
   * @return processed string
   */
  public static String pure(final String source, boolean clean, boolean singleSpace) {
    String pureString = source.replaceAll(PatternProvider.HTML_STRIP, "");
    if (clean) {
      if (singleSpace) {
        return simplifyWhitespace(pureString);
      } else {
        return remove(pureString, " ");
      }
    }
    return pureString;
  }

  /**
   * 删除字符串末尾的换行符。如果字符串不以换行结尾，则什么也不做.
   * <p>
   * <p>
   * 换行符有三种情形："<code>\n</code>"、"<code>\r</code>"、" <code>\r\n</code>"。
   * <p>
   * <pre>
   * TextHelper.chomp(null)          = null
   * TextHelper.chomp("")            = ""
   * TextHelper.chomp("abc \r")      = "abc "
   * TextHelper.chomp("abc\n")       = "abc"
   * TextHelper.chomp("abc\r\n")     = "abc"
   * TextHelper.chomp("abc\r\n\r\n") = "abc\r\n"
   * TextHelper.chomp("abc\n\r")     = "abc\n"
   * TextHelper.chomp("abc\n\rabc")  = "abc\n\rabc"
   * TextHelper.chomp("\r")          = ""
   * TextHelper.chomp("\n")          = ""
   * TextHelper.chomp("\r\n")        = ""
   * </pre>
   *
   * @param source original string
   * @return processed string
   */
  public static String chomp(final String source) {
    int index = -1;
    final String last = at(source, index);

    if (!"\r".equals(last) && !"\n".equals(last)) {
      return source;
    }

    if ("\n".equals(last) && "\r".equals(at(source, -2))) {
      index--;
    }

    return sub(source, --index, -Integer.MAX_VALUE);
  }

  public static String toHexString(byte[] bytes) {
    if (bytes == null || bytes.length <= 0) {
      return null;
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      int v = bytes[i] & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        result.append(0);
      }
      result.append(hv);
    }
    return result.toString();
  }

}
