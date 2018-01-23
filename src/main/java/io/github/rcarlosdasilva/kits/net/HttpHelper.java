package io.github.rcarlosdasilva.kits.net;

import com.google.common.collect.Lists;
import io.github.rcarlosdasilva.kits.net.http.*;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Http请求工具
 *
 * @author Dean Zhao (rcarlosdasilva@qq.com)
 */
public class HttpHelper {

  public static final int TIMEOUT_OF_CONNECT_DEFAULT = 5;
  public static final int TIMEOUT_OF_READ_DEFAULT = 60;
  public static final int TIMEOUT_OF_WRITE_DEFAULT = 60;
  private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
  private static final MediaType XML_TYPE = MediaType.parse("application/xml; charset=utf-8");
  private static final MediaType MULTI_FORM_TYPE = MultipartBody.FORM;
  private static OkHttpClient client = null;
  private static int connectTimeout = TIMEOUT_OF_CONNECT_DEFAULT;
  private static int readTimeout = TIMEOUT_OF_READ_DEFAULT;
  private static int writeTimeout = TIMEOUT_OF_WRITE_DEFAULT;

  private static Request generatePlainRequest(String url, HttpMethod method, String content,
                                              ContentType type) {
    MediaType mediaType = type == ContentType.JSON ? JSON_TYPE : XML_TYPE;
    if (content == null) {
      content = "";
    }

    okhttp3.Request.Builder builder = new Request.Builder().url(url);
    Request request = null;
    switch (method) {
      case GET: {
        request = builder.build();
        break;
      }
      case HEAD: {
        request = builder.head().build();
        break;
      }
      case POST: {
        request = builder.post(RequestBody.create(mediaType, content)).build();
        break;
      }
      case PUT: {
        request = builder.put(RequestBody.create(mediaType, content)).build();
        break;
      }
      case PATCH: {
        request = builder.patch(RequestBody.create(mediaType, content)).build();
        break;
      }
      case DELETE: {
        request = builder.delete(RequestBody.create(mediaType, content)).build();
        break;
      }
      default:
        request = null;
    }

    if (request == null) {
      throw new RuntimeException("Unsupported or unknown request method");
    }

    return request;
  }

  private static Request generateFormRequest(String url, HttpMethod method, List<FormData> form) {
    Request.Builder builder = new Request.Builder().url(url);
    FormBody.Builder formBuilder = new FormBody.Builder();
    if (form != null) {
      for (FormData data : form) {
        formBuilder.add(data.getKey(), data.getValue());
      }
    }

    Request request = null;
    switch (method) {
      case POST: {
        request = builder.post(formBuilder.build()).build();
        break;
      }
      case PUT: {
        request = builder.put(formBuilder.build()).build();
        break;
      }
      case PATCH: {
        request = builder.patch(formBuilder.build()).build();
        break;
      }
      case DELETE: {
        request = builder.delete(formBuilder.build()).build();
        break;
      }
      default:
        request = null;
    }

    if (request == null) {
      throw new RuntimeException("Unsupported or unknown request method");
    }

    return request;
  }

  public static void rebuild(int connectTimeout, int readTimeout, int writeTimeout) {
    HttpHelper.connectTimeout = connectTimeout;
    HttpHelper.readTimeout = readTimeout;
    HttpHelper.writeTimeout = writeTimeout;

    client = null;
  }

