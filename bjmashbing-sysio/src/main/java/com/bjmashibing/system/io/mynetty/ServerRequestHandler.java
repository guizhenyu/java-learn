package com.bjmashibing.system.io.mynetty;

import com.bjmashibing.system.io.netty.SerDerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description: ServerRequestHandler date: 2021/5/6 11:31 下午
 *
 * @author: guizhenyu
 */
public class ServerRequestHandler extends ChannelInboundHandlerAdapter {

  Dispatcher dis;

  public ServerRequestHandler(Dispatcher dis) {
    this.dis = dis;
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg){
    Packmsg requestPkg = (Packmsg) msg;

    // 有新的header + content
    String ioThreadName = Thread.currentThread().getName();
    //1. 直接在当前放大处理IO和业务返回

    //3. 使用netty自己的eventloop 来处理业务及返回

    ctx.executor().execute(new Runnable() {
      @Override
      public void run() {

        String serviceName = requestPkg.content.getName();
        String methodName = requestPkg.content.getMethodName();
        Object c = dis.get(serviceName);

        Class<?> clazz = c.getClass();

        Object res = null;

        try {
            Method method = clazz.getMethod(methodName, requestPkg.content.parameterTypes);
            res = method.invoke(c, requestPkg.content.getArgs());
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }

        MyContent content = new MyContent();
                String execThreadName = Thread.currentThread().getName();
                String s = "io thread: " + ioThreadName + " exec thread: " + execThreadName + " from args:" + requestPkg.content.getArgs()[0];
        System.out.println(s);
        content.setRes((String)res);
        byte[] contentBytes = SerDerUtil.ser(content);

        Myheader resHeader = new Myheader();
        resHeader.setRequestID(requestPkg.header.getRequestID());
        resHeader.setFlag(0x14141424);
        resHeader.setDataLen(contentBytes.length);
        byte[] headerBytes = SerDerUtil.ser(resHeader);

        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(
            contentBytes.length + headerBytes.length);
        byteBuf.writeBytes(headerBytes);
        byteBuf.writeBytes(contentBytes);

        ctx.writeAndFlush(byteBuf);

      }
    });

  }
}
