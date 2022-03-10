package com.bjmashibing.system.io.mynetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

/**
 * description: MyNetty01 date: 2021/4/25 10:24 下午
 *
 * @author: guizhenyu
 */
public class MyNetty01 {


  @Test
  public void clientNode () throws Exception {
    NioEventLoopGroup thread = new NioEventLoopGroup(50);

    NioSocketChannel client = new NioSocketChannel();

    thread.register(client);

    ChannelFuture connect = client.connect(new InetSocketAddress("192.168.2.248", 9090));
    ChannelFuture sync = connect.sync();
    ByteBuf byteBuf = Unpooled.copiedBuffer("hello world!".getBytes());
    ChannelFuture send = client.writeAndFlush(byteBuf);
    send.sync();

    sync.channel().closeFuture().sync();
    System.out.println("client over!");
  }

}
