package io.gzy.java.concurrency.homework;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description: AtomaticIntegerTest
 * date: 2021/2/2 5:37 下午
 * author: guizhenyu
 */
public class AtomaticIntegerTest {
    static  AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {



        for (int i = 0; i < 5000; i++){
            new Thread(){
                @Override
                public void run() {
                    atomicInteger.getAndAdd(1);
                }}.start();
        }

        System.out.printf(atomicInteger.get() + "");

    }

}
