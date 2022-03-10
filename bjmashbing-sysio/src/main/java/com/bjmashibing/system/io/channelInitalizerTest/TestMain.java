package com.bjmashibing.system.io.channelInitalizerTest;

/**
 * description: TestMain date: 2021/5/27 8:01 下午
 *
 * @author: guizhenyu
 */
public class TestMain {


  public static void main(String[] args) {
    SharableTest a = new SharableTest() {
      @Override
      public String init() {
        return null;
      }
    };
    System.out.println(a);
    SharableTest b = new SharableTest() {
      @Override
      public String init() {
        return null;
      }
    };
    System.out.println(b);
    Imp c = new Imp();
    System.out.println(c);


  }
}
