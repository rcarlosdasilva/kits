package io.github.rcarlosdasilva.kits.net.http;

import okhttp3.Headers;

import java.io.InputStream;

public class ResponseDigest {

  private int code;
  private Headers headers;
  private String message;
  private long contentLength;
  private String stringValue;
  private InputStream streamValue;

  private ResponseDigest() {
  }

  public static ResponseDigest digest(int code, String message, Headers headers, long contentLength,
                                      String value) {
    ResponseDigest digest = new ResponseDigest();
    digest.code = code;
    digest.message = message;
    digest.headers = headers;
    digest.contentLength = contentLength;
    digest.stringValue = value;
    return digest;
  }

  public static ResponseDigest digest(int code, String message, Headers headers, long contentLength,
                                      InputStream value) {
    ResponseDigest digest = new ResponseDigest();
    digest.code = code;
    digest.message = message;
    digest.headers = headers;
    digest.contentLength = contentLength;
    digest.streamValue = value;
    return digest;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public boolean isSuccessful() {
    return code >= 200 && code < 300;
  }

  public boolean isRedirect() {
    switch (code) {
      case HttpStatusCode.PERMANENT_REDIRECT:
      case HttpStatusCode.TEMPORARY_REDIRECT:
      case HttpStatusCode.MULTIPLE_CHOICES:
      case HttpStatusCode.MOVED_PERMANENTLY:
      case HttpStatusCode.FOUND:
      case HttpStatusCode.SEE_OTHER:
        return true;
      default:
        return false;
    }
  }

  public Headers getHeaders() {
    return headers;
  }

  public long getContentLength() {
    return contentLength;
  }

  public String getStringValue() {
    return stringValue;
  }

  public InputStream getStreamValue() {
    return streamValue;
  }

}
