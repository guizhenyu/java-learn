package cn.iocoder.springboot.eventdemo.event;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * description: CostomerSerializer date: 2021/4/14 6:30 下午
 *
 * @author: guizhenyu
 */
public class CostomerSerializer implements ObjectSerializer {

  @Override
  public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i)
      throws IOException {
    System.out.println("序列化增强");
  }
}
