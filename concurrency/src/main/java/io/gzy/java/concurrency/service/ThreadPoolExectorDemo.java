package io.gzy.java.concurrency.service;


import io.gzy.java.concurrency.entity.MyTask;
import javafx.concurrent.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description: ThreadPoolExectorDemo
 * date: 2020/12/29 5:30 下午
 * author: guizhenyu
 */
public class ThreadPoolExectorDemo {

    /**
     * 自定义线程工厂实现类
     */
    private static class MyThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            if (thread.getPriority() != Thread.NORM_PRIORITY){
                //设置线程优先级
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            if (thread.isDaemon()){
                // 设置改线程为守护线程
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    /**
     * 自定义拒绝策略
     */
    private static class MyRejectExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("当前任务被拒绝：" + r.toString());
        }
    }

    public static void main(String[] args) {
        //创建一个有界的阻塞队列对象
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        //线程工厂
        ThreadFactory factory = new MyThreadFactory(); 
        MyRejectExecutionHandler handler = new MyRejectExecutionHandler();
        ExecutorService pool = new ThreadPoolExecutor(1, 
                2, 
                  60, 
                TimeUnit.SECONDS,
                workQueue,
                factory,
                handler
                );

        MyTask t1 = new MyTask(1) ;
        MyTask t2 = new MyTask(2) ;
        MyTask t3 = new MyTask(3) ;



        //提交2个线程
        System.out.println("提交任务t1,目前线程池的状况:" + pool.toString());
        pool.execute(t1);
        System.out.println("提交任务t2,目前线程池的状况:" + pool.toString());
        pool.execute(t2);

        //关闭线程池
        pool.shutdown();
    }
}
