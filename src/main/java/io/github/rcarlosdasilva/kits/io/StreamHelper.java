package io.github.rcarlosdasilva.kits.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import com.google.common.base.Preconditions;

import io.github.rcarlosdasilva.kits.Default;

public class StreamHelper {

  private StreamHelper() {
    throw new IllegalStateException("StreamHelper class");
  }

  /**
   * 关闭流.
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
   * 从输入流读取内容, 写入到输出流中.
   * 
   * @param in
   *          输入
   * @param out
   *          输出
   * @throws IOException
   *           输入输出异常
   */
  public static void io(Reader in, Writer out) throws IOException {
    io(in, out, Default.DEFAULT_STREAM_BUFFER_SIZE);
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
    Preconditions.checkNotNull(in);
    Preconditions.checkNotNull(out);
    Preconditions.checkArgument(bufferSize > 0);

    char[] buffer = new char[bufferSize];
    int amount;

    while ((amount = in.read(buffer, 0, bufferSize)) != -1) {
      out.write(buffer, 0, amount);
      out.flush();
    }
  }

  /**
   * 从输入流读取内容, 写入到输出流中.
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
    io(in, out, Default.DEFAULT_STREAM_BUFFER_SIZE);
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
  public static void io(InputStream in, OutputStream out, int bufferSize) throws IOException {
    Preconditions.checkNotNull(in);
    Preconditions.checkNotNull(out);
    Preconditions.checkArgument(bufferSize > 0);

    io(new InputStreamReader(in), new OutputStreamWriter(out), bufferSize);
  }

  /**
   * 非阻塞IO，文件转文件.
   * 
   * @param in
   *          input
   * @param out
   *          output
   * @throws IOException
   *           输入输出异常
   */
  public static void nio(File in, File out) throws IOException {
    Preconditions.checkNotNull(in);
    Preconditions.checkNotNull(out);

    try (FileInputStream fis = new FileInputStream(in);
        FileChannel inChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream(out);
        FileChannel outChannel = fos.getChannel();) {
      inChannel.transferTo(0, inChannel.size(), outChannel);
    }
  }

  /**
   * 非阻塞IO，从输入流读取内容, 写入到输出流中.
   * 
   * @param in
   *          input
   * @param out
   *          output
   * @throws IOException
   *           输入输出异常
   */
  public static void nio(InputStream in, OutputStream out) throws IOException {
    nio(in, out, Default.DEFAULT_STREAM_BUFFER_SIZE);
  }

  /**
   * 非阻塞IO，从输入流读取内容, 写入到输出流中.使用指定大小的缓冲区.
   * 
   * @param in
   *          input
   * @param out
   *          output
   * @param bufferSize
   *          缓冲区大小(字节数)
   * @throws IOException
   *           输入输出异常
   */
  public static void nio(InputStream in, OutputStream out, int bufferSize) throws IOException {
    Preconditions.checkNotNull(in);
    Preconditions.checkNotNull(out);
    Preconditions.checkArgument(bufferSize > 0);

    ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);

    ReadableByteChannel inChannel = Channels.newChannel(in);
    WritableByteChannel outChannel = Channels.newChannel(out);

    while (inChannel.read(buffer) != -1) {
      buffer.flip(); // 翻转缓冲区，转换成读模式，为读取缓冲区数据做准备
      // 这个while循环中会获取当前填充到缓冲区的数据，写到通道中
      while (buffer.hasRemaining()) {
        outChannel.write(buffer);
      }
      buffer.clear();// 清空缓冲区，为填充作准备
    }
  }

  public static byte[] readBytes(InputStream in) throws IOException {
    return readBytes(in, 0);
  }

  public static byte[] readBytes(InputStream in, int length) throws IOException {
    Preconditions.checkNotNull(in);

    if (length <= 0) {
      return Default.EMPTY_TYBES;
    }

    byte[] buffer = new byte[length];

    if (in.markSupported()) {
      in.mark(length + 1);
    }

    int readLength = in.read(buffer);

    if (in.markSupported()) {
      in.reset();
    }

    if (readLength < 0) {
      return Default.EMPTY_TYBES;
    }
    if (readLength < length) {
      byte[] newBuffer = new byte[readLength];
      System.arraycopy(buffer, 0, newBuffer, 0, readLength);
      buffer = newBuffer;
    }
    return buffer;
  }

}
