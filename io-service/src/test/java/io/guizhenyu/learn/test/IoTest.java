package io.guizhenyu.learn.test;

import com.sun.deploy.util.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Test;

/**
 * description: IoTest date: 2021/4/13 6:05 下午
 *
 * @author: guizhenyu
 */
public class IoTest {

  private final static String CONTENT = "Zero copy implemented by MappedByteBuffer";
  private final static String FILE_NAME = "/Users/apple_gui/idea_project/java-learn/io-service/mmap.txt";
  private final static String CHARSET = "UTF-8";

  @Test
  public void writeToFileByMapperedByteBuffer(){
//    URL resource = getClass().getResource(FILE_NAME);
//    String pathStr = resource.getPath();
    Path path = Paths.get(FILE_NAME);
    byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));
    try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ,
        StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
      MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, bytes.length);
      if (mappedByteBuffer != null) {
        mappedByteBuffer.put(bytes);
        mappedByteBuffer.force();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void readFromFileByMappedByteBuffer() {
    Path path = Paths.get(FILE_NAME);
    int length = CONTENT.getBytes(Charset.forName(CHARSET)).length;
    try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
      MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_ONLY, 0, length);
      if (mappedByteBuffer != null) {
        byte[] bytes = new byte[length];
        mappedByteBuffer.get(bytes);
        String content = new String(bytes, StandardCharsets.UTF_8);
        assertEquals(content, "Zero copy implemented by MappedByteBuffer");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void assertEquals(String content, String zero_copy_implemented_by_mappedByteBuffer) {
    if (!zero_copy_implemented_by_mappedByteBuffer.equals(content)){
      System.out.println("文件内容不一致----------");
    }
  }

}
