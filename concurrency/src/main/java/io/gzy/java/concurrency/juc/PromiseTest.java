package io.gzy.java.concurrency.juc;

import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;
import java.util.concurrent.TimeUnit;

/**
 * description: PromiseTest date: 2022/2/28 10:54 上午
 *
 * @author: guizhenyu
 */
public class PromiseTest {


  public static void main(String[] args) throws InterruptedException {

    EventExecutor executor = new DefaultEventExecutor();

    Promise<String> promise = executor.newPromise();

    promise.addListener((FutureListener<String>)future -> System.out.println(future.get()));

    executor.execute(() ->
    {

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      promise.setSuccess("hello");
//      promise.setFailure(new Exception("dfgdf"));
    });

    executor.shutdownNow();
    executor.awaitTermination(1, TimeUnit.HOURS);
  }
}
