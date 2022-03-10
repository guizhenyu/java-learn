package io.gzy.java.base.knowledge.proxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import sun.misc.ProxyGenerator;
import sun.reflect.misc.ReflectUtil;

/**
 * description: Proxy date: 2022/1/12 6:27 上午
 *
 * @author: guizhenyu
 */
public class Proxy implements java.io.Serializable {

  // 用于在实现方法中回调的调用处理器
  protected InvocationHandler h;

  // 保存该处理器对象构造方法
  protected Proxy(InvocationHandler h) {
    Objects.requireNonNull(h);
    this.h = h;
  }

  // 构建代理类的Class对象
  public static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces)
      throws IllegalArgumentException {
    final Class<?>[] intfs = interfaces
        .clone(); // 克隆一个传入的接口类数组，通过复制该数组，我们可以保证在创建过程中intfs的定性（也即不会被外部方法进行修改）
    return getProxyClass0(loader, intfs);
  }

  // 执行创建操作
  private static Class<?> getProxyClass0(ClassLoader loader, Class<?>... interfaces) {
    // 限制接口数量为2byte大小（JVM规范定义）
    if (interfaces.length > 65535) {
      throw new IllegalArgumentException("interface limit exceeded");
    }
    // 通过缓存获取，如果没有获取成功，那么创建代理类对象
    return proxyClassCache.get(loader, interfaces);
  }

  // 将在proxyClassCache中获取loader对应的interfaces的代理类为空时调用，这里使用了策略模式，实际完成创建代理的方法在该类中，所以读者直接看这里即可
  private static final class ProxyClassFactory implements
      BiFunction<ClassLoader, Class<?>[], Class<?>> {

    // 代理类名前缀名
    private static final String proxyClassNamePrefix = "$Proxy";
    // 用于与前缀名拼接的自增原子性long对象
    private static final AtomicLong nextUniqueNumber = new AtomicLong();

    // 实现代理对象的创建
    public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {
      Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
      for (Class<?> intf : interfaces) { // 遍历接口Class数组
        // 使用传递的类加载器加载接口类信息，在加载过程中将会对这些接口的定义进行约束检测
        Class<?> interfaceClass = null;
        try {
          interfaceClass = Class.forName(intf.getName(), false, loader);
        } catch (ClassNotFoundException e) {
        }
        if (interfaceClass
            != intf) { // 如果发现传入的类加载器加载的Class对象与传入的接口class对象不相同，说明调用该方法时的类加载器与传入的类加载器不符，这时表明当前接口对传入的类加载器不可见，抛出异常
          throw new IllegalArgumentException(
              intf + " is not visible from class loader");
        }
        // 验证该类为接口类型
        if (!interfaceClass.isInterface()) {
          throw new IllegalArgumentException(
              interfaceClass.getName() + " is not an interface");
        }
        // 验证没有重复实现同一个接口
        if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
          throw new IllegalArgumentException(
              "repeated interface: " + interfaceClass.getName());
        }
      }
      String proxyPkg = null;     // 定义代理类所属包名
      int accessFlags = Modifier.PUBLIC | Modifier.FINAL; // 默认代理类的访问修饰符（public final）
      // 遍历需要实现的接口class实例，记录一个 non-public 代理接口的包，这样就可以在同一个包中定义代理类，同时验证所有 non-public 代理接口都在同一个包中（如果代理的接口为非public的，必然生成的类需要和该接口所在的包相同）
      for (Class<?> intf : interfaces) {
        int flags = intf.getModifiers();
        if (!Modifier.isPublic(flags)) { // 如果找到一个接口不是public共有的，那么修改代理的accessFlags修饰符仅为final
          accessFlags = Modifier.FINAL;
          String name = intf.getName();
          int n = name.lastIndexOf('.');
          String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
          if (proxyPkg == null) {
            proxyPkg = pkg;
          } else if (!pkg.equals(proxyPkg)) {
            throw new IllegalArgumentException(
                "non-public interfaces from different packages");
          }
        }
      }
      // 如果不是non-public的接口，那么使用com.sun.proxy作为代理类的包名
      if (proxyPkg == null) {
        proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
      }
      // 生成代理类的名字
      long num = nextUniqueNumber.getAndIncrement();
      String proxyName = proxyPkg + proxyClassNamePrefix + num;
      // 生成代理类的字节文件
      byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
          proxyName, interfaces, accessFlags);
      try {
        // 直接通过defineClass JNI方法进行字节码加载操作，该操作将会返回Class实例
        return defineClass0(loader, proxyName,
            proxyClassFile, 0, proxyClassFile.length);
      } catch (ClassFormatError e) {
        throw new IllegalArgumentException(e.toString());
      }
    }
  }

  public static byte[] generateProxyClass(final String name,
      Class<?>[] interfaces,
      int accessFlags)
  {
    // 创建ProxyGenerator对象
    ProxyGenerator gen = new ProxyGenerator(name, interfaces, accessFlags);
    final byte[] classFile = gen.generateClassFile(); // 生成字节码
    // 如果设置环境变量saveGeneratedFiles为true，那么保存在字节码信息
    if (saveGeneratedFiles) {
      java.security.AccessController.doPrivileged(
          new java.security.PrivilegedAction<Void>() {
            public Void run() {
              try {
                int i = name.lastIndexOf('.');
                Path path;
                if (i > 0) {
                  Path dir = Paths.get(name.substring(0, i).replace('.', File.separatorChar));
                  Files.createDirectories(dir); // 创建目录
                  path = dir.resolve(name.substring(i+1, name.length()) + ".class");
                } else {
                  path = Paths.get(name + ".class");
                }
                Files.write(path, classFile); // 写入字节码信息
                return null;
              } catch (IOException e) {
                throw new InternalError(
                    "I/O exception saving generated file: " + e);
              }
            }
          });
    }
    return classFile;
  }

}