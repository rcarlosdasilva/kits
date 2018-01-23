package io.github.rcarlosdasilva.kits.net;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

  private static final String DEFAULT_AJAX_HEADER_NAME = "X-Requested-With";
  private static final String DEFAULT_AJAX_HEADER_VALUE = "XMLHttpRequest";

  /**
   * 判断是否是Ajax请求
   *
   * @param request request
   * @return boolean
   */
  public static boolean isAjax(HttpServletRequest request) {
    return request != null && request.getHeader(DEFAULT_AJAX_HEADER_NAME) != null
        && request.getHeader(DEFAULT_AJAX_HEADER_NAME).equals(DEFAULT_AJAX_HEADER_VALUE);
  }

  /**
   * 判断是否像是Ajax请求（头信息不区分大消息）
   *
   * @param request request
   * @return boolean
   */
  public static boolean likeAjax(HttpServletRequest request) {
    if (request == null) {
      return false;
    }

    String value = request.getHeader(DEFAULT_AJAX_HEADER_NAME);
    if (value == null) {
      value = request.getHeader(DEFAULT_AJAX_HEADER_NAME.toLowerCase());
    }
    if (value == null) {
      value = request.getHeader(DEFAULT_AJAX_HEADER_NAME.toUpperCase());
    }

    return value != null && value.equalsIgnoreCase(DEFAULT_AJAX_HEADER_VALUE);
  }

}
