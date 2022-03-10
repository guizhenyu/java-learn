package io.gzy.java.base.knowledge.synchorize;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description: MangoIceProduceConsume
 * date: 2021/3/31 7:42 下午
 *
 * @author: guizhenyu
 */
public class MangoIceProduceConsume {

    private final int MAX_SIZE = 10;

    private final LinkedList<MangoIce> mangoIces = new LinkedList<MangoIce>();

    public void produce() throws InterruptedException {
        while (true){
            synchronized (mangoIces){
                while (mangoIces.size() == MAX_SIZE){
                    try {
                        mangoIces.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                mangoIces.add(new MangoIce());
                System.out.println("【目前剩余的冰沙数】：" + mangoIces.size());
                mangoIces.notify();

                Thread.sleep(new Random().nextInt(100));

            }
        }
    }

    public void consume() throws InterruptedException {
        while (true){
            synchronized (mangoIces){

                while (mangoIces.size() == 0){
                    mangoIces.wait();
                }

                mangoIces.remove();
                System.out.println("【目前消费后的冰沙数】：" + mangoIces.size());
                mangoIces.notify();

                Thread.sleep(new Random().nextInt(100));
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        MangoIceProduceConsume produceAndConsumerTest = new MangoIceProduceConsume();

        for(int i = 0;i < 10; i++){
            executorService.submit(() -> {
                try {
                    produceAndConsumerTest.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            executorService.submit(() -> {
                try {
                    produceAndConsumerTest.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
