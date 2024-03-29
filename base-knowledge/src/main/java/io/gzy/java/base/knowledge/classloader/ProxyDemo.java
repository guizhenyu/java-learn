package io.gzy.java.base.knowledge.classloader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: ProxyDemo date: 2022/1/11 8:51 下午
 *
 * @author: guizhenyu
 */
public class ProxyDemo {

  public static void main(String[] args) throws Exception {
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); // 将创建的代理类$Proxy0的字节码保存
    InvocationHandler handler = new MyInvocationHandler(); // 创建回调函数
    Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), Foo.class); // 创建代理类
    Foo f = (Foo) proxyClass.getConstructor(InvocationHandler.class).newInstance(handler); // 生成代理对象
    f.foo(); // 调用代理对象方法
    f.tee();
  }
  // 回调处理器
  private static class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println(method.getName());
      return null;
    }
  }
  private interface Foo {
    void foo();

    void tee();
  }

}
