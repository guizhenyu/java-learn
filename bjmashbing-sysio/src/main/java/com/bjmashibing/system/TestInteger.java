package com.bjmashibing.system;

/**
 * description: TestInteger date: 2021/8/6 3:24 下午
 *
 * @author: guizhenyu
 */
public class TestInteger {


  public static void main(String[] args) {
    Integer i1 = 127;
    Integer i2 = 127;
    System.err.println(i1 == i2);

    Integer i4 = 127;
    Integer i3 = new Integer(100);

    System.err.println(i3 == 100);



    i1 = 128;
    i2 = 128;
    System.err.println(i1 == i2);
  }

}
