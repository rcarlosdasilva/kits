package io.github.rcarlosdasilva.kits.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import com.google.common.base.Preconditions;

public class StreamHelper {

  private static final int DEFAULT_BUFFER_SIZE = 8192;

  /**
   * 关闭流
   * 
   * @param closed
   *          closed
   */
  public static void close(Closeable closed) {
    if (closed != null) {
      try {
        closed.close();
      } catch (IOException ignore) {
        // can ignore
      }
    }
  }

  /**
   * 从输入流读取内容, 写入到输出流中. 使用指定大小的缓冲区.
   * 
   * @param in
   *          输入流
   * @param out
   *          输出流
   * @throws IOException
   *           输入输出异常
   */
  public static void io(Reader in, Writer out) throws IOException {
    io(in, out, DEFAULT_BUFFER_SIZE);
  }

  /**
   * 从输入流读取内容, 写入到输出流中. 使用指定大小的缓冲区.
   * 
   * @param in
   *          输入流
   * @param out
   *          输出流
   * @param bufferSize
   *          缓冲区大小(字节数)
   * @throws IOException
   *           输入输出异常
   */
  public static void io(Reader in, Writer out, int bufferSize) throws IOException {
    io(in, out, DEFAULT_BUFFER_SIZE);
  }

  /**
   * 从输入流读取内容, 写入到输出流中. 使用指定大小的缓冲区.
   * 
   * @param in
   *          输入流
   * @param out
   *          输出流
   * 
   * @throws IOException
   *           输入输出异常
   */
  public static void io(InputStream in, OutputStream out) throws IOException {
    io(in, out, DEFAULT_BUFFER_SIZE);
  }

  /**
   * 从输入流读取内容, 写入到输出流中. 使用指定大小的缓冲区.
   * 
   * @param in
   *          输入流
   * @param out
   *          输出流
   * @param bufferSize
   *          缓冲区大小(字节数)
   * 
   * @throws IOException
   *           输入输出异常
   */
  private static void io(InputStream in, OutputStream out, int bufferSize) throws IOException {
    Preconditions.checkState(bufferSize > 0);

    byte[] buffer = new byte[bufferSize];
    int amount;

    while ((amount = in.read(buffer)) >= 0) {
      out.write(buffer, 0, amount);
    }

    out.flush();
  }

}
