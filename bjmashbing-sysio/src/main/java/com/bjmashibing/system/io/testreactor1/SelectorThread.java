package com.bjmashibing.system.io.testreactor1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * description: SelectorThread date: 2021/4/21 9:51 上午
 *
 * @author: guizhenyu
 */
public class SelectorThread implements Runnable{

  Selector selector = null;

  SelectorThreadGroup stg;

  LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();


  SelectorThread(SelectorThreadGroup selectorThreadGroup){
    try {
      stg = selectorThreadGroup;
      selector = Selector.open();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    // loop
    while (true){
      try {

        System.out.println(Thread.currentThread().getName()+"   :  before select...."+ selector.keys().size());

        int nums = selector.select();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+"   :  after select...." + selector.keys().size());

        if(nums > 0){
          Set<SelectionKey> selectionKeys = selector.selectedKeys();
          Iterator<SelectionKey> iterator = selectionKeys.iterator();
          while(iterator.hasNext()){
            SelectionKey key = iterator.next();
            iterator.remove();

            if (key.isAcceptable()){
              acceptHandler(key);

            }

            if (key.isReadable()){
              readHandler(key);
            }

            if (key.isWritable()){

              writeHandler(key);
            }
          }

          if (!lbq.isEmpty()){
            Channel c = lbq.take();
            if (c instanceof ServerSocketChannel){
              ServerSocketChannel server = (ServerSocketChannel) c;
              server.register(selector, SelectionKey.OP_ACCEPT);
              System.out.println(Thread.currentThread().getName()+" register listen");

            }
            if (c instanceof SocketChannel){
              SocketChannel client = (SocketChannel) c;
              ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
              client.register(selector, SelectionKey.OP_READ, buffer);
              System.out.println(Thread.currentThread().getName()+" register client: " + client.getRemoteAddress());

            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void readHandler(SelectionKey key) {
    ByteBuffer buffer = (ByteBuffer) key.attachment();
    SocketChannel client = (SocketChannel) key.channel();
    buffer.clear();
    while (true){

      try {
        int num = client.read(buffer);
        if (num > 0){
          buffer.flip();
          while (buffer.hasRemaining()){
            client.write(buffer);
          }
          buffer.clear();
        }

        if (num == 0){
          break;
        }

        if (num < 0){
          // 客户端断开了
          System.out.println("client: " + client.getRemoteAddress()+"closed......");

          key.cancel();
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }


    }

  }

  private void acceptHandler(SelectionKey key) {
    System.out.println(Thread.currentThread().getName()+"   acceptHandler......");

    ServerSocketChannel server = (ServerSocketChannel) key.channel();

    try {
      SocketChannel client = server.accept();
      client.configureBlocking(false);
      // 选择一个多路复用器去注册
      stg.nextSelector(client);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
  private void writeHandler(SelectionKey key) {
  }
}
