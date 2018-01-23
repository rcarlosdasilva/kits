package io.github.rcarlosdasilva.kits.cache;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 内存缓存助手
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class MemoryCacheHelper {

  public static final int DEFAULT_EXPIRE_AFTER_ACCESS = 10;
  public static final int DEFAULT_EXPIRE_AFTER_WRITE = 30;
  public static final int DEFAULT_MAX_SIZE = 5000;
  private static final String DEFAULT_CACHE_NAME = "__default_cache__";
  private static final Map<String, Cache<String, Object>> cacheManager = Maps.newConcurrentMap();

  static {
    create(DEFAULT_CACHE_NAME, MemoryCacheConfig.getDefault());
  }

  private static Cache<String, Object> cache(String target) {
    Cache<String, Object> cache = cacheManager.get(target);
    if (cache == null) {
      synchronized (MemoryCacheHelper.class) {
        if (cache == null) {
          cache = build(target, MemoryCacheConfig.getDefault());
        }
      }
    }
    return cache;
  }

  /**
   * 创建一个新的cache容器.
   *
   * @param name   cache名
   * @param config 配置
   */
  public static void create(String name, MemoryCacheConfig config) {
    build(name, config);
  }

  private static Cache<String, Object> build(String name, MemoryCacheConfig config) {
    Preconditions.checkNotNull(name);
    Preconditions.checkNotNull(config);

    CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
    if (config.getInitialCapacity() >= 0) {
      builder.initialCapacity(config.getInitialCapacity());
    }
    if (config.getConcurrencyLevel() >= 0) {
      builder.concurrencyLevel(config.getConcurrencyLevel());
    }
    if (config.getExpireAfterAccess() >= 0) {
      builder.expireAfterAccess(config.getExpireAfterAccess(), config.getTimeUnit());
    }
    if (config.getExpireAfterWrite() >= 0) {
      builder.expireAfterWrite(config.getExpireAfterWrite(), config.getTimeUnit());
    }
    if (config.getMaximumSize() >= 0) {
      builder.maximumSize(config.getMaximumSize());
    }
    if (config.getMaximumWeight() >= 0) {
      builder.maximumWeight(config.getMaximumWeight());
    }
    if (config.getRefreshAfterWrite() >= 0) {
      builder.refreshAfterWrite(config.getRefreshAfterWrite(), config.getTimeUnit());
    }
    Cache<String, Object> cache = builder.build();
    cacheManager.put(name, cache);
    return cache;
  }

  public static Object get(String key) {
    return get(key, Object.class);
  }

  public static <T> T get(String key, Class<T> clazz) {
    return get(DEFAULT_CACHE_NAME, key, clazz);
  }

  public static Object get(String key, Object defaultValue) {
    Object value = get(key, Object.class);
    return value == null ? defaultValue : value;
  }

  public static <T> T get(String key, Class<T> clazz, T defaultValue) {
    T value = get(DEFAULT_CACHE_NAME, key, clazz);
    return value == null ? defaultValue : value;
  }

  public static Object get(String target, String key) {
    return get(target, key, Object.class);
  }

  @SuppressWarnings("unchecked")
  public static <T> T get(String target, String key, Class<T> clazz) {
    return (T) cache(target).getIfPresent(key);
  }

  public static Object get(String target, String key, Object defaultValue) {
    Object value = get(target, key, Object.class, defaultValue);
    return value == null ? defaultValue : value;
  }

  @SuppressWarnings("unchecked")
  public static <T> T get(String target, String key, Class<T> clazz, T defaultValue) {
    T value = (T) cache(target).getIfPresent(key);
    return value == null ? defaultValue : value;
  }

  public static void put(String key, Object value) {
    put(DEFAULT_CACHE_NAME, key, value);
  }

  public static void put(String target, String key, Object value) {
    cache(target).put(key, value);
  }

  public static void remove(String key) {
    remove(DEFAULT_CACHE_NAME, key);
  }

  public static void remove(String target, String key) {
    cache(target).invalidate(key);
  }

  public static boolean exists(String key) {
    return exists(DEFAULT_CACHE_NAME, key);
  }

  public static boolean exists(String target, String key) {
    return cache(target).getIfPresent(key) != null;
  }

  public static void empty() {
    empty(DEFAULT_CACHE_NAME);
  }

  public static void empty(String target) {
    cache(target).invalidateAll();
  }

  /**
   * cache容器是否存在.
   *
   * @param target cache名
   * @return true/false
   */
  public static boolean hadCache(String target) {
    return cacheManager.get(target) != null;
  }

  /**
   * 删除Cache容器.
   *
   * @param target cache名
   */
  public static void discardCache(String target) {
    cacheManager.remove(target);
  }

  /**
   * 缓存配置，参考Guava的CacheBuilder选项
   *
   * @author Dean Zhao (rcarlosdasilva@qq.com)
   */
  static class MemoryCacheConfig {

    private static final int UNCONFIG_INT = -1;

    private static final MemoryCacheConfig DEFAULT_CONFIG = new MemoryCacheConfig();

    static {
      DEFAULT_CONFIG.maximumSize = DEFAULT_MAX_SIZE;
      DEFAULT_CONFIG.expireAfterAccess = DEFAULT_EXPIRE_AFTER_ACCESS;
    }

    private int initialCapacity = UNCONFIG_INT;
    private int concurrencyLevel = UNCONFIG_INT;
    private long maximumSize = UNCONFIG_INT;
    private long maximumWeight = UNCONFIG_INT;
    private long expireAfterWrite = UNCONFIG_INT;
    private long expireAfterAccess = UNCONFIG_INT;
    private long refreshAfterWrite = UNCONFIG_INT;
    private TimeUnit timeUnit = TimeUnit.MINUTES;

    public static MemoryCacheConfig getDefault() {
      return DEFAULT_CONFIG;
    }

    public int getInitialCapacity() {
      return initialCapacity;
    }

    public void setInitialCapacity(int initialCapacity) {
      this.initialCapacity = initialCapacity;
    }

    public int getConcurrencyLevel() {
      return concurrencyLevel;
    }

    public void setConcurrencyLevel(int concurrencyLevel) {
      this.concurrencyLevel = concurrencyLevel;
    }

    public long getMaximumSize() {
      return maximumSize;
    }

    public void setMaximumSize(long maximumSize) {
      this.maximumSize = maximumSize;
    }

    public long getMaximumWeight() {
      return maximumWeight;
    }

    public void setMaximumWeight(long maximumWeight) {
      this.maximumWeight = maximumWeight;
    }

    public long getExpireAfterWrite() {
      return expireAfterWrite;
    }

    public void setExpireAfterWrite(long expireAfterWrite) {
      this.expireAfterWrite = expireAfterWrite;
    }

    public long getExpireAfterAccess() {
      return expireAfterAccess;
    }

    public void setExpireAfterAccess(long expireAfterAccess) {
      this.expireAfterAccess = expireAfterAccess;
    }

    public long getRefreshAfterWrite() {
      return refreshAfterWrite;
    }

    public void setRefreshAfterWrite(long refreshAfterWrite) {
      this.refreshAfterWrite = refreshAfterWrite;
    }

    public TimeUnit getTimeUnit() {
      return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
      this.timeUnit = timeUnit;
    }

  }

}
