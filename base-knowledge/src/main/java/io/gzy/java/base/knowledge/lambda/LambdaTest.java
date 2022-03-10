package io.gzy.java.base.knowledge.lambda;

/**
 * description: LambdaTest
 * date: 2021/4/8 9:59 上午
 *
 * @author: guizhenyu
 */
public class LambdaTest {

    interface Print<T>{
        public void print(T x);
    }
    public static void PrintString(String s, Print<String> print) {
        print.print(s);
    }
    public static void main(String[] args) {
        PrintString("test", (x) -> System.out.println(x));
    }

}
