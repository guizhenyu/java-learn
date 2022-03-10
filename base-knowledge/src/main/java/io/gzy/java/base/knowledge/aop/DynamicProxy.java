package io.gzy.java.base.knowledge.aop;

/**
 * description: DynamicProxy
 * date: 2021/2/4 2:24 下午
 * author: guizhenyu
 */
public class DynamicProxy {


//  public static void main(String[] args) {
//    int i = 1;
//    i = i++;
//    int j = i++;
//    int k = i + ++i * i++;
//    System.out.println("i="+i);
//    System.out.println("j="+j);
//    System.out.println("k="+k);
//  }
  public static void main(String[] args) {
    int i = 9;
    i = i++;

    int b = i;
    System.out.println(i);
    System.out.println(b);
    i = ++i;
    System.out.println(i);
  }
}
