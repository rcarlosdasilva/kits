package io.github.rcarlosdasilva.kits.net;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import io.github.rcarlosdasilva.kits.convention.PatternProvider;

/**
 * 网络地址工具类
 * 
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class AddressHelper {

  private static final String X_FORWARDED_FOR = "X-Forwarded-For";
  private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
  private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
  private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
  private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

  /**
   * 获取请求的项目模块路径，以/开头.
   * 
   * @param request
   *          {@link HttpServletRequest}
   * @return 相对路径
   */
  public static String getModuleUri(HttpServletRequest request) {
    if (request == null) {
      return null;
    }
    return request.getContextPath();
  }

  /**
   * 获取请求的相对路径，以/开头，不包含项目模块路径.
   * 
   * @param request
   *          {@link HttpServletRequest}
   * @return 相对路径
   */
  public static String getRelativeUri(HttpServletRequest request) {
    if (request == null) {
      return null;
    }
    return request.getServletPath();
  }

  /**
   * 获取请求的绝对路径，以/开头，包含项目模块路径.
   * 
   * @param request
   *          {@link HttpServletRequest}
   * @return 绝对路径
   */
  public static String getAbsoluteUri(HttpServletRequest request) {
    if (request == null) {
      return null;
    }
    return request.getRequestURL().toString();
  }

  /**
   * 判断给定的ip字符串是否合法.
   * 
   * @param ip
   *          ip地址
   * @return 是否合法
   */
  public static boolean isLegalIp(String ip) {
    if (ip == null) {
      return false;
    }
    return ip.matches(PatternProvider.IP4);
  }

  /**
   * 获取远程ip地址.
   * 
   * @param request
   *          {@link HttpServletRequest}
   * @return 远程ip
   */
  public static String remoteIp(HttpServletRequest request) {
    if (request == null) {
      return null;
    }

    String ip = remoteIpFromHeader(request, X_FORWARDED_FOR);

    if (!Strings.isNullOrEmpty(ip) && ip.contains(",")) {
      Iterable<String> ips = Splitter.on(",").split(ip);
      ip = ips.iterator().next();
      if (!isLegalIp(ip)) {
        ip = null;
      }
    }

    if (Strings.isNullOrEmpty(ip)) {
      ip = remoteIpFromHeader(request, PROXY_CLIENT_IP);
    }
    if (Strings.isNullOrEmpty(ip)) {
      ip = remoteIpFromHeader(request, WL_PROXY_CLIENT_IP);
    }
    if (Strings.isNullOrEmpty(ip)) {
      ip = remoteIpFromHeader(request, HTTP_CLIENT_IP);
    }
    if (Strings.isNullOrEmpty(ip)) {
      ip = remoteIpFromHeader(request, HTTP_X_FORWARDED_FOR);
    }
    if (Strings.isNullOrEmpty(ip)) {
      ip = request.getRemoteAddr();
    }
    return null;
  }

  /**
   * 从request.header中获取指定内容.
   * 
   * @param request
   *          {@link HttpServletRequest}
   * @param key
   *          键
   * @return 内容
   */
  private static String remoteIpFromHeader(HttpServletRequest request, String key) {
    String ip = request.getHeader(key);
    if (ip != null && isLegalIp(ip)) {
      return ip;
    }
    return null;
  }

}
