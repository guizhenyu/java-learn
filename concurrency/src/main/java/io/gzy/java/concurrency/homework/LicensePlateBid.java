package io.gzy.java.concurrency.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

/**
 * description: LicensePlateBid
 * date: 2021/1/31 10:53 下午
 * author: guizhenyu
 */
public class LicensePlateBid {
    static final int initialPlatePrice = 4000;
    static int platePrice = initialPlatePrice;
    static int cnt = 0; //报价次数
    static final Object lock = new Object();
    static Map<String, Integer> priceMap = new HashMap<>();
    static final Random random = new Random();
    /**
     * 有一个车牌拍卖系统，参与竞拍的人每个人需要拍出一个价格，
     * 但是竞拍人，不能连续出价，必须有人出价以后自己才能出价，否则无效。
     * 并且每次竞拍价格区间在1-200元以内，请写一段代码，
     * 模拟多人竞拍1000次以后，谁获得了基础价格是4000元的车牌.
     * @param args
     */
    public static void main(String[] args) {
        Stream.of("我","黄晓","伏二兆","尹德福","冯力").peek(e -> priceMap.put(e, initialPlatePrice)).
                forEach(e -> new Thread(() -> {
                    synchronized (lock){
                        while(cnt < 1000){
                            if(platePrice == initialPlatePrice || priceMap.get(e) != platePrice){
                                platePrice += random.nextInt(200) + 1;
                                priceMap.put(e, platePrice);
                                System.out.println(e + " 第 " + ( ++cnt) + " 次报价 " + platePrice);
                                if (cnt == 1000){
                                    System.out.println(e + " 第 " + cnt + " 次, 花了 " + platePrice);
                                }
                            }else{
                                System.out.println(e + " 连续报价 ");
                            }
                            lock.notifyAll();
                            try {
                                lock.wait();
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        lock.notifyAll();
                    }
                }, e).start());

    }
}
