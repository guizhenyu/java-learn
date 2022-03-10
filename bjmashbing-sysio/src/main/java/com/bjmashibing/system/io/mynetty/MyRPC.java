package com.bjmashibing.system.io.mynetty;



/*
  1. RPC需要哪些
  2. 来回通信， 连接数量，拆包
  3. 动态代理，序列化，协议封装
  4. 连接池
  5. RPC 本质上就是: 就像调用本地方法一样去调用远程的方法，面向java中就是所谓的 面向interfaces开发
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

/**
 * description: MyRPC date: 2021/5/6 5:56 下午
 *
 * @author: guizhenyu
 */
public class MyRPC {


  @Test
  public void startServer(){

    MyCar car = new MyCar();
    MyFly fly = new MyFly();
    Dispatcher dis = new Dispatcher();
    dis.register(Car.class.getName(), car);
    dis.register(Fly.class.getName(), fly);

    NioEventLoopGroup boss = new NioEventLoopGroup(20);
    NioEventLoopGroup worker = boss;

    ServerBootstrap sbs = new ServerBootstrap();
    ChannelFuture bind = sbs.group(boss, worker)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<NioSocketChannel>() {
          @Override
          protected void initChannel(NioSocketChannel ch) throws Exception {
            System.out.println("server accept client port: " + ch.remoteAddress().getPort());
            ChannelPipeline p = ch.pipeline();
            p.addFirst(new ServerDecode());
            p.addLast(new ServerRequestHandler(dis));
          }
        }).bind(new InetSocketAddress("localhost", 9091));

    try {
      bind.sync().channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void get(){

    new Thread(() -> {
      startServer();
    }).start();

    System.out.println("server started....");

    AtomicInteger num = new AtomicInteger(0);

    int size = 50;

    Thread[] threads = new Thread[size];
    for (int i = 0; i < size; i ++ ){

      threads[i] = new Thread(() ->{
        Car car = proxyGet(Car.class);
        String arg = "hello" + num.incrementAndGet();
        String res = car.ooxx(arg);
//        System.out.println("client over msg: " + res + "src arg: " + arg);
      });

    }

    for (Thread thread : threads){
      thread.start();
    }

    try {
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }




  public static <T>T proxyGet(Class<T> interfaceInfo){
    // 实现各个版本的动态代理
    ClassLoader classLoader = interfaceInfo.getClassLoader();
    Class<?>[] methodInfo = {interfaceInfo};

    return (T) Proxy.newProxyInstance(classLoader, methodInfo, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如何设计我们的consumer对于provider的调用过程

        // 1. 调用 服务，方法，参数 ==》 封装成message [content]
        String name = interfaceInfo.getName();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        MyContent content = new MyContent();

        content.setArgs(args);
        content.setName(name);
        content.setMethodName(methodName);
        content.setParameterTypes(parameterTypes);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeObject(content);
        byte[] msgBody = out.toByteArray();
        // 2. requestID + message, 本地要缓存
        // 协议： 【header<>】 【msgBody】
        Myheader header = createHeader(msgBody);

        out.reset();
        oout = new ObjectOutputStream(out);
        oout.writeObject(header);

        // 解决数据decode问题
        // TODO: Server:: dispatcher Executor
        byte[] msgHeader = out.toByteArray();
        System.out.println("old:::---" + msgHeader.length);

        // 3. 连接池：： 取得连接
        ClientFactory factory = ClientFactory.getFactory();
        NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9091));

        // 4. 发送----》 走io, out --> 走Netty (Event )

        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT
            .directBuffer(msgHeader.length + msgBody.length);

        long id = header.getRequestID();
        CompletableFuture<String> res = new CompletableFuture<>();
        ResponseMappingCallback.addCallBack(id, res);

        byteBuf.writeBytes(msgHeader);
        byteBuf.writeBytes(msgBody);
        ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
        channelFuture.sync();


        //5，？，如果从IO ，未来回来了，怎么将代码执行到这里
        //（睡眠/回调，如何让线程停下来？你还能让他继续。。。）

        return res.get();
      }
    });
  }

  public static Myheader createHeader(byte[] msg){
    Myheader header = new Myheader();
    int length = msg.length;
    int f = 0x14141414;
    long requestID = Math.abs(UUID.randomUUID().getLeastSignificantBits());

    header.setFlag(f);
    header.setDataLen(length);
    header.setRequestID(requestID);
    return header;

  }

}
class MyContent implements Serializable{

  String name;
  String methodName;
  Class<?>[] parameterTypes;
  Object[] args;
  String res;

  public String getRes() {
    return res;
  }

  public void setRes(String res) {
    this.res = res;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Class<?>[] getParameterTypes() {
    return parameterTypes;
  }

  public void setParameterTypes(Class<?>[] parameterTypes) {
    this.parameterTypes = parameterTypes;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }
}



class Myheader implements Serializable{
  /*
  // 通信上的协议
  1. ooxx的值
  2. UUID: requestID
  3. DATA_LEN
   */
  int flag; // 32bit可以设置很多信息
  long requestID;
  long dataLen;

  public int getFlag() {
    return flag;
  }

  public void setFlag(int flag) {
    this.flag = flag;
  }

  public long getRequestID() {
    return requestID;
  }

