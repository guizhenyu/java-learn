package io.gzy.java.concurrency.homework;

import java.util.HashMap;

/**
 * description: CodeReSort
 * date: 2021/2/1 10:05 上午
 * author: guizhenyu
 */
public class CodeReSort {

    private static boolean isChange;

    private static int number;


    public static void main(String[] args) {

        new HashMap<String, String>();
       new Thread() {
            @Override
            public void run() {
                while (!isChange){
                    Thread.yield();
                }
                System.out.println(number);
            }
        }.start();

       isChange = true;
       number = 2;
    }
}
