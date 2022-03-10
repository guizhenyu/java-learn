package com.bjmashibing.system.io.testreactor1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description: SelectorThreadGroup date: 2021/4/21 9:50 上午
 *
 * @author: guizhenyu
 */
public class SelectorThreadGroup {

  SelectorThread[] sts;

  ServerSocketChannel server = null;

  SelectorThreadGroup stg = this;

  AtomicInteger xid = new AtomicInteger(0);

  public SelectorThreadGroup(int threadNum) {
    sts = new SelectorThread[threadNum];

    for (int i = 0 ; i < threadNum; i++){
      sts[i] = new SelectorThread(this);

      new Thread(sts[i]).start();
    }
  }

  public void bind(int port){

    try {
      server = ServerSocketChannel.open();
      server.configureBlocking(false);
      server.bind(new InetSocketAddress(port));


      nextSelector(server);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void nextSelector(Channel c) {
    SelectorThread st = next();
    st.lbq.add(c);
    st.selector.wakeup();
    ServerSocketChannel s = (ServerSocketChannel) c;
    try {
      s.register(st.selector, SelectionKey.OP_ACCEPT);
      st.selector.wakeup();

    } catch (ClosedChannelException e) {
      e.printStackTrace();
    }
  }

  private SelectorThread next(){
    int index = xid.incrementAndGet() % sts.length;
    return sts[index];
  }

}