  public void setRequestID(long requestID) {
    this.requestID = requestID;
  }

  public long getDataLen() {
    return dataLen;
  }

  public void setDataLen(long dataLen) {
    this.dataLen = dataLen;
  }
}
interface Car{
  public String ooxx(String msg);
}

interface Fly{
  void xxoo(String msg);
}

class MyCar implements Car{

  @Override
  public String ooxx(String msg) {
    System.out.println("server car, get client arg:" + msg);
    return "server res " + msg;
  }
}

class MyFly implements Fly{

  @Override
  public void xxoo(String msg) {
    System.out.println("server fly, get client arg:" + msg);
  }
}


// 源于 spark 源码
class ClientFactory{

  int poolSize = 10;
  NioEventLoopGroup clientWorker;
  Random random =new Random();
  private ClientFactory(){}

  // toto zhezhng
  private static final ClientFactory factory;

  static {
    factory = new ClientFactory();
  }

  public static ClientFactory getFactory(){
    return factory;
  }

  ConcurrentHashMap<InetSocketAddress, ClientPool> outBoxs = new ConcurrentHashMap<>();

  public synchronized NioSocketChannel getClient(InetSocketAddress address){

    ClientPool clientPool = outBoxs.get(address);

    if (Objects.isNull(clientPool)){
      outBoxs.putIfAbsent(address, new ClientPool(poolSize));
      clientPool = outBoxs.get(address);
    }

    int i = random.nextInt(poolSize);

    if (clientPool.clients[i] != null  && clientPool.clients[i].isActive()){
      return clientPool.clients[i];
    }

    synchronized (clientPool.lock[i]){
      return clientPool.clients[i] = create(address);
    }
  }

  private NioSocketChannel create(InetSocketAddress address) {

    // 基于netty的创建客户端创建方式

    clientWorker  = new NioEventLoopGroup(1);
    Bootstrap bs = new Bootstrap();
    ChannelFuture connect = bs.group(clientWorker)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<NioSocketChannel>() {
          @Override
          protected void initChannel(NioSocketChannel ch) throws Exception {
            ChannelPipeline p = ch.pipeline();
            p.addLast(new ServerDecode());
            p.addLast(new ClientResponses());
          }
        }).connect(address);

    NioSocketChannel client = null;
    try {
      client = (NioSocketChannel) connect.sync().channel();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return client;
  }

}

class ResponseMappingCallback{
  static ConcurrentHashMap<Long, CompletableFuture> mapping = new ConcurrentHashMap<>();

  public static void addCallBack(long requestId, CompletableFuture cb){
    mapping.putIfAbsent(requestId, cb);
  }

  public static void  runCallBack(Packmsg msg){

    CompletableFuture cf = mapping.get(msg.header.getRequestID());

    cf.complete(msg.getContent().getRes());
    removeCB(msg.header.getRequestID());
  }

  private static void removeCB(long requestID) {
    mapping.remove(requestID);
  }
}

class ClientResponses extends ChannelInboundHandlerAdapter{
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
    Packmsg responsepkg = (Packmsg) msg;

    ResponseMappingCallback.runCallBack(responsepkg);
  }
}

class ClientPool{
  NioSocketChannel[] clients;
  Object[] lock;

  ClientPool(int size){
    // init 连接都是空的
    clients = new NioSocketChannel[size];
    // 锁也是初始化
    lock = new Object[size];
    for (int i = 0; i < size; i++){
      lock[i] = new Object();
    }
  }

}

class ServerDecode extends ByteToMessageDecoder{

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {

    while (buf.readableBytes() >= 110){
      byte[] bytes = new byte[110];
      buf.getBytes(buf.readerIndex(), bytes);
      ByteArrayInputStream in = new ByteArrayInputStream(bytes);
      ObjectInputStream oin = new ObjectInputStream(in);
      Myheader header = (Myheader) oin.readObject();

      // DECODE在两个方向都用
      if (buf.readableBytes() >= header.getDataLen()){
        // 处理指针
        buf.readBytes(110); // 移动指针到body开始的位置
        byte[] data = new byte[(int) header.getDataLen()];
        buf.readBytes(data);
        ByteArrayInputStream din = new ByteArrayInputStream(data);
        ObjectInputStream doin = new ObjectInputStream(din);

        if (header.getFlag() == 0x14141414){
          MyContent content = (MyContent) doin.readObject();
          out.add(new Packmsg(header, content));
        }
        if (header.getFlag() == 0x14141424){
          MyContent content = (MyContent) doin.readObject();
          out.add(new Packmsg(header, content));
        }

      }else{
        break;
      }
    }
  }
}

class Packmsg {

  Myheader header;
  MyContent content;

  public Myheader getHeader() {
    return header;
  }

  public void setHeader(Myheader header) {
    this.header = header;
  }

  public MyContent getContent() {
    return content;
  }

  public void setContent(MyContent content) {
    this.content = content;
  }

  public Packmsg(
      Myheader header, MyContent content) {
    this.header = header;
    this.content = content;
  }
}

class Dispatcher{

  public static ConcurrentHashMap<String, Object> invokeMap = new ConcurrentHashMap<>();

  public void register(String k, Object obj){
    invokeMap.put(k, obj);
  }

  public Object get(String k){
    return invokeMap.get(k);
  }
}