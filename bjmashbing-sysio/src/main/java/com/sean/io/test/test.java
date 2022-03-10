package com.sean.io.test;

/**
 * description: test date: 2021/5/21 2:25 下午
 *
 * @author: guizhenyu
 */
public class test {
  /**
   * Returns Integer corresponding to s, or null if s is null.
   * @throws NumberFormatException if s is nonnull and
   * doesn't represent a valid integer
   */
  public static Integer parseInt(String s) {
    return (s != null) ?
        (Integer) nullT(s) : new Integer(1) + new Integer(2);
  }
  public static Integer nullT(String s) {
    return null;
  }
  public static void main(String[] args) {
    System.out.println(parseInt("-1") + " " +
        parseInt(null) + " " +
        parseInt("1"));

    Integer a =10;
    Integer b = 20;

    System.out.println(a + b);
  }



}