  private static OkHttpClient client() {
    if (client == null) {
      synchronized (HttpHelper.class) {
        if (client == null) {
          client = new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS)
              .readTimeout(readTimeout, TimeUnit.SECONDS)
              .writeTimeout(writeTimeout, TimeUnit.SECONDS).build();
        }
      }
    }
    return client;
  }

  /**
   * 发送请求，Content-Type = application/json或application/xml.
   *
   * @param url     请求地址
   * @param method  请求方法
   * @param content 请求参数体
   * @param type    指定请求内容格式，JSON或XML
   * @return response字符串
   */
  public static ResponseDigest requestWithBodyContent(String url, HttpMethod method, String content,
                                                      ContentType type) {
    Request request = generatePlainRequest(url, method, content, type);
    Response response = null;
    try {
      response = client().newCall(request).execute();
      return ResponseDigest.digest(response.code(), response.message(), response.headers(),
          response.body().contentLength(), response.body().string());
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    } finally {
      if (response != null) {
        response.close();
      }
    }
  }

  /**
   * 发送请求，并返回二进制流，Content-Type = application/json或application/xml.
   *
   * @param url     请求地址
   * @param method  请求方法
   * @param content 请求参数体
   * @param type    指定请求内容格式，JSON或XML
   * @return response二进制流
   */
  public static ResponseDigest requestStreamWithBodyContent(String url, HttpMethod method,
                                                            String content, ContentType type) {
    Request request = generatePlainRequest(url, method, content, type);
    Response response = null;
    try {
      response = client().newCall(request).execute();
      return ResponseDigest.digest(response.code(), response.message(), response.headers(),
          response.body().contentLength(), response.body().byteStream());
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * 发送请求，带Form表单数据，Content-Type = application/x-www-form-urlencoded.
   * <p>
   * <p>
   * 只支持POST，PUT，PATCH，DELETE方法
   *
   * @param url    地址
   * @param method 请求方法
   * @param form   表单数据.
   * @return response字符串
   */
  public static ResponseDigest requestWithForm(String url, HttpMethod method, List<FormData> form) {
    Request request = generateFormRequest(url, method, form);
    Response response = null;
    try {
      response = client().newCall(request).execute();
      return ResponseDigest.digest(response.code(), response.message(), response.headers(),
          response.body().contentLength(), response.body().string());
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    } finally {
      if (response != null) {
        response.close();
      }
    }
  }

  /**
   * 发送请求，带Form表单数据，并返回二进制流，Content-Type = application/x-www-form-urlencoded.
   * <p>
   * <p>
   * 只支持POST，PUT，PATCH，DELETE方法
   *
   * @param url    地址
   * @param method 请求方法
   * @param form   表单数据.
   * @return response二进制流
   */
  public static ResponseDigest requestStreamWithForm(String url, HttpMethod method,
                                                     List<FormData> form) {
    Request request = generateFormRequest(url, method, form);
    Response response = null;
    try {
      response = client().newCall(request).execute();
      return ResponseDigest.digest(response.code(), response.message(), response.headers(),
          response.body().contentLength(), response.body().byteStream());
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * 以POST方法上传一个Multipart数据.
   *
   * @param url            请求地址
   * @param multiFile      文件信息
   * @param additionalData 附加表单数据
   * @return response字符串
   */
  public static ResponseDigest requestWithFile(String url, MultiFile multiFile,
                                               List<FormData> additionalData) {
    return requestWithFile(url, Lists.newArrayList(multiFile), additionalData);
  }

  /**
   * 以POST方法上传一个Multipart数据，内含多个文件，Content-Type = multipart/form-data.
   *
   * @param url            请求地址
   * @param multiFiles     多个文件信息
   * @param additionalData 附加表单数据
   * @return response字符串
   */
  public static ResponseDigest requestWithFile(String url, List<MultiFile> multiFiles,
                                               List<FormData> additionalData) {
    okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
    builder.setType(MULTI_FORM_TYPE);
    if (additionalData != null) {
      for (FormData formData : additionalData) {
        builder.addFormDataPart(formData.getKey(), formData.getValue());
      }
    }

    for (MultiFile multiFile : multiFiles) {
      MediaType mediaType = MediaType.parse(multiFile.getContentType().getText());
      builder.addFormDataPart(multiFile.getFileKey(), multiFile.getFileName(),
          RequestBody.create(mediaType, multiFile.getFile()));
    }

    RequestBody body = builder.build();
    Request request = new Request.Builder().url(url).post(body).build();
    Response response = null;
    try {
      response = client().newCall(request).execute();
      return ResponseDigest.digest(response.code(), response.message(), response.headers(),
          response.body().contentLength(), response.body().string());
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    } finally {
      if (response != null) {
        response.close();
      }
    }
  }

}
